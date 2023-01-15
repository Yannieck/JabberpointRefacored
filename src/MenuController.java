import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serial;

import javax.swing.JOptionPane;

/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {

    @Serial
    private static final long serialVersionUID = 227L;

    protected static final String TESTFILE = "testPresentation.xml";
    protected static final String SAVEFILE = "savedPresentation.xml";

    private final Frame parentFrame;
    private final Presentation presentation;

    private final PresentationController presentationController;

    public MenuController(Frame frame, Presentation pres, PresentationController presentationController) {
        this.parentFrame = frame;
        this.presentation = pres;
        this.presentationController = presentationController;
        MenuItem menuItem;

        Menu fileMenu = new Menu("File");
        fileMenu.add(createOpenFileButton());
        fileMenu.add(createNewFileButton());
        fileMenu.add(createSaveFileButton());
        fileMenu.addSeparator();
        fileMenu.add(createExitButton());
        add(fileMenu);

        Menu viewMenu = new Menu("View");
        viewMenu.add(menuItem = mkMenuItem("Next"));
        menuItem.addActionListener(actionEvent -> presentationController.nextSlide());
        viewMenu.add(menuItem = mkMenuItem("Prev"));
        menuItem.addActionListener(actionEvent -> presentationController.prevSlide());
        viewMenu.add(menuItem = mkMenuItem("Go to"));
        menuItem.addActionListener(actionEvent -> {
            String pageNumberStr = JOptionPane.showInputDialog("Page number?");
            int pageNumber = Integer.parseInt(pageNumberStr);
            presentationController.goToSlide(pageNumber - 1);
        });
        add(viewMenu);

        Menu helpMenu = new Menu("Help");
        helpMenu.add(menuItem = mkMenuItem("About"));
        menuItem.addActionListener(actionEvent -> AboutBox.show(parentFrame));
        setHelpMenu(helpMenu);
    }

    private MenuItem createOpenFileButton() {
        MenuItem menuItem = mkMenuItem("Open");

        menuItem.addActionListener(actionEvent -> {
            try {
                XMLAccessor.loadFile(this.presentation, TESTFILE);
                this.presentationController.resetCurrentSlide();
                this.parentFrame.setTitle(this.presentation.getTitle());
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(this.parentFrame, "IO Exception: " + exc,
                        "Load Error", JOptionPane.ERROR_MESSAGE);
            }
            this.parentFrame.repaint();
        });

        return menuItem;
    }

    private MenuItem createNewFileButton() {
        MenuItem menuItem = mkMenuItem("New");

        menuItem.addActionListener(actionEvent -> {
            this.presentationController.resetCurrentSlide();
            this.presentation.clearSlides();
            this.parentFrame.repaint();
        });

        return menuItem;
    }

    private MenuItem createSaveFileButton() {
        MenuItem menuItem = mkMenuItem("Save");

        menuItem.addActionListener(actionEvent -> {
            try {
                XMLAccessor.saveFile(presentation, SAVEFILE);
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(parentFrame, "IO Exception: " + exc,
                        "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return menuItem;
    }

    private MenuItem createExitButton() {
        MenuItem menuItem = mkMenuItem("Exit");
        menuItem.addActionListener(actionEvent -> System.exit(0));
        return menuItem;
    }

    /**
     * Creates a button in the menu
     * @param name Display text of the button
     * @return MenuItem with text
     */
    public MenuItem mkMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
