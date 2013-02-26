package objects;

import entities.ObjectType;
import static entities.WorldVariables.WIDTH;

public class Player extends AbstractFigther {

	public Player(double x, double y, double width, double height, float speed, ObjectType type) {
		super(x, y, width, height, speed, type);
		
		bomb = new Bomb(16, 32, .7f, ObjectType.ROCKET);
	}
	
	public void updateTime(int delta) {
		super.update(delta);
		
		if (x < 0) 
			x = 0;
		else if (x + width > WIDTH)
			x = WIDTH - width; //WIDTH (tela) - width(objeto)
		
		if (isBombLaunched()) {
			bomb.update(delta);
			
			if (bomb.getY() <= 0)
				bomb.reload();
		}
	}

	public void launchBomb() {
		if (!isBombLaunched()) {
			bomb.launch(x, y - height/2);
		}
	}
}
