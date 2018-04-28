package state;

import java.awt.Font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Printer {
    private static UnicodeFont fontYellow;
    private static UnicodeFont fontWhite;
    private static UnicodeFont fontWarn;
    private static UnicodeFont fontHits;
    private static UnicodeFont fontTitle;

    public static void write(float x, float y, String text, PrinterType type) {
        switch (type) {
        case YELLOW:
            fontYellow.drawString(x, y, text);
            break;
        case WHITE:
            fontWhite.drawString(x, y, text);
            break;
        case HITS:
            fontHits.drawString(x, y, text);
            break;
        case WARN:
            fontWarn.drawString(x, y, text);
            break;
        default:
            fontTitle.drawString(x, y, text);
            break;
        }
    }

    @SuppressWarnings("unchecked")
    public static void setUpFonts() throws SlickException {
        Font awtFontText = new Font("Verdana", Font.BOLD, 14);
        fontYellow = new UnicodeFont(awtFontText);
        fontYellow.getEffects().add(new ColorEffect(java.awt.Color.yellow));
        fontYellow.addAsciiGlyphs();
        fontYellow.loadGlyphs();

        Font awtFontNum = new Font("Verdana", Font.BOLD, 14);
        fontWhite = new UnicodeFont(awtFontNum);
        fontWhite.getEffects().add(new ColorEffect(java.awt.Color.white));
        fontWhite.addAsciiGlyphs();
        fontWhite.loadGlyphs();

        Font awtFontWarn = new Font("Verdana", Font.BOLD, 24);
        fontWarn = new UnicodeFont(awtFontWarn);
        fontWarn.getEffects().add(new ColorEffect(java.awt.Color.lightGray));
        fontWarn.addAsciiGlyphs();
        fontWarn.loadGlyphs();

        Font awtFontHits = new Font("Verdana", Font.BOLD, 14);
        fontHits = new UnicodeFont(awtFontHits);
        fontHits.getEffects().add(new ColorEffect(java.awt.Color.white));
        fontHits.addAsciiGlyphs();
        fontHits.loadGlyphs();

        Font awtFontTitle = new Font("Verdana", Font.BOLD, 42);
        fontTitle = new UnicodeFont(awtFontTitle);
        fontTitle.getEffects().add(new ColorEffect(java.awt.Color.LIGHT_GRAY));
        fontTitle.addAsciiGlyphs();
        fontTitle.loadGlyphs();

    }

    public enum PrinterType {
        YELLOW, WHITE, HITS, WARN, TITLE;
    }
}
