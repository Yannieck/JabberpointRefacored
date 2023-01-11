import java.awt.Rectangle;
import java.awt.Graphics;

/** <p>The abstract class for items on a slide.<p>
 * <p>All SlideItems have drawing capabilities.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public abstract class SlideItem {
	private final StyleType styleType;

	public SlideItem(StyleType styleType) {
		this.styleType = styleType;
	}

	/**
	 * Get the bounding box
	 * @param graphics the Graphics object to draw to
	 * @param scale scalar
	 * @return rectangle with the position and size of the bounding box
	 */
	public abstract Rectangle getBoundingBox(Graphics graphics, float scale);

	/**
	 * Draw the SlideItem to the screen
	 * @param x draw origin x
	 * @param y draw origin y
	 * @param scale scalar
	 * @param graphics the Graphics object to draw to
	 */
	public abstract void draw(int x, int y, float scale,
			Graphics graphics);

	public StyleType getLevel() {
		return styleType;
	}

	protected Style getStyle(){
		return StyleFactory.getStyle(this.styleType);
	}
}
