package game.gui.sprites;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * Interface for a collection (array list) of sprites.
 */
public class SpriteCollection {
    private java.util.List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * add a new sprite to array list.
     * @param s - sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            if (s != null) {
                s.timePassed();
            }
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d - draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }


    /**
     * removes a sprite.
     * @param s to remove
     * @return the removed sprite and null if does not exist.
     */
    public Sprite removeSprite(Sprite s) {
        this.sprites.remove(s);
        return s;
    }
}
