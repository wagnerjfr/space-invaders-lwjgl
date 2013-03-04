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
import objects.Background;
import objects.CollisionType;
import objects.Player;
import objects.Score;
import objects.SoundManager;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import entities.ObjectType;

public class InvadersMain {
		
	private boolean isRunnig = true;
	private long lastFrame;
	
	private Player player;
	private Aliens aliens;
	private Score score;
	private Background background;
	
	public InvadersMain() {
		setUpDisplay();
		setUpOpenGL();
		SoundManager.create();
		setUpEntities();
		setUpTimer();
		while (isRunnig) {
			render();
			input();
			if (score.isGameOver()) {
				score.writeGameOver();
			}
			else {
				logic(getDelta());
			}
			Display.update();
			Display.sync(250);
			
			if (Display.isCloseRequested()) {
				isRunnig = false;
			}
		}
		SoundManager.destroy();
		Display.destroy();
		System.exit(0);
	}
	
	private void setUpDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Space Invaders 2D");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			SoundManager.destroy();
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
		player = new Player(WIDTH/2, HEIGHT-42, 32, 32, .2f, ObjectType.PLAYER);
		score = new Score(getTime());
		aliens = new Aliens(score.getNumStage());
		background = new Background();
	}
	
	private void render() {
		glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		background.draw();
		player.draw();
		aliens.draw();
		score.draw(getTime());
	}
	
	private void input() {
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        switch (Keyboard.getEventKey()) {
		             case Keyboard.KEY_SPACE:
		            	 if (!score.isGameOver()) {
			            	 player.launchBomb();
			            	 score.increaseRockets();
		            	 }
		            	 break;
		             case Keyboard.KEY_Y:
		     			 score.initialize(getTime());
   		     			 aliens.createAliens(score.getNumStage());
		            	 break;
		             case Keyboard.KEY_N:
		            	 isRunnig = false;
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
			score.increasePoints();
		}

		//Bomb x Player
		if (aliens.collison(player, CollisionType.BOMB_X_PLAYER)) {
			score.decreaseLives();
			score.addWarnHit(player.getX(), player.getY() - 20, "Hit!!", getTime());
		}

		//Enemy x Player
		if (aliens.collison(player, CollisionType.ENEMY_X_PLAYER)) {
			score.increasePoints();
			score.decreaseLives();
		}
		
		//Next stage
		if (aliens.getTotalNumberOfAliens() == 0) {
			score.setNewStage(getTime());
			aliens.createAliens(score.getNumStage());
			background.resetY();
		}
		
		// Game Over
		if ((aliens.getLowestEnemy() >= HEIGHT - IMAGE_HEIGHT) || (score.getNumLives() == 0)) {
			score.setGameOver(getTime());
		}
	}
	
	public static void main(String[] args) {
		
		new InvadersMain();
	}
}
