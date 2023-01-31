package Jabberpoint.Presentation;

import Jabberpoint.Slide.Slide;
import Jabberpoint.UI.SlideViewerComponent;

import java.util.ArrayList;

/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String title; //The title of the presentation
	private ArrayList<Slide> showList; //An ArrayList with slides
	private SlideViewerComponent slideViewComponent; //The view component of the slides

	public Presentation() {
		this.showList = new ArrayList<>();
		this.setActiveSlide(0);
	}

	/**
	 * Add a slideViewerComponent to the presentation
	 * @param slideViewerComponent slideViewerComponent
	 */
	public void setSlideViewerComponent(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	/**
	 * Set the currently active slide by page number
	 * @param number page number
	 */
	public void setActiveSlide(int number) {
		if (slideViewComponent != null) {
			slideViewComponent.update(getSlide(number), number, getSlideCount());
		}
	}

	/**
	 * Clear the slides in the presentation
	 */
	public void clearSlides() {
		showList = new ArrayList<>();
		slideViewComponent.update(null, 0, 0);
	}

	/**
	 * Add a slide to the presentation
	 * @param slide slide to add
	 */
	public void append(Slide slide) {
		showList.add(slide);
	}

	/**
	 * Get a slide with a specific number
	 * @param number Page number of the slide
	 * @return Slide object
	 */
	public Slide getSlide(int number) {
		if(number < showList.size() && number >= 0){
			return showList.get(number);
		} else {
			return null;
		}
	}

	public int getSlideCount() {
		return showList.size();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
