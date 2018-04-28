package effects;

import java.io.IOException;
import java.util.ArrayList;

import entities.ObjectType;
import entities.WorldVariables;

public class Effects {

    public static ArrayList<Explosion> listExplosion;
    public static ArrayList<Sound> listSound;
    
    public static void setUp() throws IOException {
        WorldVariables.setUpSpriteSheet();
        listExplosion = new ArrayList<Explosion>();
        listSound = new ArrayList<Sound>();
    }

    public static void createExplosion(double x, double y, double width, double height) {
        listExplosion.add(new Explosion(x, y, width, height, ObjectType.EXPLOSION));
    }

    public static void playSound(SoundType type) throws IOException {
        Sound sound = new Sound(type);
        listSound.add(sound);
        sound.play(type);
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
