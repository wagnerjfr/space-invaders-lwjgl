package entities;

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.awt.Rectangle;

public abstract class AbstractEntity implements Entity {
	
	protected double x, y, width, height;
	protected Rectangle hitbox = new Rectangle();
	protected ObjectType type;
	private static Sprite sprite;
	
	public AbstractEntity() {}
	
	public AbstractEntity(double x, double y, double width, double height, ObjectType type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
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
        glEnable(GL_TEXTURE_RECTANGLE_ARB);
        glEnable(GL_CULL_FACE);
        //glCullFace(GL_BACK);
        
        sprite = WorldVariables.spriteMap.get(type.location);

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, WorldVariables.spritesheet);
		
		int sprite_x = sprite.getX();
        int sprite_y = sprite.getY();
        int sprite_x2 = sprite.getX() + sprite.getWidth();
        int sprite_y2 = sprite.getY() + sprite.getHeight();

        glLoadIdentity();
        glTranslated(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(sprite_x, sprite_y);
        glVertex2d(0, 0);
        glTexCoord2f(sprite_x, sprite_y2);
        glVertex2d(0, height);
        glTexCoord2f(sprite_x2, sprite_y2);
        glVertex2d(width, height);
        glTexCoord2f(sprite_x2, sprite_y);
        glVertex2d(width, 0);
        glEnd();
        glLoadIdentity();

        glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
        
        glDisable(GL_TEXTURE_RECTANGLE_ARB);
        glDisable(GL_CULL_FACE);
	}
}
