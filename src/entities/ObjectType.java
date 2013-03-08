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
	EXPLOSION("explosion1.png,explosion2.png,explosion3.png,explosion4.png,explosion5.png,explosion6.png,explosion7.png,explosion8.png,explosion9.png,explosion10.png," +
			"explosion11.png,explosion12.png,explosion13.png,explosion14.png,explosion15.png,explosion16.png,explosion17.png,explosion18.png,explosion19.png,explosion20.png," +
			"explosion21.png,explosion22.png,explosion23.png,explosion24.png,explosion25.png,explosion26.png,explosion27.png,explosion28.png,explosion29.png,explosion30.png," +
			"explosion31.png,explosion32.png,explosion33.png,explosion34.png,explosion35.png,explosion36.png,explosion37.png,explosion38.png,explosion39.png,explosion40.png," +
			"explosion41.png,explosion42.png,explosion43.png,explosion44.png,explosion45.png");
	
	public final String location;
	
	private ObjectType(String location) {
		this.location = location;
	}
}
