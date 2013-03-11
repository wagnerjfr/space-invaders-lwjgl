package main;

import static entities.WorldVariables.HEIGHT;
import static entities.WorldVariables.IMAGE_HEIGHT;
import static entities.WorldVariables.WIDTH;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import objects.Aliens;
import objects.CollisionType;
import objects.Player;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import state.Background;
import state.Background.BackgroundType;
import state.Control;
import state.Control.StateType;
import state.SetStage;
import effects.Effects;
import effects.Sound;
import entities.ObjectType;

public class InvadersMain {
		
	private boolean isRunnig = true;
	private long lastFrame;
	
	private Player player;
	private Aliens aliens;
	private Control control;
	private SetStage setStage;
	private Background background, backgroundMain, backgroundExit;
	
	public InvadersMain() {
		setUpDisplay();
		setUpOpenGL();
		Effects.setUp();
		setUpEntities();
		setUpTimer();
		
		gameLoop();
		
		Sound.destroy();
		Display.destroy();
		System.exit(0);
	}
	
	private void gameLoop() {
		while (isRunnig) {
			input();

			glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			switch (control.state) {
			case MAIN:
				backgroundMain.drawMain(getTime());
				break;
			case GAMEOVER:
				render();
				control.writeGameOver();
				break;
			case END_STAGE:
				background.draw();
				player.draw(getTime());
				control.drawScore(getTime());
				control.waitEndGameStage(getTime());
				if (Effects.listExplosion != null)
					Effects.drawEffect(ObjectType.EXPLOSION, getTime());
				break;
			case NEXT_STAGE:
				control.setNewStage(getTime());
				aliens.createAliens(setStage.getStage(control.getNumStage()));
				Effects.clear();
				render();
				control.state = StateType.GAME;
				break;
			case EXIT:
				backgroundExit.draw();
				if (!control.waitExit(getTime()))
					isRunnig = false;
				break;
			case CONGRATULATIONS:
				backgroundMain.draw();
				control.writeNextLevel();
				break;
			default:
				render();
				logic(getDelta());
				break;
			}
			
			Display.update();
			Display.sync(150);
			
			if (Display.isCloseRequested()) {
				control.state = StateType.EXIT;
			}
		}
	}
	
	private void setUpDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Space Invaders 2D");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Sound.destroy();
			Display.destroy();
			System.exit(1);
		}
	}

	private void setUpOpenGL() {
		//initialization OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND); 
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private void setUpEntities() {
		player = new Player(WIDTH/2, HEIGHT-42, 32, 42, .2f, ObjectType.PLAYER);
		control = new Control(getTime());
		setStage = new SetStage();
		aliens = new Aliens();
		background = new Background(BackgroundType.GAME);
		backgroundMain = new Background(BackgroundType.MAIN);
		backgroundExit = new Background(BackgroundType.EXIT);
	}
	
	private void render() {
		glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		background.draw();
		player.draw(getTime());
		aliens.draw(getTime());
		control.drawScore(getTime());
		
		if (Effects.listExplosion != null)
			Effects.drawEffect(ObjectType.EXPLOSION, getTime());
	}
	
	private void input() {
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        switch (Keyboard.getEventKey()) {
		             case Keyboard.KEY_SPACE:
		            	 if (control.state.equals(StateType.GAME)) {
			            	 player.launchBomb();
			            	 control.increaseRockets();
		            	 }
		            	 break;
			         case Keyboard.KEY_RETURN:
		             case Keyboard.KEY_Y:
		            	 if (control.state.equals(StateType.MAIN) 
		            			 ||control.state.equals(StateType.GAMEOVER)) {
			     			 control.initialize(getTime());
	   		     			 aliens.createAliens(setStage.getStage(control.getNumStage()));
	   		     			 control.state = StateType.GAME;
		            	 }
		            	 break;
		             case Keyboard.KEY_ESCAPE:
		             case Keyboard.KEY_N:
		            	 if (control.state.equals(StateType.GAMEOVER))
		            		 control.state = StateType.EXIT;
		            	 else
			            	 control.state = StateType.EXIT;
		            	 break;
		        }
		    }
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			player.moveLeft();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			player.moveRight();
		} else {
			player.standBy();
		}
	}

	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private int getDelta() {
		long currentTime = getTime();
		int delta = (int)(currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}

	private void setUpTimer() {
		lastFrame = getTime();
	}

	private void logic(int delta) {
		
		player.updateTime(delta);
		aliens.updateTime(delta, lastFrame);
		
		//Verify Collision
		//Rocket x Enemy
		if (aliens.collison(player, CollisionType.ROCKET_X_ENEMY)) {
			control.increasePoints();
		}

		//Bomb x Player
		if (aliens.collison(player, CollisionType.BOMB_X_PLAYER)) {
			control.decreaseLives();
			Effects.createExplosion(player.getX() - player.getWidth()/2, player.getY() - player.getHeight(), 64, 64);
		}

		//Enemy x Player
		if (aliens.collison(player, CollisionType.ENEMY_X_PLAYER)) {
			control.increasePoints();
			control.decreaseLives();
		}
		
		//Next stage
		if (aliens.getTotalNumberOfAliens() == 0) {
			control.state = StateType.END_STAGE;
			
			//End of the stages.. next level
			if (control.getNumStage() == 10) {
				control.setGameOver(getTime());
				control.state = StateType.CONGRATULATIONS;
			}
		}
		
		// Game Over
		if ((aliens.getLowestEnemy() >= HEIGHT - IMAGE_HEIGHT) || (control.getNumLives() == 0)) {
			control.setGameOver(getTime());
			Effects.clear();
		}
	}
	
	public static void main(String[] args) {
		
		new InvadersMain();
	}
}
