package game.listener;
import game.gui.shapes.Block;
import game.gui.shapes.Ball;
import game.Game;

/**
 * class for block removing.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game current game
     * @param remainingBlocks counter for the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * Remember to remove this listener from the block that is being removed from the game.
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decrease(1);
        this.game.removeCollidable(beingHit);
        this.game.removeSprite(beingHit);
        hitter.setColor(beingHit.getColor());
    }
}