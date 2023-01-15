import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/** <p>A text item.</p>
 * <p>A text item has drawing capabilities.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class TextItem extends SlideItem {
	private final String text;

	/**
	 * Image component for a slide
	 * @param styleType Style of the bitmap item
	 * @param string The text to show on the page
	 */
	public TextItem(StyleType styleType, String string) {
		super(styleType);
		this.text = string;
	}

	/**
	 * Creates an AttributedString with style attributes from the text
	 * @param style Style of the text
	 * @param scale Scale of the text
	 * @return AttributedString with styles
	 */
	private AttributedString getAttributedString(Style style, float scale) {
		AttributedString attrStr = new AttributedString(this.text);
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, this.text.length());
		return attrStr;
	}

//Returns the bounding box of an Item

	/**
	 * Get the bounding box of the text
	 * @param g The Graphics object to draw to
	 * @param scale Scalar
	 * @return Bounding box of the text
	 */
	public Rectangle getBoundingBox(Graphics g,
			float scale) {
		List<TextLayout> layouts = getLayouts(g, this.getStyle(), scale);
		int xsize = 0, ysize = (int) (this.getStyle().getLeading() * scale);
		for (TextLayout layout : layouts) {
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		return new Rectangle((int) (this.getStyle().getIndent() *scale), 0, xsize, ysize );
	}

	/**
	 * Draws the text to the screen
	 * @param x Draw origin x
	 * @param y Draw origin y
	 * @param scale Scalar
	 * @param g The Graphics object to draw to
	 */
	public void draw(int x, int y, float scale, Graphics g) {
		if (this.text == null || this.text.length() == 0) {
			return;
		}
		List<TextLayout> layouts = getLayouts(g, this.getStyle(), scale);
		Point pen = new Point(x + (int)(this.getStyle().getIndent() * scale),
				y + (int) (this.getStyle().getLeading() * scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(this.getStyle().getColor());
		for (TextLayout layout : layouts) {
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
	  }

	/**
	 * Generate the layouts for the textObject
	 * @param graphics Graphics component to draw to
	 * @param style Style of the text
	 * @param scale Scale of the text
	 * @return List of TextLayouts
	 */
	private List<TextLayout> getLayouts(Graphics graphics, Style style, float scale) {
		List<TextLayout> layouts = new ArrayList<>();
		AttributedString attrStr = getAttributedString(style, scale);
    	Graphics2D g2d = (Graphics2D) graphics;
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	float wrappingWidth = (SlideViewerFrame.WIDTH - style.getIndent()) * scale;
    	while (measurer.getPosition() < this.text.length()) {
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}
    	return layouts;
	}

	public String getText() {
		return this.text;
	}
}
