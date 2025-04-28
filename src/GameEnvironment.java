import java.util.ArrayList;

/**
 * Game Environment provides an array list of collidable to check with in the game, closest point to collision.
 */
public class GameEnvironment {
    private java.util.List<Collidable> gameCollidables = new ArrayList<Collidable>();
    /**
     * add the given collidable to the environment.
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        gameCollidables.add(c);
    }
    /**
     * Assume an object moving from line.start() to line.end().
     * @param trajectory a line from the point of the object to the next point it should be according to the velocity.
     * @return If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double distance = Double.MAX_VALUE;
        Point closestPoint = null;
        Collidable closestCollidable = null;
        for (Collidable c : gameCollidables) { // for each collidable we will check the closest distance.
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine((Rectangle) c);
            if (intersectionPoint != null) {
                double newdistance = trajectory.getStart().distance(intersectionPoint);
                if (newdistance < distance) {
                    distance = newdistance;
                    closestPoint = intersectionPoint;
                    closestCollidable = c;
                }
            }
        }
        if (closestPoint != null) {
            return new CollisionInfo(closestPoint, trajectory, closestCollidable);
        }
        return null;
    }

}
