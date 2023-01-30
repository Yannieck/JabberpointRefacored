/** A built-in demo presentation
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation {

	public static void loadFile(Presentation presentation) {
		presentation.setTitle("Demo Presentation");
		Slide slide;
		slide = new Slide("JabberPoint");
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

		slide = new Slide("Demonstration of levels and styles");
		slide.append(StyleType.H2, "Level 1");
		slide.append(StyleType.H3, "Level 2");
		slide.append(StyleType.H2, "Again level 1");
		slide.append(StyleType.H2, "Level 1 has style number 1");
		slide.append(StyleType.H3, "Level 2 has style number 2");
		slide.append(StyleType.H4, "This is how level 3 looks like");
		slide.append(StyleType.P, "And this is level 4");
		presentation.append(slide);

		slide = new Slide("The third slide");
		slide.append(StyleType.H2, "To open a new presentation,");
		slide.append(StyleType.H3, "use File->Open from the menu.");
		slide.append(StyleType.H2, " ");
		slide.append(StyleType.H2, "This is the end of the presentation.");
		slide.append(new BitmapItem(StyleType.H1, "JabberPoint.jpg"));
		presentation.append(slide);
	}
}
