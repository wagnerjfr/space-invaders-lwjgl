package objects;

import entities.AbstractMoveableEntity;
import entities.ObjectType;

public class Bomb extends AbstractMoveableEntity {
	
	private boolean isLaunched = false;
	
	private SoundManager fireSound, explosionSound;

	@SuppressWarnings("incomplete-switch")
	public Bomb(double x, double y, double width, double height, float speed, ObjectType type) {
		super(x, y, width, height, speed, type);
		
		switch (type) {
		case ROCKET:
			fireSound = new SoundManager(SoundType.LAUNCH_ROCKET);
			explosionSound = new SoundManager(SoundType.EXPLOSION_ROCKET);
			break;
		case BOMB:
			explosionSound = new SoundManager(SoundType.EXPLOSION_BOMB);
			break;
		}
	}

	@SuppressWarnings("incomplete-switch")
	public void launch(double x, double y) {
		isLaunched = true;
		setLocation(x, y);
		
		switch (type) {
		case ROCKET:
			setDY(-speed);
			fireSound.play();
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
		setX(-10);
		setY(-10);
	}
	
	public void playExplosion() {
		explosionSound.play();
	}
}
