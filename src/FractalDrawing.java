import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class that draws the fractal is the concrete Observer
 */
public class FractalDrawing extends JFrame implements FractalObserver{
    /**
     * Size of the canvas
     */
    public static final int SIZE = 700;
    /**
     * FractalSubject object
     */
    private final FractalSubject subject;
    /**
     * DrawPanel object to draw the fractal on
     */
    DrawPanel drawingPanel;

    /**
     * FractalDrawing constructor
     * @param subj FractalSubject
     */
    public FractalDrawing(FractalSubject subj) {
        this.subject = subj;
        this.subject.attach(this);

        JFrame drawingFrame = new JFrame("Bubbles and Bedlam Fractal Display");
        drawingFrame.setSize(SIZE,SIZE);
        drawingPanel = new DrawPanel();
        drawingFrame.setContentPane(drawingPanel);
        drawingPanel.setBackground(Color.BLACK);
        drawingFrame.setResizable(false);
        drawingFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        drawingFrame.setVisible(true);

    }

    /**
     * Update method that gets called by FractalGenerator
     */
    @Override
    public void update() {
        drawingPanel.repaint();
    }

    /**
     * Private DrawPanel inner class
     */
    private class DrawPanel extends JPanel {

        /**
         * Constructor
         */
        DrawPanel(){}

        /**
         * Method that draws the shapes
         * @param g the <code>Graphics</code> object to protect
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ArrayList<FractalElement> elements = subject.getData();
            for (FractalElement element : elements) {
                element.draw(g, SIZE, SIZE);
            }

        }
    }
}
