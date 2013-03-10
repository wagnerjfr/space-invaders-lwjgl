package state;

import static entities.WorldVariables.HEIGHT;
import static entities.WorldVariables.WIDTH;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import effects.Effects;
import effects.SoundType;

import state.Printer.PrinterType;

public class Background {

	private Texture figure;
	private BackgroundType type;
	
	private final double VELOCITY = 0.05;
	private double figure_x = 0;
	private boolean moveRight = true;
	
	private long timeToShow = 0;
	private long timeToBlank = 0;
	
	private boolean bPlaySound = false;
	
	public Background(BackgroundType type) {
		this.type = type;
		setFigure();
	}
	
	private void setFigure() {
		try {
			this.figure = TextureLoader.getTexture("PNG", new FileInputStream(new File(type.location)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw() {
		figure.bind();
		
		if (type.equals(BackgroundType.GAME)) {
			if ((moveRight) && (figure_x > -150)) {
				figure_x = figure_x - VELOCITY;
			}
			else if (figure_x < 0) {
				moveRight = false;
				figure_x = figure_x + VELOCITY;
			}
			else if (figure_x >= 0) {
				moveRight = true;
			}
		}
		else
			figure_x = 0;
		
		glTranslated(figure_x, 0, 0);
		glBegin(GL_QUADS);
		glTexCoord2d(0, 0);
		glVertex2d(0, 0);
		glTexCoord2d(1, 0);
		glVertex2d(figure.getImageWidth(), 0);
		glTexCoord2d(1, 1);
		glVertex2d(figure.getImageWidth(), figure.getImageHeight()+70);
		glTexCoord2d(0, 1);
		glVertex2d(0, figure.getImageHeight()+70);
		glEnd();
	}
	
	public void drawMain(long newTime) {
		final int RATE_SHOW = 500;
		final int RATE_BLANK = 250;

		int gap = 15;
		
		if (!bPlaySound) {
			Effects.playSound(SoundType.EXPLOSION_BOMB);
			bPlaySound = true;
		}
		
		glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		draw();

		Printer.write(WIDTH/2 - 170, HEIGHT/2 - 100, "UFO Invasion", PrinterType.TITLE);
		Printer.write(WIDTH/2 - 170, HEIGHT/2 + 2*gap, "Arrow LEFT to go left", PrinterType.WHITE);
		Printer.write(WIDTH/2 - 170, HEIGHT/2 + 3*gap, "Arrow RIGHT to go right", PrinterType.WHITE);
		Printer.write(WIDTH/2 - 170, HEIGHT/2 + 4*gap, "SPACE to fire", PrinterType.WHITE);
		Printer.write(WIDTH/2 - 170, HEIGHT/2 + 5*gap, "ESC to quit", PrinterType.WHITE);
		
		if (timeToShow == 0 && timeToBlank == 0) {
			timeToShow = newTime + RATE_SHOW;
			timeToBlank = timeToShow + RATE_BLANK;
		}

		if (timeToShow > newTime)
			Printer.write(WIDTH/2 - 170, HEIGHT/2 + 7*gap, "Press ENTER to play!", PrinterType.YELLOW);
		else {
			timeToShow = 0;
			
			if (timeToBlank < newTime)
				timeToBlank = 0;
		}
	}
	
	public enum BackgroundType {
		MAIN("res/images/main.png"), GAME("res/images/fundo.png"), EXIT("res/images/exit.png");
		
		public String location;
		private BackgroundType(String location) {
			this.location = location;
		}
	}
}
