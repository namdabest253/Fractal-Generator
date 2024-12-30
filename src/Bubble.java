import java.awt.*;

/**
 * Bubble record
 * @param x coordinate
 * @param y coordinate
 * @param size of bubble
 * @param color of bubble
 */
public record Bubble(int x, int y, int size, Color color) implements FractalElement {
    @Override
    public void draw(Graphics graphics, int width, int height) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke bs = new BasicStroke(1);
        g2d.setStroke(bs);
        g2d.drawOval(x,y,size,size);
        Color transparent = new Color(color.getRed(), color.getGreen(), color.getBlue(), 102);
        g2d.setColor(transparent);
        g2d.fillOval(x,y,size,size);
    }
}

