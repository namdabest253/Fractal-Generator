import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * FractalGenerator class that calculates data to draw the shapes and is the concrete Subject
 */
public class FractalGenerator implements FractalSubject{
    /**
     * ArrayList of the observers
     */
    private final ArrayList<FractalObserver> observers;
    /**
     * ArrayList of the elements
     */
    private final ArrayList<FractalElement> elements;
    /**
     * Recursion depth of fractal
     */
    private int depthValue;
    /**
     * Child to parent ratio of the fractal
     */
    private int childToParent;
    /**
     * Child count of the fractal
     */
    private int childCount;
    /**
     * Bedlam Level of the fractal
     */
    private int bedlamLevel;
    /**
     * Color of the fractal
     */
    private Color color;
    /**
     * Boolean for random pastels
     */
    private boolean randomPastels;
    /**
     * Random object
     */
    private final Random random;

    /**
     * FractalGenerator constructor
     */
    public FractalGenerator() {
        observers = new ArrayList<>();
        elements = new ArrayList<>();
        random = new Random();
    }

    /**
     * Adds an observer to the observers ArrayList
     * @param observer observer that's being added
     */
    @Override
    public void attach(FractalObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the observers ArrayList
     * @param observer observer that's being removed
     */
    @Override
    public void detach(FractalObserver observer) {
        observers.remove(observer);
    }

    /**
     * Calls the update method for every observer in the observers ArrayList
     */
    @Override
    public void notifyObserver() {
        for (FractalObserver observer : observers) {
            observer.update();
        }
    }

    /**
     * Large set method for every value
     * @param depthValue recursion depth
     * @param childToParent child to parent ratio
     * @param childCount child count
     * @param bedlamLevel bedlam level
     * @param backgroundColor color of the fractal
     * @param pastels boolean if pastel colors
     */
    @Override
    public void setUserChoices(int depthValue, int childToParent, int childCount, int bedlamLevel, Color backgroundColor, boolean pastels) {
        this.depthValue = depthValue;
        this.childToParent = childToParent;
        this.childCount = childCount;
        this.bedlamLevel = bedlamLevel;
        color = backgroundColor;
        randomPastels = pastels;
        generateFractals();
        notifyObserver();
    }

    /**
     * Get method for elements ArrayList
     * @return ArrayList of elements
     */
    @Override
    public ArrayList<FractalElement> getData() {
        return elements;
    }

    /**
     * Generates the data for the shapes in the fractal
     */
    public void generateFractals() {
        elements.clear();

        double canvasSize = 700;
        double size = canvasSize/4;
        double position = (canvasSize-size)/2;
        generateFractalHelper(position, position, size, depthValue);

        notifyObserver();
    }

    /**
     * Helped method for generateFractals
     * @param x coordinate
     * @param y coordinate
     * @param size size of shape
     * @param depth recursion depth of fractal
     */
    private void generateFractalHelper(double x, double y, double size, int depth) {
        if (depth == 0) {
            return;
        }

        double childSize = size * (childToParent / 100.0);
        double childAngleDelta = 360.0 / childCount;
        double typicalDistance = (size - childSize) / 2;

        FractalElement currentElement = generateElement(x, y, size);
        FractalElement arcElement = generateArcElement(x, y, size);
        elements.add(currentElement);
        elements.add(arcElement);

        for (int i = 0; i < childCount; i++) {

            double angle = childAngleDelta * i;
            double childBedlamDistanceX = 0, childBedlamDistanceY = 0;

            if (bedlamLevel != 0) {
                double radiusBedlam = childSize * (0.1/4) * bedlamLevel;
                double angleBedlam = childAngleDelta * (0.1/4) * bedlamLevel;
                double distanceBedlam = typicalDistance * (0.25/4) * bedlamLevel;

                childSize = applyBedlam(childSize, radiusBedlam);
                angle = applyBedlam(angle, angleBedlam);
                childBedlamDistanceX = applyBedlam(0, distanceBedlam);
                childBedlamDistanceY = applyBedlam(0, distanceBedlam);

                if(Math.random() > 0.5) { childBedlamDistanceX *= -1; }
                if(Math.random() > 0.5) { childBedlamDistanceY *= -1; }
            }

            double childX = (childBedlamDistanceX + (size-childSize)/2 + x + Math.cos(Math.toRadians(angle-90)) * (size + childSize)/2);
            double childY = (childBedlamDistanceY + (size-childSize)/2 + y + Math.sin(Math.toRadians(angle-90)) * (size + childSize)/2);

            // Recursive call
            generateFractalHelper(childX, childY, childSize, depth - 1);
        }
    }

    /**
     * Calculates the original distribution value
     * @param mean mean
     * @param bedlamLevel bedlam level
     * @return Original distribution value
     */
    private double applyBedlam(double mean, double bedlamLevel) {
        return (random.nextGaussian() * bedlamLevel) + mean;
    }

    /**
     * Creates a new Bubbles object that will be stored in the elements ArrayList
     * @param x coordinate
     * @param y coordinate
     * @param size size of bubble
     * @return Bubbles record
     */
    private FractalElement generateElement(double x, double y, double size) {
        if (randomPastels) {
            color = generateRandomPastelColor();
        }
        return new Bubble((int) x, (int) y, (int) size, color);
    }

    /**
     * Creates a new Arc object that will be stored in the elements ArrayList
     * @param x coordinate
     * @param y coordinate
     * @param size size of bubble
     * @return Arc record
     */
    private FractalElement generateArcElement(double x, double y, double size) {
        return new Arc((int) x, (int) y, (int) size);
    }

    /**
     * Generates a random pastel color
     * @return random pastel color
     */
    private Color generateRandomPastelColor() {
        int r = 128 + random.nextInt(128);
        int g = 128 + random.nextInt(128);
        int b = 128 + random.nextInt(128);
        return new Color(r, g, b);
    }
}
