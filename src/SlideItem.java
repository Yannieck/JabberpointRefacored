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
	private StyleType styleType; //The level of the SlideItem

	public SlideItem(StyleType styleType) {
		this.styleType = styleType;
	}

	public SlideItem() {
		this(StyleType.P);
	}

//Returns the level
	public StyleType getLevel() {
		return styleType;
	}

	protected Style getStyle(){
		return StyleFactory.getStyle(this.styleType);
	}

//Returns the bounding box
	public abstract Rectangle getBoundingBox(Graphics g, float scale);

//Draws the item
	public abstract void draw(int x, int y, float scale, 
			Graphics g);
}
