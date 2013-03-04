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
	private double figure_y = 0;
	
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
		
		if (figure_y > -110)
			figure_y = figure_y - 0.05;
		
		glTranslated(0, figure_y, 0);
		glBegin(GL_QUADS);
		glTexCoord2d(0, 0);
		glVertex2d(0, 0);
		glTexCoord2d(1, 0);
		glVertex2d(figure.getImageWidth(), 0);
		glTexCoord2d(1, 1);
		glVertex2d(figure.getImageWidth(), figure.getImageHeight() + 350);
		glTexCoord2d(0, 1);
		glVertex2d(0, figure.getImageHeight() + 350);
		glEnd();
	}

	public void resetY() {
		figure_y = 0;
	}
}
