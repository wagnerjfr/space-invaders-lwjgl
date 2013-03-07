package effects;

import java.util.ArrayList;

import entities.ObjectType;
import entities.WorldVariables;

public class Effects {

	public static ArrayList<Explosion> listExplosion;
	public static ArrayList<SoundManager> listSound;
	
	public static void setUp() {
		SoundManager.create();
		WorldVariables.setUpSpriteSheet();
		
		listExplosion = new ArrayList<Explosion>();
		listSound = new ArrayList<SoundManager>();
	}
	
	public static void createExplosion(double x, double y, double width, double height) {
		listExplosion.add(new Explosion(x, y, width, height, ObjectType.EXPLOSION));
	}
	
	public static void playSound(SoundType type) {
		SoundManager sound = new SoundManager(type);
		listSound.add(sound);
		sound.play();
	}

	public static void drawEffect(ObjectType type, long newTime) {
		
		for (Explosion explosion : listExplosion) {
			explosion.draw(newTime);
		}
	}
	
	public static void clear() {
		listExplosion.clear();
		listSound.clear();
	}
}
