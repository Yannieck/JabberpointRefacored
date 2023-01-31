package Jabberpoint.Accessor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Jabberpoint.Presentation.Presentation;
import Jabberpoint.Slide.BitmapItem;
import Jabberpoint.Slide.Slide;
import Jabberpoint.Slide.SlideItem;
import Jabberpoint.Slide.TextItem;
import Jabberpoint.Styles.StyleType;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


/** Jabberpoint.Accessor.XMLAccessor, reads and writes XML files
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class XMLAccessor implements Loader, Saver {

	/**
	 * Gets the value of the tag from an element
	 * @param element Element to look through
	 * @param tagName name of the tag to search for
	 * @return String with the value of the tag
	 */
    private String getElementText(Element element, String tagName) {
    	NodeList titles = element.getElementsByTagName(tagName);
    	return titles.item(0).getTextContent();
    }

	/**
	 * Loads an XML file to the presentation
	 * @param presentation The presentation to load to
	 * @param filename The path of the file to upload
	 * @throws IOException Throws when loading file failed
	 */
	public void loadFile(Presentation presentation, String filename) throws IOException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();    
			Document document = builder.parse(new File(filename));
			Element doc = document.getDocumentElement();
			presentation.setTitle(getElementText(doc, "showtitle"));
			presentation.clearSlides();

			NodeList slides = doc.getElementsByTagName("slide");

			loadSlides(presentation, slides);
		} 
		catch (IOException | SAXException iox) {
			System.err.println(iox.getMessage());
		} catch (ParserConfigurationException pcx) {
			System.err.println("Parser Configuration Exception");
		}	
	}

	private void loadSlides(Presentation presentation, NodeList slides) {
		for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {
			Element xmlSlide = (Element) slides.item(slideNumber);

			Slide slide = new Slide(getElementText(xmlSlide, "title"));
			presentation.append(slide);

			NodeList slideItems = xmlSlide.getElementsByTagName("item");

			for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
				Element item = (Element) slideItems.item(itemNumber);
				loadSlideItem(slide, item);
			}
		}
	}

	/**
	 * Loads a slide item to a slide
	 * @param slide The slide to load to
	 * @param item The item to load to the slide
	 */
	private void loadSlideItem(Slide slide, Element item) {
		int level = 1;
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem("level").getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				System.err.println("Number Format Exception");
			}
		}
		String type = attributes.getNamedItem("kind").getTextContent();
		if ("text".equals(type)) {
			slide.append(new TextItem(StyleType.values()[level], item.getTextContent()));
		}
		else {
			if ("image".equals(type)) {
				slide.append(new BitmapItem(StyleType.values()[level], item.getTextContent()));
			}
			else {
				System.err.println("Unknown Element type");
			}
		}
	}

	/**
	 * Save a file to the given file path
	 * @param presentation The presentation to save
	 * @param filename The path to save to
	 * @throws IOException Throws when writing file failed
	 */
	public void saveFile(Presentation presentation, String filename) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(filename));
		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		out.println("<presentation>");
		out.print("<showtitle>");
		out.print(presentation.getTitle());
		out.println("</showtitle>");
		for (int slideNumber = 0; slideNumber<presentation.getSlideCount(); slideNumber++) {
			Slide slide = presentation.getSlide(slideNumber);
			out.println("<slide>");
			out.println("<title>" + slide.getTitle() + "</title>");
			for (int itemNumber = 0; itemNumber<slide.getSlideCount(); itemNumber++) {
				SlideItem slideItem = slide.getSlideItems(itemNumber);
				out.print("<item kind="); 
				if (slideItem instanceof TextItem) {
					out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
					out.print( ( (TextItem) slideItem).getText());
				}
				else {
					if (slideItem instanceof BitmapItem) {
						out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
						out.print( ( (BitmapItem) slideItem).getName());
					}
					else {
						System.out.println("Ignoring " + slideItem);
					}
				}
				out.println("</item>");
			}
			out.println("</slide>");
		}
		out.println("</presentation>");
		out.close();
	}
}
