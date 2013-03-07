package effects;

public enum SoundType {
	LAUNCH_ROCKET("res/sounds/colt45.wav"), EXPLOSION_ROCKET("res/sounds/bomb-02.wav"),
	EXPLOSION_BOMB("res/sounds/alarm.wav");
	
	public final String location;
	private SoundType(String location) {
		this.location = location;
	}
}
