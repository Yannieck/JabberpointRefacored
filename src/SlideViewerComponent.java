import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serial;
import javax.swing.JComponent;
import javax.swing.JFrame;


/** <p>SlideViewerComponent is a graphical component that ca display Slides.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {

	private Slide slide;

	@Serial
	private static final long serialVersionUID = 227L;

	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;
	private int currentPage;
	private int pageCount;

	/**
	 * Create the frame to display the presentation in
	 */
	public SlideViewerComponent() {
		setBackground(BGCOLOR);
		this.currentPage = 0;
		this.pageCount = 0;
	}

	/**
	 * Scale the slide component to the frame size
	 * @return Dimension with frame size
	 */
	public Dimension getPreferredSize() {
		return new Dimension(SlideViewerFrame.WIDTH, SlideViewerFrame.HEIGHT);
	}

	/**
	 * Updates the frame to display the new slide data
	 * @param slide the slide data to display
	 * @param currentPage The current page the user is on
	 * @param pageCount The total number of pages
	 */
	public void update(Slide slide, int currentPage, int pageCount) {
        this.slide = slide;

		if (slide == null) {
			System.out.println("n");
			repaint();
			return;
		}
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		repaint();
	}

	/**
	 * Draw the frame
	 * @param graphics The Graphics object to protect
	 */
	public void paintComponent(Graphics graphics) {
		graphics.setColor(BGCOLOR);
		graphics.fillRect(0, 0, getSize().width, getSize().height);
		if (slide == null) {
			return;
		}
		Font font = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		graphics.setFont(font);

		graphics.setColor(COLOR);
		graphics.drawString("Slide " + (1 + this.currentPage + " of " + this.pageCount), XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		slide.draw(graphics, area);
	}
}
