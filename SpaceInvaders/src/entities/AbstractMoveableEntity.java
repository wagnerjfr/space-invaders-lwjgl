package entities;

public abstract class AbstractMoveableEntity extends AbstractEntity implements MoveableEntity {

    protected double dX, dY;
    protected float speed;

    public AbstractMoveableEntity(double x, double y, double width, double height, float speed, ObjectType type) {
        super(x, y, width, height, type);
        this.dX = 0;
        this.dY = 0;
        this.speed = speed;
    }

    @Override
    public void update(int delta) {
        this.x += delta * dX;
        this.y += delta * dY;
    }

    @Override
    public double getDX() {
        return dX;
    }

    @Override
    public double getDY() {
        return dY;
    }

    @Override
    public void setDX(double dX) {
        this.dX = dX;
    }

    @Override
    public void setDY(double dY) {
        this.dY = dY;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
