import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Block - kind of rectangle and collidable.
 */
public class Block extends Rectangle implements Collidable, Sprite {
    /**
     * Constuctor - 1.
     * @param upperleft - point for upper left corner.
     * @param width - width of the block.
     * @param height - height of the block.
     */
    public Block(Point upperleft, double width, double height) {
        super(upperleft, width, height);
    }

    /**
     * Constuctor - 2.
     * @param x - coordinate for upper left corner.
     * @param y - coordinate for upper left corner.
     * @param width - width of the block.
     * @param height - height of the block.
     */
    public Block(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     * gets shape to collide with.
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint to check with
     * @param currentVelocity current velocity
     * @return is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // for easier reading.
        double leftX   = this.getUpperLeft().getX();
        double rightX  = this.getUpperLeft().getX() + this.getWidth();
        double upY    = this.getUpperLeft().getY();
        double downY = this.getUpperLeft().getY() + this.getHeight();
        Line left = new Line(leftX, upY, leftX, downY); // left line
        Line right = new Line(rightX, upY, rightX, downY); // right line
        Line top = new Line(leftX, upY, rightX, upY); // top line
        Line bottom = new Line(leftX, downY, rightX, downY); // bottom line
        Velocity newvel = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        //check if colliding on the left and right
        double setdist = 2;
        if (setdist > left.distancefromLine(collisionPoint)
                || setdist * 2 > right.distancefromLine(collisionPoint)) {
            newvel.setDx(-currentVelocity.getDx());
        }
        //check if colliding on the top and bottom
        if (setdist > top.distancefromLine(collisionPoint)
                || setdist * 2 > bottom.distancefromLine(collisionPoint)) {
            newvel.setDy(-currentVelocity.getDy());
        }
        return newvel; // return the new velocity for the object
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight()); // draw the outline and fill.
    }


    @Override
    public void timePassed() {
        //System.out.println("timePassed!");
    }

    /**
     * Adds the block to the provided game.
     * @param g - game to add to.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
