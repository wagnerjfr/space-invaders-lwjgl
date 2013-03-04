package entities;

public enum ObjectType {
	PLAYER("res/images/nave1.png"), 
	ENEMY1("res/images/alien1.png"), ENEMY2("res/images/alien2.png"), 
	ROCKET("res/images/rocket.png"), BOMB("res/images/bomb.png");
	
	public final String location;
	private ObjectType(String location) {
		this.location = location;
	}
}
