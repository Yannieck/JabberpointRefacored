import java.awt.*;
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
	protected ArrayList<SlideItem> slideItems; //The SlideItems are kept in a ArrayList

	public Slide() {
		slideItems = new ArrayList<SlideItem>();
	}

	/**
	 * Adds a SlideItem to the slide
	 * @param slideItem the SlideItem to add to the slide
	 */
	public void append(SlideItem slideItem) {
		slideItems.add(slideItem);
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
	 * Draws the slide with the slideItems to the screen
	 * @param graphics the Graphics object to draw to
	 * @param area the area to draw inside of
	 */
	public void draw(Graphics graphics, Rectangle area) {
		float scale = calculateScale(area.width, area.height);
		Point drawOrigin = new Point(area.x, area.y);

		SlideItem titleItem = new TextItem(StyleType.H1, this.title);
		titleItem.draw(drawOrigin.x, drawOrigin.y, scale, graphics);
		drawOrigin.y += titleItem.getBoundingBox(graphics, scale).height;

		for (SlideItem slideItem : this.slideItems) {
			slideItem.draw(drawOrigin.x, drawOrigin.y, scale, graphics);
			drawOrigin.y += slideItem.getBoundingBox(graphics, scale).height;
		}
	}

	/**
	 * Calculates the scalar to scale the page content to the window size
	 * @param width The height of the draw area
	 * @param height The height of the draw area
	 * @return scalar to scale components
	 */
	private float calculateScale(float width, float height) {
		return Math.min(width / ((float)SlideViewerFrame.WIDTH), height / ((float)SlideViewerFrame.HEIGHT));
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public SlideItem getSlideItems(int slideIndex){
		return this.slideItems.get(slideIndex);
	}

	public int getSlideCount() {
		return slideItems.size();
	}
}
