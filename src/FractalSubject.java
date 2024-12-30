import java.awt.*;
import java.util.ArrayList;

/**
 * FractalSubject Interface
 */
public interface FractalSubject {
    /**
     * Abstract attach method
     * @param observer observer
     */
    void attach(FractalObserver observer);
    /**
     * Abstract detach method
     * @param observer observer
     */
    void detach(FractalObserver observer);

    /**
     * Abstract notify method
     */
    void notifyObserver();

    /**
     * Abstract serUserChoices method
     * @param value recursion depth of fractal
     * @param cToPSliderValue child to parent ratio
     * @param childCountSliderValue child count
     * @param bLevelSliderValue bedlam level
     * @param background color
     * @param selected pastel checkbox
     */
    void setUserChoices(int value, int cToPSliderValue, int childCountSliderValue, int bLevelSliderValue, Color background, boolean selected);

    /**
     * Abstract getData method
     * @return ArrayList of FractalElements
     */
    ArrayList<FractalElement> getData();
}
