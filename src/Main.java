/**
 * Main method
 */
public class Main {

    /**
     * Constructor
     */
    public Main(){}

    /**
     * main method
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        FractalGenerator subject = new FractalGenerator();
        new FractalGui(subject);
        new FractalDrawing(subject);
    }
}