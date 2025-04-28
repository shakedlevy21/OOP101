/**
 * Interface for collidable objects.
 */
public interface Collidable {
    /**
     * gets shape to collide with.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint to check with
     * @param currentVelocity current velocity
     * @return is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
