import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * task 4 - Draws multiple balls on one screen.
 */
public class MultipleFramesBouncingBallsAnimation {

    private static final int WIDTHOFCANVAS = 800;
    private static final int HEIGHTOFCANVAS = 600;
    private static final int GRAYSTART = 50;
    private static final int GRAYEND = 500;
    private static final int YELLOWSTART = 450;
    private static final int YELLOWEND = 600;

    /**
     * main function for exec.
     * @param args - gets unlimited numbers from user which are the size of each ball.
     */
    public static void main(String[] args) {
        double epsilon = 0.001;
        //check if the args[] is empty
        if (args.length < 1) {
            return;
        }
        GUI gui = new GUI("Bouncing Balls", WIDTHOFCANVAS, HEIGHTOFCANVAS);

        Ball[] balls = new Ball[args.length];
        Random r = new Random();

        Rectangle yellowrect = new Rectangle(YELLOWSTART, YELLOWSTART,
                150, 150, Color.YELLOW);
        for (int i = 0; i < args.length; i++) {
            int radius = Integer.parseInt(args[i]);
            if (radius < 0) {
                radius = 20;
            } else if (radius >= Math.min(WIDTHOFCANVAS, HEIGHTOFCANVAS)
                    || radius >= Math.abs(GRAYEND - GRAYSTART)) {
                radius = radius / 10;
            }
            double x;
            double y;
            if (i <= args.length / 2 - 1) { //first half is inside the gray
                double minX = GRAYSTART + radius;      // at least radius from the left
                double maxX = GRAYEND - 3 * radius;    // 3 radii away from the right edge
                x = r.nextDouble() * (maxX - minX) + minX;

                double minY = GRAYSTART + radius;      // at least radius from the top
                double maxY = GRAYEND - 3 * radius;    // 3 radii away from the bottom edge
                y = r.nextDouble() * (maxY - minY) + minY;
            } else { //second half outside of the gray frame
                x = (r.nextDouble() * (WIDTHOFCANVAS - 2 * radius)) + radius;
                y = (r.nextDouble() * (HEIGHTOFCANVAS - 2 * radius)) + radius;
            }
            //check if is inside of yellow rectangle
            if (x > YELLOWSTART && x + radius < YELLOWEND && y > YELLOWSTART && y + radius < YELLOWEND) {
                x = WIDTHOFCANVAS / 2.0;
                y = HEIGHTOFCANVAS / 2.0;
            }

            int angle = r.nextInt(360);
            double speed = Math.max(1, 50 / radius);
            Color color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            Ball b = new Ball(x, y, radius, color);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            b.setVelocity(v);
            if (i <= args.length / 2 - 1) {
                b.setScreen(GRAYSTART, GRAYEND, GRAYSTART, GRAYEND);
            } else {
                b.setScreen(0, WIDTHOFCANVAS, 0, HEIGHTOFCANVAS);
            }
            balls[i] = b;
        }


        while (true) {
            DrawSurface d = gui.getDrawSurface();

            d.setColor(Color.GRAY);
            d.fillRectangle(GRAYSTART, GRAYSTART, 450, 450);


            for (Ball ball : balls) {
                ball.collideRectangle(yellowrect);
                ball.moveOneStep();
                ball.drawOn(d);
            }

            yellowrect.drawOn(d);

            gui.show(d);
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(30);
        }

    }
}
