import Jabberpoint.Accessor.XMLAccessor;
import Jabberpoint.Accessor.DemoPresentation;
import Jabberpoint.Presentation.Presentation;
import Jabberpoint.UI.SlideViewerFrame;

import javax.swing.JOptionPane;

import java.io.IOException;

/**
 * JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class JabberPoint {
    /**
     * Loads the presentation and generates the UI
     * @param argv parsed arguments
     */
    public static void main(String[] argv) {
        Presentation presentation = new Presentation();
        try {
            if (argv.length == 0) {
                DemoPresentation.loadFile(presentation);
            } else {
                new XMLAccessor().loadFile(presentation, argv[0]);
            }
            new SlideViewerFrame(presentation);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "IO Error: " + ex, "Jabberpoint Error ",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
