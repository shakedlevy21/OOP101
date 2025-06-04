package game.gui.sprites;

import game.gui.shapes.Point;
import game.gui.shapes.Line;

/**
 * A class to provide all the information for the collision.
 */
public class CollisionInfo {
    private Point closestPoint;
    private Line trajectory;
    private Collidable collidable;

    /**
     * Constructor.
     * @param closestPoint - closest point to collision.
     * @param trajectory - the line in which the object should travel to.
     * @param collidable - the collidable object it should collide with.
     */
    public CollisionInfo(Point closestPoint, Line trajectory, Collidable collidable) {
        this.closestPoint = closestPoint;
        this.trajectory = trajectory;
        this.collidable = collidable;
    }

    /**
     * the point at which the collision occurs.
     * @return closest point of collision.
     */
    public Point collisionPoint() {
        return this.closestPoint;
    }

    /**
     * the collidable object involved in the collision.
     * @return the object of the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }

    /**
     * The trajectory line of the current object.
     * @return trajectory line.
     */
    public Line trajectory() {
        return this.trajectory;
    }
}
