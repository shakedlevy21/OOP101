package game.listener;

import biuoop.DrawSurface;

/**
 * Counter class for updating remaining collidables.
 */
public class Counter {
    private int counter = 0;

    /**
     * add number to current count.
     * @param number to increase
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     * @param number to subtract
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     * @return current count.
     */
    public int getValue() {
        return counter;
    }

    /**
     * drawon.
     * @param d draw surface
     * @param x coordinates
     * @param y coordinates
     */
    public void draw(DrawSurface d, int x, int y) {
        d.drawText(x, y, "Score: " + this.counter, 20);
    }
}
