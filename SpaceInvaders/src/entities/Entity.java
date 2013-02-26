package entities;

public interface Entity {
	public void draw();
	public void update(int delta);
	public void setLocation(double x, double y);
	public void setX(double x);
	public void setY(double y);
	public void setWidth(double width);
	public void setHeight(double height);
	public void setObjectType(ObjectType type);
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
	public ObjectType getObjectType();
	public boolean intersects(Entity entity);
	
}
