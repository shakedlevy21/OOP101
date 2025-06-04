package game.listener;

import game.Game;
import game.gui.shapes.Ball;
import game.gui.shapes.Block;

/**
 * class for ball removing balls from the game.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game - game
     * @param remainingBlocks counter for blocks
     */
    public BallRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBalls = remainingBlocks;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit
     * @param hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        this.game.removeSprite(hitter);
    }
}
