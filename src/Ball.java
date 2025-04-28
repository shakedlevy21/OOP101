import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Ball - class for balls.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity v;
    private GameEnvironment collidables;


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
        Line trajectory = new Line(center, new Point(center.getX() + v.getDx(), center.getY() + v.getDy()));
        CollisionInfo info = collidables.getClosestCollision(trajectory);
        if (info != null) {
            double dist = Math.sqrt(Math.pow(v.getDx(), 2) + Math.pow(v.getDy(), 2));
            if (Math.abs(info.collisionPoint().distance(center) - radius) < radius + dist) {
                //make a close velocity for the point to get closer to the collidable
                Velocity vel = new Velocity(Math.min(info.collisionPoint().distance(center), radius),
                        Math.min(info.collisionPoint().distance(center), radius));
                this.v.applyToPoint(this.center, vel);
                Collidable collidable = info.collisionObject();
                if (collidable != null) {

                    Velocity newvel = collidable.hit(info.collisionPoint(), this.v);
                    if (newvel != null) {
                        this.v = newvel;
                    }
                }
            }
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * not in use anymore.
     * collideRectangle - checks if this ball is colliding with a rectangle and changes its direction accordingly.
     * @param r - the rectangle to check with
     * @return - boolean if they collided or not (for future use maybe)
     */
    public boolean collideRectangle(Rectangle r) {
        // circle center correction because of the drawing of GUI
        double circlex = this.center.getX() + this.radius;
        double circley = this.center.getY() + this.radius;
        double radius = this.radius;
        // rectangle edge detect
        double rectleft   = r.getX();
        double rectright  = r.getX() + r.getWidth();
        double recttop    = r.getY();
        double rectbottom = r.getY() + r.getHeight();
        // closest edge on rectangle to circle
        double xtest = circlex;
        if (circlex < rectleft) { // left side
            xtest = rectleft;
        } else if (circlex > rectright) { // right side
            xtest = rectright;
        }
        double ytest = circley;
        if (circley < recttop) { // top side
            ytest = recttop;
        } else if (circley > rectbottom) { // bottom side
            ytest = rectbottom;
        }

        // distance from circle center to closest point
        double distx = circlex - xtest;
        double disty = circley - ytest;
        double distance = Math.sqrt(distx * distx + disty * disty);
        boolean iscollided = false;
        //check for intersection
        if (distance < radius) { //than collide and check which axis to change
            if (Math.abs(distx) > 0.00001) {
                this.setXminus();
                iscollided = true;
            }
            if (Math.abs(disty) > 0.00001) {
                this.setYminus();
                iscollided = true;
            }
            return iscollided;
        }
        return iscollided;
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
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillOval(getX() - radius, getY() - radius, getSize() * 2, getSize() * 2);
        surface.setColor(Color.BLACK);
        surface.drawOval(getX() - radius, getY() - radius, getSize() * 2, getSize() * 2);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Adds the block to the provided game.
     * @param g - game to add to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
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

    /**
     * set the environment for the ball to know all the collidables it should be aware of.
     * @param collidables
     */
    public void setEnvironment(GameEnvironment collidables) {
        this.collidables = collidables;
    }
}
