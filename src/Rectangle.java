import biuoop.DrawSurface;
import java.awt.Color;

/**
 * class for Rectangles.
 */
public class Rectangle {
    private final double x;
    private final double y;
    private double width = 0;
    private double height = 0;
    private Color color = Color.white;


    /**
     * Constructor.
     * @param x - x coordinate from upper left corner
     * @param y - y coordinate from upper left corner
     * @param width - width of rectangle
     * @param height - height of rectangle
     * @param color - color
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * draws a filled rectangle on surface.
     * @param surface - GUI surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.x, (int) this.y, (int) this.width, (int) this.height);
    }
    /*
    //////////////////////////////////////////////
                        GETS
    //////////////////////////////////////////////
     */

    /**
     * getX.
     * @return x
     */
    public double getX() {
        return this.x;
    }
    /**
     * getY.
     * @return Y
     */
    public double getY() {
        return this.y;
    }
    /**
     * getWidth.
     * @return width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * getHeight.
     * @return height
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * getColor.
     * @return color
     */
    public Color getColor() {
        return this.color;
    }
}
