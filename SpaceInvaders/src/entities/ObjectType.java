package entities;

public enum ObjectType {
	PLAYER("res/images/nave.png"), 
	ENEMY1("res/images/alien.png"), ENEMY2("res/images/alien2.png"), 
	ENEMY3("res/images/alien3.png"), ENEMY4("res/images/alien4.png"), 
	ENEMY5("res/images/alien5.png"), 
	ROCKET("res/images/rocket.png"), BOMB("res/images/bomb.png");
	
	public final String location;
	private ObjectType(String location) {
		this.location = location;
	}
}
