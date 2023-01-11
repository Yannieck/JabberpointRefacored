import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/**
 * <p>The class for a Bitmap item</p>
 * <p>Bitmap items are responsible for drawing themselves.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class BitmapItem extends SlideItem {
    private BufferedImage bufferedImage;
    private String imageName;

    protected static final String FILE = "File ";
    protected static final String NOTFOUND = " not found";


    //level indicates the item-level; name indicates the name of the file with the image
    public BitmapItem(StyleType level, String name) {
        super(level);
        imageName = name;
        try {
            bufferedImage = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            System.err.println(FILE + imageName + NOTFOUND);
        }
    }

    //An empty bitmap item
    public BitmapItem() {
        this(StyleType.H1, null);
    }

    //Returns the filename of the image
    public String getName() {
        return imageName;
    }

    //Returns the bounding box of the image
    public Rectangle getBoundingBox(Graphics g, float scale) {
        return new Rectangle((int) (this.getStyle().getIndent() * scale), 0,
                (int) (bufferedImage.getWidth() * scale),
                ((int) (this.getStyle().getLeading() * scale)) +
                        (int) (bufferedImage.getHeight() * scale));
    }

    //Draws the image
    public void draw(int x, int y, float scale, Graphics g) {
        int width = x + (int) (this.getStyle().getIndent() * scale);
        int height = y + (int) (this.getStyle().getLeading() * scale);
        g.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth() * scale),
                (int) (bufferedImage.getHeight() * scale), null);
    }
}
