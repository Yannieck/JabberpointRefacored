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
    private final String imageName;

    /**
     * Image component for a slide
     * @param styleType Style of the bitmap item
     * @param name Name of the to be loaded image
     */
    public BitmapItem(StyleType styleType, String name) {
        super(styleType);
        this.imageName = name;
        try {
            this.bufferedImage = ImageIO.read(new File(this.imageName));
        } catch (IOException e) {
            System.err.println("File " + this.imageName + " not found");
        }
    }

    /**
     * Get the bounding box of the image
     * @param g The Graphics object to draw to
     * @param scale Scalar
     * @return Bounding box of the image
     */
    public Rectangle getBoundingBox(Graphics g, float scale) {
        return new Rectangle(
            (int) (this.getStyle().getIndent() * scale),
            0,
            (int) (this.bufferedImage.getWidth() * scale),
            ((int) (this.getStyle().getLeading() * scale)) + (int) (this.bufferedImage.getHeight() * scale)
        );
    }

    /**
     * Draws the image to the screen
     * @param x Draw origin x
     * @param y Draw origin y
     * @param scale Scalar
     * @param g The Graphics object to draw to
     */
    public void draw(int x, int y, float scale, Graphics g) {
        int width = x + (int) (this.getStyle().getIndent() * scale);
        int height = y + (int) (this.getStyle().getLeading() * scale);
        g.drawImage(this.bufferedImage, width, height, (int) (this.bufferedImage.getWidth() * scale),
            (int) (this.bufferedImage.getHeight() * scale), null);
    }

    public String getName() {
        return this.imageName;
    }
}
