package objects;

import java.awt.Font;
import java.text.SimpleDateFormat;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;


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
	
	private static UnicodeFont font;
	private static UnicodeFont fontNum;
	private final int gap = 100;

	public Score(long time) {
		initialize(time);
		setUpFonts();
	}

	public void initialize(long time) {
		numLives = 5;
		numPoints = 0;
		numRockets = 0;
		numStage = 0;
		gameStartTime = time;
	}

	public void increasePoints() {
		numPoints++;
	}

	public void decreaseLives() {
		numLives--;
	}

	public int nextStage() {
		numStage++;
		return numStage;
	}

	public void increaseRockets() {
		numRockets++;
	}
		
	public void draw(long newTime) {
		writeText(10, 0, lives);
		writeNum(10 + 50, 0, String.valueOf(numLives));
		
		writeText(gap, 0, points);
		writeNum(gap + 60, 0, String.valueOf(numPoints));
		
		writeText(2*gap, 0, rockets);
		writeNum(2*gap + 75, 0, String.valueOf(numRockets));
		
		writeText(4*gap, 0, stage);
		writeNum(4*gap + 55, 0, String.valueOf(numStage));
		
		writeText(5*gap, 0, time);
		writeNum(5*gap + 50, 0, getTime(newTime - gameStartTime));
	}

	private void writeText(float x, float y, String text) {
	    font.drawString(x, y, text);
	}

	private void writeNum(float x, float y, String text) {
	    fontNum.drawString(x, y, text);
	}
	
    @SuppressWarnings("unchecked")
	private static void setUpFonts() {
        Font awtFont = new Font("Verdana", Font.BOLD, 14);
        font = new UnicodeFont(awtFont);
        font.getEffects().add(new ColorEffect(java.awt.Color.yellow));
        font.addAsciiGlyphs();
        try {
            font.loadGlyphs();
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
    }
    
    private String getTime(long timePlayed) {
    	return (new SimpleDateFormat("mm:ss:SSS")).format(timePlayed);   
    }
}
