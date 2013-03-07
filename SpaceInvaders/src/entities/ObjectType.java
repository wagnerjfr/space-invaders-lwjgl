package entities;


public enum ObjectType {
	
	/*
	 * OBS: location with no white spaces!
	 */
	
	PLAYER("nave1.png,nave2.png"), 
	ENEMY1("alien1R.png,alien1L.png"), 
	ENEMY2("alien2R.png,alien2L.png"), 
	ENEMY3("alien3R.png,alien3L.png"), 
	ENEMY4("alien4R.png,alien4L.png"), 
	ENEMY5("alien5R.png,alien5L.png"), 
	ENEMY6("alien6R.png,alien6L.png"), 
	ROCKET("rocket.png"), 
	BOMB("bomb.png"),
	EXPLOSION("explosion1.png,explosion2.png,explosion3.png,explosion4.png,explosion5.png,explosion6.png,explosion7.png,explosion8.png");
	
	public final String location;
	
	private ObjectType(String location) {
		this.location = location;
	}
}
