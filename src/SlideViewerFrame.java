import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.Serial;
import javax.swing.JFrame;

/**
 * <p>The applicatiewindow for a slideviewcomponent</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class SlideViewerFrame extends JFrame {
	@Serial
	private static final long serialVersionUID = 3227L;
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;

	/**
	 * Instantiate a new window
	 * @param presentation the presentation to display
	 */
	public SlideViewerFrame(Presentation presentation) {
		super(presentation.getTitle());
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent();
		presentation.setSlideViewerComponent(slideViewerComponent);

		setupWindow(slideViewerComponent, presentation);
	}

	/**
	 * Sets up the GUI
	 * @param slideViewerComponent main component to add to the frame
	 * @param presentation the presentation to display
	 */
	private void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
		PresentationController presentationController = new PresentationController(presentation);
		addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		getContentPane().add(slideViewerComponent);
		addKeyListener(new KeyController(presentationController));
		setMenuBar(new MenuController(this, presentation, presentationController));
		setSize(new Dimension(WIDTH, HEIGHT));
		setVisible(true);
	}
}
