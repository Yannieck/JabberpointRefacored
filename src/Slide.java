import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/** <p>A slide. This class has drawing functionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
	protected String title; //The title is kept separately
	protected ArrayList<SlideItem> items; //The SlideItems are kept in a ArrayList

	public Slide() {
		items = new ArrayList<SlideItem>();
	}

	/**
	 * Adds a SlideItem to the slide
	 * @param slideItem the SlideItem to add to the slide
	 */
	public void append(SlideItem slideItem) {
		items.add(slideItem);
	}

	/**
	 * Adds a TextItem to the slide, based of off the level and message
	 * @param styleType The StyleType the text will get
	 * @param text The text inside the TextItem
	 */
	public void append(StyleType styleType, String text) {
		append(new TextItem(styleType, text));
	}

	/**
	 * Draws the slide with the slideItems to the screen.
	 * @param graphics the Graphics object to draw to
	 * @param area the area to draw inside of
	 */
	public void draw(Graphics graphics, Rectangle area) {
		float scale = calculateScale(area);
	    int y = area.y;

	    SlideItem slideItem = new TextItem(StyleType.H1, getTitle());
	    Style style = StyleFactory.getStyle(slideItem.getLevel());
	    slideItem.draw(area.x, y, scale, graphics, style);
	    y += slideItem.getBoundingBox(graphics, scale, style).height;

	    for (int number = 0; number< getSlideCount(); number++) {
	      slideItem = getSlideItems(number);
	      style = StyleFactory.getStyle(slideItem.getLevel());
	      slideItem.draw(area.x, y, scale, graphics, style);
	      y += slideItem.getBoundingBox(graphics, scale, style).height;
	    }
	  }

	/**
	 * Calculates the scalar to scale the page content to the window size
	 * @param area Rectangle with the container size
	 * @return scalar to scale components
	 */
	private float calculateScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)SlideViewerFrame.WIDTH), ((float)area.height) / ((float)SlideViewerFrame.HEIGHT));
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public SlideItem getSlideItems(int slideIndex){
		return this.items.get(slideIndex);
	}

	public int getSlideCount() {
		return items.size();
	}
}
