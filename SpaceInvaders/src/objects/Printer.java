package objects;

import java.awt.Font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Printer {
	private static UnicodeFont fontText;
	private static UnicodeFont fontNum;
	private static UnicodeFont fontWarn;
	private static UnicodeFont fontHits;
	
	public static void writeText(float x, float y, String text) {
		fontText.drawString(x, y, text);
	}

	public static void writeNum(float x, float y, String text) {
	    fontNum.drawString(x, y, text);
	}
	
	public static void writeWarn(float x, float y, String text) {
		fontWarn.drawString(x, y, text);
	}
	
	public static void writeHits(float x, float y, String text) {
		fontHits.drawString(x, y, text);
	}

	@SuppressWarnings("unchecked")
	public static void setUpFonts() {
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

        Font awtFontWarn = new Font("Verdana", Font.BOLD, 24);
        fontWarn = new UnicodeFont(awtFontWarn);
        fontWarn.getEffects().add(new ColorEffect(java.awt.Color.lightGray));
        fontWarn.addAsciiGlyphs();
        try {
        	fontWarn.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }

        Font awtFontHits = new Font("Verdana", Font.BOLD, 14);
        fontHits = new UnicodeFont(awtFontHits);
        fontHits.getEffects().add(new ColorEffect(java.awt.Color.white));
        fontHits.addAsciiGlyphs();
        try {
        	fontHits.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
