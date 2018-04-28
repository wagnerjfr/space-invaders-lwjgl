package objects;

import java.io.IOException;

import effects.Effects;
import effects.SoundType;
import entities.AbstractMoveableEntity;
import entities.ObjectType;

public class Bomb extends AbstractMoveableEntity {

    private boolean isLaunched = false;

    public Bomb(double x, double y, double width, double height, float speed, ObjectType type) {
        super(x, y, width, height, speed, type);
    }

    @SuppressWarnings("incomplete-switch")
    public void launch(double x, double y) throws IOException {
        isLaunched = true;
        setLocation(x, y);

        switch (type) {
        case ROCKET:
            setDY(-speed);
            Effects.playSound(SoundType.LAUNCH_ROCKET);
            break;
        case BOMB:
            setDY(speed);
            Effects.playSound(SoundType.LAUNCH_BOMB);
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
}
