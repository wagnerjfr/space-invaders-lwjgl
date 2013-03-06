package entities;


public enum ObjectType {
	
	/*
	 * OBS: location with no white spaces!
	 */
	
	PLAYER("nave1.png"), 
	ENEMY1("alien1R.png,alien1L.png"), 
	ENEMY2("alien2R.png,alien2L.png"), 
	ROCKET("rocket.png"), 
	BOMB("bomb.png");
	
	public final String location;
	
	private ObjectType(String location) {
		this.location = location;
	}
}
