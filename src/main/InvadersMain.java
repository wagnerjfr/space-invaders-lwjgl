package main;

import objects.Aliens;
import objects.CollisionType;
import objects.Enemy;
import objects.Player;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

import entities.ObjectType;
import static org.lwjgl.opengl.GL11.*;
import static entities.WorldVariables.*;

public class InvadersMain {
		
	private boolean isRunnig = true;
	private long lastFrame;
	
	private Player player;
	private Aliens aliens;
	
	public InvadersMain() {
		setUpDisplay();
		setUpOpenGL();
		setUpEntities();
		setUpTimer();
		while (isRunnig) {
			render();
			input();
			logic(getDelta());
			Display.update();
			Display.sync(100);
			
			if (Display.isCloseRequested()) {
				isRunnig = false;
			}
		}
		Display.destroy();
		System.exit(0);
	}
	
	private void setUpDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Space Invaders 2D");
			//Display.setInitialBackground(.5f, .5f, .5f);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
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
/*		bat = new Bat(10, 200, 32, 32, ObjectType.PLAYER);
		ball = new Ball(200, 200, 32, 32, ObjectType.ENEMY1);
		ball.setDX(-0.1);*/
		player = new Player(320, 480-32, 32, 32, .2f, ObjectType.PLAYER);
		aliens = new Aliens();
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		player.draw();
		aliens.draw();
	}
	
	private void input() {
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        switch (Keyboard.getEventKey()) {
		             case Keyboard.KEY_SPACE:
		            	 player.launchBomb();
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
			
		}

		//Bomb x Player OR Enemy x Player
		if (aliens.collison(player, CollisionType.BOMB_X_PLAYER)) {
		}

		//Enemy x Player
		if (aliens.collison(player, CollisionType.ENEMY_X_PLAYER)) {
		}
		
		if (aliens.getTotalNumberOfAliens() == 0) {
			aliens.createAliens(2);
		}
	}

	public static void main(String[] args) {
		
		new InvadersMain();
	}
}
