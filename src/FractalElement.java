import java.awt.Graphics;

/**
 * FractalElement Interface
 */
public interface FractalElement {
    /**
     * Abstract method to draw shapes
     * @param graphics graphics
     * @param width of canvas
     * @param height of canvas
     */
    void draw(Graphics graphics, int width, int height);
}
