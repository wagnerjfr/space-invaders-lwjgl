package objects;

import effects.Effects;
import entities.ObjectType;
import static entities.WorldVariables.*;

public class Enemy extends AbstractFigther {
	
	private long timeToLaunch = 0;
	private boolean reload = false;
	private boolean isMoving = true;
	private int numOfHits; 

	public Enemy(double x, double y, double width, double height, float speed, ObjectType type, float bombSpeed, int hits) {
		super(x, y, width, height, speed, type);
		shift_rate = 100;
		
		createBombs(1, x, y, 10, 20, bombSpeed, ObjectType.BOMB);
		
		numOfHits = hits;
	}
	
	@Override
	public void draw(long newTime) {
		super.draw(newTime);
		
		Printer.writeHits((float)x + 12, (float)y - 15, String.valueOf(numOfHits));
	}
	
	public void updateTime(int delta, long systime) {
		
		if (isMoving) {

			super.update(delta);
		
			if (x >= WIDTH - width && Aliens.isMovingRight) {
				Aliens.isMovingRight = false;
				Aliens.isMovingDown = true;
				//moveLeft();  //Nao pode mover pois eh o ultimo da linha, atualiza no prox. loop
			}
			else if (x <= 0 && !Aliens.isMovingRight) {
				Aliens.isMovingRight = true;
				Aliens.isMovingDown = true;
				moveRight(); //Ja pode mover pois eh o 1st da fila, o restante eh atualizada em seguida
			}
			else if (Aliens.isMovingRight)
				moveRight();
			else 
				moveLeft();
			
			//Cria tempo para lancar a bomba
			if (!listBomb.get(0).isLaunched() && !reload) {
				createTimeBombLaunch(systime);
				reload = true;
			}
		}
		
		//Logica para o lancamento das bombas
		updateBomb(delta, ObjectType.BOMB);
		
		if ((timeToLaunch < systime) && reload) {
			launchBomb(systime);
			reload = false;
		}
	}

	private void createTimeBombLaunch(long systime) {
		long rand = (long) (Math.random() * 10); //[0 a 9]
		timeToLaunch = systime + rand * 1000;
	}
	
	private void launchBomb(long systime) {
		listBomb.get(0).launch(x + 10, y + height/2);
	}
	
	void hit() {
		numOfHits--;
		if (numOfHits == 0) {
			Effects.createExplosion(x, y-32, 100, 100);
			isMoving = false;
			setDX(0);
			setDY(0);
			setLocation(WIDTH, HEIGHT);
		}
	}
	
	public boolean isMoving() {
		return isMoving;
	}
}
