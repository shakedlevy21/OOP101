package game.listener;

/**
 * interface for hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl
     */
    void removeHitListener(HitListener hl);
}
