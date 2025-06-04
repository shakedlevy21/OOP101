package game.listener;
import game.gui.shapes.Ball;
import game.gui.shapes.Block;

/**
 * interface for hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit
     * @param hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
