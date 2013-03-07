package entities;

public interface MoveableEntity extends Entity {
	public void update(int delta);
	public double getDX();
	public double getDY();
	public float getSpeed();
	public void setDX(double dX);
	public void setDY(double dY);
	public void setSpeed(float speed);
}
