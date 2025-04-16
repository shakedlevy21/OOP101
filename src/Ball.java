import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Ball - class for balls.
 */
public class Ball {
    private Point center;
    private int radius;
    private Color color;
    private Velocity v;
    private int screenmaxwidth;
    private int screenminwidth;
    private int screenmaxheight;
    private int screenminheight;


    /**
     * Constructor.
     * @param center - the center point
     * @param r - the radius
     * @param color - color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * Constructor 2.
     * @param x - coordinate x
     * @param y - coordinate y
     * @param r - radius
     * @param color - color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * sets the screen in which the ball moves in.
     * @param maxwidth max width
     * @param minwidth min width
     * @param maxheight max height
     * @param minheight min height
     */
    public void setScreen(int minwidth, int maxwidth, int minheight, int maxheight) {
        this.screenmaxwidth = maxwidth;
        this.screenmaxheight = maxheight;
        this.screenminheight = minheight;
        this.screenminwidth = minwidth;
    }

    /**
     * Sets the velocity of the ball.
     * @param v - for the velocity to add
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball.
     * @param dx - delta x coordinates
     * @param dy - delta y coordinates
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * moveOneStep: checks edges and changes the center of the ball to the next frame.
     */
    public void moveOneStep() {
        //check edges
        //right and left
        if ((this.center.getX() + radius * 2) >= this.screenmaxwidth || this.center.getX() < screenminwidth) {
            this.v.setDx(-this.v.getDx());
        }
        //up and down
        if ((this.center.getY() + radius * 2) >= this.screenmaxheight || this.center.getY() < screenminheight) {
            this.v.setDy(-this.v.getDy());
        }
        //apply to point
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * collideRectangle - checks if this ball is colliding with a rectangle and changes its direction accordingly.
     * @param r - the rectangle to check with
     * @return - boolean if they collided or not
     */
    public boolean collideRectangle(Rectangle r) {
        // circle center correction because of the drawing of GUI
        double cx = this.center.getX() + this.radius;
        double cy = this.center.getY() + this.radius;
        double radius = this.radius;
        // rectangle edges
        double rectLeft   = r.getX();
        double rectRight  = r.getX() + r.getWidth();
        double rectTop    = r.getY();
        double rectBottom = r.getY() + r.getHeight();
        // closest point to circle
        double testX = cx;
        if (cx < rectLeft) {
            testX = rectLeft; // left side
        } else if (cx > rectRight) {
            testX = rectRight; // right side
        }
        double testY = cy;
        if (cy < rectTop) {
            testY = rectTop; // top side
        } else if (cy > rectBottom) {
            testY = rectBottom; // bottom side
        }

        // distance from circle center to closest point
        double distX = cx - testX;
        double distY = cy - testY;
        double distance = Math.sqrt(distX * distX + distY * distY);
        //check for intersection
        if (distance < radius) {
            boolean collided = false;
            if (Math.abs(distX) > 0.00001) {
                this.setXminus();
                collided = true;
            }
            if (Math.abs(distY) > 0.00001) {
                this.setYminus();
                collided = true;
            }
            return collided;
        }
        return false;
    }

    /*
    //////////////////////////////////////////////
                        GETS
    //////////////////////////////////////////////
     */

    /**
     * GetX.
     * @return X
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getY.
     * @return Y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize.
     * @return radius of ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * getCenter.
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * getColor.
     * @return Color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * getVelocity.
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillOval(getX(), getY(), getSize() * 2, getSize() * 2);
    }
    /*
    //////////////////////////////////////////////
                        SETS
    //////////////////////////////////////////////
     */

    /**
     * set a new radius (cases in which the radius does not fit the screen).
     * @param radius - new radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * changes the direction of the ball in case of collision.
     */
    public void setXminus() {
        this.v.setDx(-this.v.getDx());
    }
    /**
     * changes the direction of the ball in case of collision.
     */
    public void setYminus() {
        this.v.setDy(-this.v.getDy());
    }
}
