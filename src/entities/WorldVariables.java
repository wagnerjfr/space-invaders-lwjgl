package entities;

import sprites.SpriteSheet;

public final class WorldVariables {
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public static int IMAGE_WIDTH = 32;
	public static int IMAGE_HEIGHT = 32;
	
	public static SpriteSheet moveableEntities; 
	public static SpriteSheet explosion; 
	
	public static void setUpSpriteSheet() {
		moveableEntities = new SpriteSheet("res/images/spriteSheetEntities.png", "res/images/spriteSheetEntities.sprites");
		explosion = new SpriteSheet("res/images/explosion/spriteSheetExplosion.png", "res/images/explosion/spriteSheetExplosion.sprites");
	}
}
