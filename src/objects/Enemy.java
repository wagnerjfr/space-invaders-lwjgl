package objects;

import entities.ObjectType;
import static entities.WorldVariables.*;

public class Enemy extends AbstractFigther {
	
	private long timeToLaunch = 0;
	private boolean reload = false;
	private boolean isMoving = true;

	public Enemy(double x, double y, double width, double height, float speed, ObjectType type) {
		super(x, y, width, height, speed, type);
		
		//bomb = new Bomb(16, 32, 0.2f, ObjectType.BOMB);
		createBombs(1, x, y, 32, 32, .2f, ObjectType.BOMB);
	}
	
	public void updateTime(int delta, long systime) {
		
		if (isMoving) {

			super.update(delta);
		
			if (x >= WIDTH - width && Aliens.isMovingRight) {
				Aliens.isMovingRight = false;
				Aliens.isMovingDown = true;
				//moveLeft();  //Nao pode j‡ mover pois Ž o ultimo da linha, atualiza no prox. loop
			}
			else if (x <= 0 && !Aliens.isMovingRight) {
				Aliens.isMovingRight = true;
				Aliens.isMovingDown = true;
				moveRight(); //Ja move pois Ž o 1st da fila, o restante Ž atualizada em seguida
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
//		System.out.println(rand);
	}
	
	private void launchBomb(long systime) {
		listBomb.get(0).launch(x, y + height/2);
	}
	
	void hit() {
		isMoving = false;
		setDX(0);
		setDY(0);
		setLocation(WIDTH, HEIGHT);
	}
}
