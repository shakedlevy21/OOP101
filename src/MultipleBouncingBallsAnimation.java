import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * Draws multiple balls on one screen
 */
public class MultipleBouncingBallsAnimation {

    private static final int WIDTHOFCANVAS = 800;
    private static final int HEIGHTOFCANVAS = 600;
    private static final int GRAYSTART = 50;
    private static final int GRAYEND = 500;
    private static final int YELLOWSTART = 450;
    private static final int YELLOWEND = 600;

    public static void main(String[] args) {
        //check if the args[] is empty
        if (args.length < 1) {
            return;
        }
        GUI gui = new GUI("Bouncing Balls", WIDTHOFCANVAS, HEIGHTOFCANVAS);

        Ball[] balls = new Ball[args.length];
        Random r = new Random();
        for (int i = 0; i < args.length; i++) {
            int radius = Integer.parseInt(args[i]) + 30;
            double x;
            double y;
            if (i <= args.length / 2 - 1) {
                x = r.nextDouble() * (GRAYEND - 2 * radius) + GRAYSTART;
                y = r.nextDouble() * (GRAYEND - 2 * radius) + GRAYSTART;
            } else {
                x = r.nextDouble() * (WIDTHOFCANVAS - 2 * radius) + radius;
                y = r.nextDouble() * (HEIGHTOFCANVAS - 2 * radius) + radius;
            }

            int angle = r.nextInt(360);
            double speed = Math.max(1, 50 / radius * 3);
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
            DrawSurface d = gui.getDrawSurface();  // Get it ONCE per frame

            d.setColor(Color.GRAY);
            d.fillRectangle(GRAYSTART, GRAYSTART, 450, 450);


            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }

            d.setColor(Color.YELLOW);
            d.fillRectangle(YELLOWSTART, YELLOWSTART, 150, 150);

            gui.show(d);
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(20);
        }

    }
}
