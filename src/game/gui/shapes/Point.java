package game.gui.shapes;
/**
 * Point - Point?
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructor.
     * @param x coordinates
     * @param y coordinates
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /*
    //////////////////////////////////////////////
                    SIMPLE CALCULATIONS
    //////////////////////////////////////////////
     */

    /**
     * distance.
     * @param other point to check with
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        //formula - sqrt((x2 - x1)^2 + (y2 - y1))
        return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
    }

    // equals - return true is the points are equal, false otherwise

    /**
     * equals - checks if two points are the same 0.00001 radius.
     * @param other point to check with
     * @return true if the same, false if not
     */
    public boolean equals(Point other) {
        //round the 0.00001 point
        return Math.abs(this.x - other.x) < 0.00001 && Math.abs(this.y - other.y) < 0.00001;
    }

    /*
    //////////////////////////////////////////////
                        GETS
    //////////////////////////////////////////////
     */

    // Geters - Return the x and y values of this point

    /**
     * getX().
     * @return point's X value
     */
    public double getX() {
        return x;
    }

    /**
     * getY().
     * @return point's Y value
     */
    public double getY() {
        return y;
    }
    /*
    //////////////////////////////////////////////
                        SETS
    //////////////////////////////////////////////
     */

    /**
     * setX() - sets a new X value.
     * @param x - new X value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * setY() - sets a new Y value.
     * @param y - new Y value
     */
    public void setY(double y) {
        this.y = y;
    }
}
