package game.listener;

import game.gui.shapes.Ball;
import game.gui.shapes.Block;

/**
 * help function to check hit listener.
 */
public class PrintingHitListener implements HitListener {
    /**
     * hit event.
     * @param beingHit block that being hit.
     * @param hitter ball that hits.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
