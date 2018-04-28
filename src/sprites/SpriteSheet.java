package sprites;

import java.io.IOException;
import java.util.HashMap;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


import static org.lwjgl.opengl.GL11.*;

public class SpriteSheet {

    private String file_png;
    private String file_sprites;

    public int spriteSheet;
    public HashMap<String, Sprite> spriteMap = new HashMap<String, Sprite>();

    public SpriteSheet(String file_png, String file_sprites) throws IOException {
        this.file_png = file_png;
        this.file_sprites = file_sprites;
        
        setUpSpriteSheet();
    }

    private void setUpSpriteSheet() throws IOException {
        spriteSheet = ImagingTools.glLoadTextureLiner(file_png);
        SAXBuilder builder = new SAXBuilder();

        try {

            Document document = builder.build(file_sprites);
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
            glDeleteTextures(spriteSheet);
            e.printStackTrace();
        }
    }
}
