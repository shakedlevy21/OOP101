import java.util.ArrayList;
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
        for (Sprite s : sprites) {
            s.timePassed();
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
}
