package effects;

import entities.AbstractEntity;
import entities.ObjectType;

public class Explosion extends AbstractEntity {
	
	public Explosion(double x, double y, double width, double height, ObjectType type) {
		super(x, y, width, height, type);
		
		shift_rate = 20;
		max_shift_times = 1;
	}
	
	@Override
	public void draw(long newTime) {
		if ((max_shift_times == -1) || (max_shift_times > 0 && max_shift_times > shift_times))
			super.draw(newTime);
	}
}
