package objects;

import static entities.WorldVariables.HEIGHT;
import static entities.WorldVariables.WIDTH;

import java.io.IOException;

import state.Printer;
import state.Printer.PrinterType;
import effects.Effects;
import entities.ObjectType;

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

        Printer.write((float)x + 12, (float)y - 15, String.valueOf(numOfHits), PrinterType.HITS);
    }

    public void updateTime(int delta, long systime) throws IOException {

        if (isMoving) {

            super.update(delta);

            if (x >= WIDTH - width && Aliens.isMovingRight) {
                Aliens.isMovingRight = false;
                Aliens.isMovingDown = true;
                //moveLeft();  //can't move because it's the last of the row, update in the next loop
            }
            else if (x <= 0 && !Aliens.isMovingRight) {
                Aliens.isMovingRight = true;
                Aliens.isMovingDown = true;
                moveRight();
            }
            else if (Aliens.isMovingRight)
                moveRight();
            else 
                moveLeft();

            //Starts clock to bomb launch
            if (!listBomb.get(0).isLaunched() && !reload) {
                createTimeBombLaunch(systime);
                reload = true;
            }
        }

        //Logic to bomb launch
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

    private void launchBomb(long systime) throws IOException {
        listBomb.get(0).launch(x + 10, y + height/2);
    }

    void hit() {
        numOfHits--;
        if (numOfHits == 0) {
            Effects.createExplosion(x-width, y-height, 100, 100);
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
