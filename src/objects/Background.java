package objects;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Background {

	private Texture figure;
	private final double VELOCITY = 0.05;
	private double figure_x = 0;
	private boolean moveRight = true;
	
	public Background() {
		setFigure();
	}
	
	private void setFigure() {
		try {
			this.figure = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/images/fundo.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw() {
		figure.bind();
		
		if ((moveRight) && (figure_x > -160)) {
			figure_x = figure_x - VELOCITY;
		}
		else if (figure_x < 0) {
			moveRight = false;
			figure_x = figure_x + VELOCITY;
		}
		else if (figure_x == 0) {
			moveRight = true;
		}
		
		glTranslated(figure_x, 0, 0);
		glBegin(GL_QUADS);
		glTexCoord2d(0, 0);
		glVertex2d(0, 0);
		glTexCoord2d(1, 0);
		glVertex2d(figure.getImageWidth(), 0);
		glTexCoord2d(1, 1);
		glVertex2d(figure.getImageWidth(), figure.getImageHeight());
		glTexCoord2d(0, 1);
		glVertex2d(0, figure.getImageHeight());
		glEnd();
	}
}
