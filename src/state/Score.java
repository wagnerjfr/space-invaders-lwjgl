package state;

import static entities.WorldVariables.HEIGHT;
import static entities.WorldVariables.WIDTH;

import java.text.SimpleDateFormat;


public class Score {
	
	public StateType state;
	
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
	private long gameEndStageTime;
	private long gameOverTime;
	
	private boolean isNewStage;
	private long timeToPrint;
	
	private final int gap = 100;
	
	private WarnHit warnHit = null;

	public Score(long time) {
		Printer.setUpFonts();
		initialize(time);
	}

	public void initialize(long time) {
		state = StateType.MAIN;
		
		numLives = 5;
		numPoints = 0;
		numRockets = 0;
		numStage = 0;
		gameStartTime = time;
		
		isNewStage = false;
		
		gameEndStageTime = 0;

		setNewStage(time);
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
		Printer.writeText(10, 0, lives);
		Printer.writeNum(10 + 50, 0, String.valueOf(numLives));
		
		/*
		 * POINTS
		 */
		Printer.writeText(gap, 0, points);
		Printer.writeNum(gap + 60, 0, String.valueOf(numPoints));
		
		/*
		 * ROCKETS
		 */
		Printer.writeText(2*gap, 0, rockets);
		Printer.writeNum(2*gap + 75, 0, String.valueOf(numRockets));
		
		/*
		 * STAGE
		 */
		Printer.writeText(4*gap, 0, stage);
		Printer.writeNum(4*gap + 55, 0, String.valueOf(numStage));
		
		/*
		 * TIME
		 */
		Printer.writeText(5*gap, 0, time);
		if (state.equals(StateType.GAMEOVER))
			Printer.writeNum(5*gap + 50, 0, getTime(gameOverTime - gameStartTime));
		else
			Printer.writeNum(5*gap + 50, 0, getTime(newTime - gameStartTime));
		
		/*
		 * New Stage message
		 */
		if (isNewStage) {
			if (timeToPrint > newTime)
				Printer.writeWarn(WIDTH/2 - 50, HEIGHT/2, "Stage " + numStage);
			else 
				isNewStage = false;
				//state = StateType.GAME;
		}
		
		if (warnHit != null) {
			warnHit.print(newTime);
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
    	state = StateType.GAMEOVER;
    	gameOverTime = time;
    }

	public void writeGameOver() {
		if (state.equals(StateType.GAMEOVER)) {
			Printer.writeWarn(WIDTH/2 - 80, HEIGHT/2 - 50, "GAME OVER!");
			Printer.writeText(WIDTH/2 - 170, HEIGHT/2, "Would you like to try again? (Y)es (N)o ");
		}
	}
	
	public void writeEndGameStage(long newTime) {
		if (state.equals(StateType.END_STAGE)) {
			if (gameEndStageTime == 0)
				gameEndStageTime = newTime + 3000;
			
			if (newTime < gameEndStageTime)
				Printer.writeText(WIDTH/2 - 170, HEIGHT/2, "Results:");
			else {
				gameEndStageTime = 0;
				state = StateType.NEXT_STAGE;
			}
		}
	}
	
	public void addWarnHit(double x, double y, String text, long newTime) {
		warnHit = new WarnHit((float)x, (float)y, text, newTime);
	}
	
	/**
	 * 
	 * @author Wagner
	 */
	private class WarnHit {
		float x, y;
		String text;
		long timeToShow;
		
		public WarnHit(float x, float y, String text, long timeToShow) {
			this.x = x;
			this.y = y;
			this.text = text;
			this.timeToShow = timeToShow + 1000;
		}
		
		public void print(long newtime) {
			if (timeToShow > newtime)
				Printer.writeText(x, y, text);
		}
	}
	
	public enum StateType {
		MAIN, GAME, NEXT_STAGE, END_STAGE, GAMEOVER;
	}
}
