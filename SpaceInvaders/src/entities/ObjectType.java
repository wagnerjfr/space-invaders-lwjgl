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
	ROCKET("rocket.png"), 
	BOMB("bomb.png"),
	EXPLOSION("0,1,2,3,4,5,6,7");
	
	public final String location;
	
	private ObjectType(String location) {
		this.location = location;
	}
}
