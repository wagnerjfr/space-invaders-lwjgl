package entities;

public enum ObjectType {
	PLAYER("res/images/player.png"), ENEMY1("res/images/enemy1.png"), ENEMY2("res/images/enemy2.png"), 
	ROCKET("res/images/rocket.png"), BOMB("res/images/bomb.png");
	
	public final String location;
	private ObjectType(String location) {
		this.location = location;
	}
}
