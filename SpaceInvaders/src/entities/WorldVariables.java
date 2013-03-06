package entities;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.util.HashMap;

public final class WorldVariables {
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public static int IMAGE_WIDTH = 32;
	public static int IMAGE_HEIGHT = 32;
	
	public static int spritesheet;
	public static HashMap<String, Sprite> spriteMap = new HashMap<String, Sprite>();
	
	public static void setUpSpriteSheet() {
		spritesheet = ImagingTools.glLoadTextureLiner("res/images/spritesheet.png");
		SAXBuilder builder = new SAXBuilder();
		
		try {
			
			Document document = builder.build("res/images/spritesheet.sprites");
			Element root = document.getRootElement();
			for (Object spriteObject : root.getChildren()) {
				Element spriteElement = (Element) spriteObject;
				String name = spriteElement.getAttributeValue("name");
				int x = spriteElement.getAttribute("x").getIntValue();
				int y = spriteElement.getAttribute("y").getIntValue();
				int w = spriteElement.getAttribute("w").getIntValue();
				int h = spriteElement.getAttribute("h").getIntValue();
				Sprite sprite = new Sprite(name, x, y, w, h);
				spriteMap.put(sprite.getName(), sprite);
			}
			
		} catch (JDOMException e) {
			glDeleteTextures(spritesheet);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
