package objects;

import entities.ObjectType;
import static entities.WorldVariables.WIDTH;

public class Player extends AbstractFigther {

	public Player(double x, double y, double width, double height, float speed, ObjectType type) {
		super(x, y, width, height, speed, type);
		
		createBombs(3, x, y, 5, 25, .7f, ObjectType.ROCKET);
	}
	
	public void updateTime(int delta) {
		super.update(delta);
		
		if (x < 0) 
			x = 0;
		else if (x + width > WIDTH)
			x = WIDTH - width; //WIDTH (tela) - width(objeto)
		
		updateBomb(delta, ObjectType.ROCKET);
	}

	public void launchBomb() {
		int i;
		
		if ((i = getDispoBomb()) != -1) {
			Bomb bomb = listBomb.get(i);
			bomb.launch(x + 10, y - height/2);
		}
	}
	
	/**
	 * Verify disponibility to launch new Rocket
	 * @return
	 */
	private int getDispoBomb() {
		int num = -1;
		for (int i = 0; i < listBomb.size(); i++) {
			Bomb bomb = listBomb.get(i);
			
			if (!bomb.isLaunched()) {
				num = i;
				break;
			}
		}
		return num;	
	}
}