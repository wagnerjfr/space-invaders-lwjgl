package objects;

import java.util.ArrayList;

import entities.AbstractMoveableEntity;
import entities.Entity;
import entities.ObjectType;
import static entities.WorldVariables.HEIGHT;

public abstract class AbstractFigther extends AbstractMoveableEntity {
	
	protected ArrayList<Bomb> listBomb;
	protected int numberOfBombs;

	public AbstractFigther(double x, double y, double width, double height, float speed, ObjectType type) {
		super(x, y, width, height, speed, type);
	}

	@Override
	public void draw(long newTime) {
		super.draw(newTime);

		for (Bomb bomb : listBomb)
			if (bomb.isLaunched())
				bomb.draw(newTime);
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
		
		for (Bomb bomb : listBomb)
			if (bomb.intersects(entity)) {
				bomb.reload();
				intersect = true;
			}
		
		return intersect;
	}
	
	public void createBombs(int number, double x, double y, double width, double height, float speed, ObjectType type) {
		numberOfBombs = number;
		
		listBomb = new ArrayList<Bomb>(numberOfBombs);
		
		for (int i = 0; i < numberOfBombs; i++) {
			Bomb bomb = new Bomb(x, y, width, height, speed, type);
			listBomb.add(bomb);
		}
	}
	
	@SuppressWarnings("incomplete-switch")
	public void updateBomb(int delta, ObjectType type) {
		for (Bomb bomb : listBomb) {
			if (bomb.isLaunched()) {
				bomb.update(delta);
				
				switch (type) {
				case ROCKET:
					if (bomb.getY() <= 0)
						bomb.reload();
					break;
				case BOMB:
					if (bomb.getY() >= HEIGHT)
						bomb.reload();
					break;
				}
			}
		}
	}
}
