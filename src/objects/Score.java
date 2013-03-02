package objects;

import java.awt.Font;
import java.text.SimpleDateFormat;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import static entities.WorldVariables.*;

public class Score {
	
	private int numLives;
	private int numPoints;
	private int numRockets;
	private int numStage;
	
	private static final String lives = "Lives: ";
	private static final String points = "Points: ";
	private static final String stage = "Stage: ";
	private static final String rockets = "Rockets: ";
	private static final String time = "Time: ";
	
	private long gameStartTime;
	private long gameOverTime;
	
	private boolean isNewStage;
	private boolean isGameOver;
	private long timeToPrint;
	
	private static UnicodeFont fontText;
	private static UnicodeFont fontNum;
	private static UnicodeFont fontWarn;
	
	private final int gap = 100;

	public Score(long time) {
		initialize(time);
		setUpFonts();
		setNewStage(time);
	}

	public void initialize(long time) {
		numLives = 5;
		numPoints = 0;
		numRockets = 0;
		numStage = 0;
		gameStartTime = time;
		
		isNewStage = false;
		isGameOver = false;
	}

	public void increasePoints() {
		numPoints++;
	}

	public void decreaseLives() {
		numLives--;
	}

	public int getNumLives() {
		return numLives;
	}

	public void nextStage() {
		numStage++;
	}
	
	public int getNumStage() {
		return numStage;
	}

	public void increaseRockets() {
		numRockets++;
	}
		
	public void draw(long newTime) {
		/*
		 * LIVES
		 */
		writeText(10, 0, lives);
		writeNum(10 + 50, 0, String.valueOf(numLives));
		
		/*
		 * POINTS
		 */
		writeText(gap, 0, points);
		writeNum(gap + 60, 0, String.valueOf(numPoints));
		
		/*
		 * ROCKETS
		 */
		writeText(2*gap, 0, rockets);
		writeNum(2*gap + 75, 0, String.valueOf(numRockets));
		
		/*
		 * STAGE
		 */
		writeText(4*gap, 0, stage);
		writeNum(4*gap + 55, 0, String.valueOf(numStage));
		
		/*
		 * TIME
		 */
		writeText(5*gap, 0, time);
		if (isGameOver)
			writeNum(5*gap + 50, 0, getTime(gameOverTime - gameStartTime));
		else
			writeNum(5*gap + 50, 0, getTime(newTime - gameStartTime));
		
		/*
		 * New Stage message
		 */
		if (isNewStage) {
			if (timeToPrint > newTime)
				writeWarn(WIDTH/2 - 50, HEIGHT/2, "Stage " + numStage);
			else 
				isNewStage = false;
		}
	}

	private void writeText(float x, float y, String text) {
		fontText.drawString(x, y, text);
	}

	private void writeNum(float x, float y, String text) {
	    fontNum.drawString(x, y, text);
	}
	
	public void writeWarn(float x, float y, String text) {
		fontWarn.drawString(x, y, text);
	}
	
    @SuppressWarnings("unchecked")
	private static void setUpFonts() {
        Font awtFontText = new Font("Verdana", Font.BOLD, 14);
        fontText = new UnicodeFont(awtFontText);
        fontText.getEffects().add(new ColorEffect(java.awt.Color.yellow));
        fontText.addAsciiGlyphs();
        try {
        	fontText.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }

        Font awtFontNum = new Font("Verdana", Font.BOLD, 14);
        fontNum = new UnicodeFont(awtFontNum);
        fontNum.getEffects().add(new ColorEffect(java.awt.Color.white));
        fontNum.addAsciiGlyphs();
        try {
        	fontNum.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }

        Font awtFontWarn = new Font("Verdana", Font.BOLD, 18);
        fontWarn = new UnicodeFont(awtFontWarn);
        fontWarn.getEffects().add(new ColorEffect(java.awt.Color.orange));
        fontWarn.addAsciiGlyphs();
        try {
        	fontWarn.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    private String getTime(long timePlayed) {
    	return (new SimpleDateFormat("mm:ss:SSS")).format(timePlayed);   
    }
    
    public void setNewStage(long time) {
    	isNewStage = true;
    	timeToPrint = time + 2000;
    	nextStage();
    }

    public void setGameOver(long time) {
    	isGameOver = true;
    	gameOverTime = time;
    }

	public boolean isGameOver() {
		return isGameOver;
	}
	
	public void writeGameOver() {
		if (isGameOver) {
			writeWarn(WIDTH/2 - 80, HEIGHT/2 - 50, "GAME OVER!");
			writeText(WIDTH/2 - 170, HEIGHT/2, "Would you like to try again? (Y)es (N)o ");
		}
	}
}
