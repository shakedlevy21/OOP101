/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constuctor.
     * @param dx - delta x coordinates
     * @param dy - delta y coordinates
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * fromAngleAndSpeed: converts angle in degrees to x and y in relation to (0, 0) and multiplies by scalar(speed).
     * @param angle - degrees in which direction to face (AntiClockWise!!!)
     * @param speed - Scalar for speed
     * @return new velocity coordinates to add
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with dx dy position.
     * @param p - the point to change
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(this.dx + p.getX(), this.dy + p.getY());
    }
    /*
    //////////////////////////////////////////////
                        SETS
    //////////////////////////////////////////////
     */

    /**
     * set Dx.
     * @param dx new Dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * set Dy.
     * @param dy new Dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /*
    //////////////////////////////////////////////
                        GETS
    //////////////////////////////////////////////
     */

    /**
     * getDx.
     * @return Dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get Dy.
     * @return Dy
     */
    public double getDy() {
        return this.dy;
    }
}
