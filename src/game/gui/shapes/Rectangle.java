package game.gui.shapes;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * class for Rectangles.
 */
public class Rectangle {
    private final Point upperLeft;
    private double width = 0;
    private double height = 0;
    private Color color = Color.WHITE;


    /**
     * Constructor.
     * @param x - x coordinate from upper left corner
     * @param y - y coordinate from upper left corner
     * @param width - width of rectangle
     * @param height - height of rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor 2.
     * @param upperleft - point to start the rectangle
     * @param width - width
     * @param height - height
     */
    public Rectangle(Point upperleft, double width, double height) {
        this.upperLeft = upperleft;
        this.width = width;
        this.height = height;
    }

    /**
     * list of intersection point that collide with the line given.
     * @param line to check with
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new ArrayList<Point>();
        double rectleftX   = this.upperLeft.getX();
        double rectrightX  = this.upperLeft.getX() + this.width;
        double rectupY    = this.upperLeft.getY();
        double rectdownY = this.upperLeft.getY() + this.height;
        Line[] lines = new Line[4];
        lines[0] = new Line(rectleftX, rectupY, rectleftX, rectdownY); // left line
        lines[1] = new Line(rectrightX, rectupY, rectrightX, rectdownY); // right line
        lines[2] = new Line(rectleftX, rectupY, rectrightX, rectupY); // top line
        lines[3] = new Line(rectleftX, rectdownY, rectrightX, rectdownY); // bottom line
        for (int i = 0; i < 4; i++) {
            Point point = line.intersectionWith(lines[i]);
            if (point != null) {
                intersectionPoints.add(point);
            }
        }
        return intersectionPoints;
    }

    /**
     * draws a filled rectangle on surface.
     * @param surface - GUI surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }
    /*
    //////////////////////////////////////////////
                        GETS
    //////////////////////////////////////////////
     */

    /**
     * upperleft corner.
     * @return the upper left corner
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * getX.
     * @return x
     */
    public double getX() {
        return this.upperLeft.getX();
    }
    /**
     * getY.
     * @return Y
     */
    public double getY() {
        return this.upperLeft.getY();
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
     * Get color.
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }
    /*
    //////////////////////////////////////////////
                        SETS
    //////////////////////////////////////////////
     */

    /**
     * sets a new width.
     * @param width new value
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * set a new height.
     * @param height new value.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * sets X.
     * @param x new x value.
     */
    public void setX(double x) {
        this.upperLeft.setX(x);
    }

    /**
     * sets new y.
     * @param y new y value.
     */
    public void setY(double y) {
        this.upperLeft.setY(y);
    }

    /**
     * set a new color.
     * @param color new color value.
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
