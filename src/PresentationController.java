public class PresentationController {
    private final Presentation presentation;
    private int currentSlideNumber = 0;

    public PresentationController(Presentation presentation) {
        this.presentation = presentation;
        this.resetCurrentSlide();
    }

    /**
     * Go to the next slide
     */
    public void prevSlide() {
        goToSlide(this.currentSlideNumber - 1);
    }

    /**
     * Go to the previous slide
     */
    public void nextSlide() {
        goToSlide(this.currentSlideNumber + 1);
    }

    /**
     * Go to a specific slide
     * @param slideNumber slide to go to
     */
    public void goToSlide(int slideNumber) {
        if(slideNumber >= 0 && slideNumber < this.presentation.getSlideCount()){
            this.presentation.setActiveSlide(slideNumber);
            this.currentSlideNumber = slideNumber;
        }
    }

    /**
     * Reset the presentation to the first page
     */
    public void resetCurrentSlide() {
        this.currentSlideNumber = 0;
        goToSlide(this.currentSlideNumber);
    }
}
