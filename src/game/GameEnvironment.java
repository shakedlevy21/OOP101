package game;

import java.util.ArrayList;

import game.gui.shapes.Point;
import game.gui.shapes.Line;
import game.gui.shapes.Rectangle;
import game.gui.sprites.Collidable;
import game.gui.sprites.CollisionInfo;

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

    /**
     * get a collidable.
     * @param collidable to search for
     * @return the collidable or null if does not exist.
     */
    public Collidable getCollidable(Collidable collidable) {
        for (Collidable c : this.gameCollidables) {
            if (c.equals(collidable)) {
                return c;
            }
        }
        return null;
    }

    /**
     * removes a collidable.
     * @param c to remove
     * @return the removed collidable and null if does not exist
     */
    public Collidable removeCollidable(Collidable c) {
        this.gameCollidables.remove(c);
        return c;
    }

}
