package entities;

public enum ObjectType {
	PLAYER("res/player.png"), ENEMY1("res/enemy1.png"), ENEMY2("res/enemy2.png"), 
	ROCKET("res/rocket.png"), BOMB("res/bomb.png");
	
	public final String location;
	private ObjectType(String location) {
		this.location = location;
	}
}
