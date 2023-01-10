/**
 * A built-in demo presentation
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation extends Accessor {

    public void loadFile(Presentation presentation, String unusedFilename) {
        presentation.setTitle("Demo Presentation");
        Slide slide;
        slide = new Slide();
        slide.setTitle("JabberPoint");
        slide.append(StyleType.H2, "The Java prestentation tool");
        slide.append(StyleType.H3, "Copyright (c) 1996-2000: Ian Darwin");
        slide.append(StyleType.H3, "Copyright (c) 2000-now:");
        slide.append(StyleType.H3, "Gert Florijn and Sylvia Stuurman");
        slide.append(StyleType.P, "Calling Jabberpoint without a filename");
        slide.append(StyleType.P, "will show this presentation");
        slide.append(StyleType.H2, "Navigate:");
        slide.append(StyleType.H4, "Next slide: PgDn or Enter");
        slide.append(StyleType.H4, "Previous slide: PgUp or up-arrow");
        slide.append(StyleType.H4, "Quit: q or Q");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("Demonstration of levels and styles");
        slide.append(StyleType.H2, "Level 1");
        slide.append(StyleType.H3, "Level 2");
        slide.append(StyleType.H2, "Again level 1");
        slide.append(StyleType.H2, "Level 1 has style number 1");
        slide.append(StyleType.H3, "Level 2 has style number 2");
        slide.append(StyleType.H4, "This is how level 3 looks like");
        slide.append(StyleType.P, "And this is level 4");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("The third slide");
        slide.append(StyleType.H2, "To open a new presentation,");
        slide.append(StyleType.H3, "use File->Open from the menu.");
        slide.append(StyleType.H2, " ");
        slide.append(StyleType.H2, "This is the end of the presentation.");
        slide.append(new BitmapItem(StyleType.H2, "JabberPoint.jpg"));
        presentation.append(slide);
    }

    public void saveFile(Presentation presentation, String unusedFilename) {
        throw new IllegalStateException("Save As->Demo! called");
    }
}
