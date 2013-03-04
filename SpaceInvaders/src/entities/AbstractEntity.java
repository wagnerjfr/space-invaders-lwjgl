package entities;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractEntity implements Entity {
	
	protected double x, y, width, height;
	protected Rectangle hitbox = new Rectangle();
	protected ObjectType type;
	protected Texture texture = null;
	
	public AbstractEntity() {}
	
	public AbstractEntity(double width, double height, ObjectType type) {
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;
		this.type = type;
		setTexture();
	}
	
	public AbstractEntity(double x, double y, double width, double height, ObjectType type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		setTexture();
	}
	
	private void setTexture() {
		try {
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(type.location)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public boolean intersects(Entity entity) {
		hitbox.setBounds((int) x, (int) y, (int) width, (int) height);
		return hitbox.intersects(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
	}

	@Override
	public void setObjectType(ObjectType type) {
		this.type = type;
	}

	@Override
	public ObjectType getObjectType() {
		return type;
	}
	
	@Override
	public void draw() {
		texture.bind();
		glLoadIdentity();
		glTranslated(x, y, 0);
		glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2d(0, 0);
			glTexCoord2d(1, 0);
			glVertex2d(width, 0);
			glTexCoord2d(1, 1);
			glVertex2d(width, height);
			glTexCoord2d(0, 1);
			glVertex2d(0, height);
		glEnd();
		glLoadIdentity();
	}
}
