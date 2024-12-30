import java.awt.*;

/**
 * Arc Record
 * @param x coordinate
 * @param y coordinate
 * @param size of the bubble
 */
public record Arc(int x, int y, int size) implements FractalElement {
    @Override
    public void draw(Graphics graphics, int width, int height) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(new Color(204,204,204,102));
        BasicStroke bs = new BasicStroke((float) size /20);
        g2d.setStroke(bs);
        g2d.drawArc((int) (x-(size*0.1)),(int) (y+(size*0.1)),size,size,40,10);
    }
}
