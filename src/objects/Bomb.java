package objects;

import entities.AbstractMoveableEntity;
import entities.ObjectType;

public class Bomb extends AbstractMoveableEntity {
	
	private boolean isLaunched = false;

	public Bomb(double width, double height, float speed, ObjectType type) {
		super(width, height, speed, type);
	}
	
	public Bomb(double x, double y, double width, double height, float speed, ObjectType type) {
		super(x, y, width, height, speed, type);
		
	}

	@SuppressWarnings("incomplete-switch")
	public void launch(double x, double y) {
		isLaunched = true;
		setLocation(x, y);
		
		switch (type) {
		case ROCKET:
			setDY(-speed);
			break;
		case BOMB:
			setDY(speed);
			break;
		}
	}
	
	public boolean isLaunched() {
		return isLaunched;
	}

	public void reload() {
		isLaunched = false;
		setX(0);
		setY(0);
	}
}
