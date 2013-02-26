package objects;

import entities.AbstractMoveableEntity;
import entities.Entity;
import entities.ObjectType;

public abstract class AbstractFigther extends AbstractMoveableEntity {
	
	protected Bomb bomb;

	public AbstractFigther(double x, double y, double width, double height, float speed, ObjectType type) {
		super(x, y, width, height, speed, type);
	}

	@Override
	public void draw() {
		super.draw();
		if (isBombLaunched())
			bomb.draw();
	}
		
	public void moveRight() {
		setDX(speed);
	}
	
	public void moveLeft() {
		setDX(-speed);
	}
	
	public void standBy() {
		setDX(0);
	}
	
	public boolean bombIntersects(Entity entity) {
		boolean intersect = false;
		
		if (bomb.intersects(entity)) {
			bomb.reload();
			intersect = true;
		}
		
		return intersect;
	}
	
	public boolean isBombLaunched() {
		return bomb.isLaunched();
	}
}
