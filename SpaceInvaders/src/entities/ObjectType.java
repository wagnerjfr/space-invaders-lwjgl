package entities;

public enum ObjectType {
	PLAYER("nave1.png"), 
	ENEMY1("alien1R.png"), ENEMY2("alien2R.png"), 
	ROCKET("rocket.png"), BOMB("bomb.png");
	
	public final String location;
	private ObjectType(String location) {
		this.location = location;
	}
}
