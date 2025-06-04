package game.gui.sprites;

import biuoop.DrawSurface;

/**
 * Interface for addressing objects in the game.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d - draw surface
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
