package game.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;
import game.gui.shapes.Ball;
import game.gui.shapes.Velocity;

/**
 * Task 3 - multiple balls in frame.
 */
public class MultipleBouncingBallsAnimation {
    private static final int WIDTHOFCANVAS = 800;
    private static final int HEIGHTOFCANVAS = 600;

    /**
     * main function for exec.
     * @param args - gets unlimited numbers from user which are the size of each ball.
     */
    public static void main(String[] args) {
        //check if the args[] is empty
        if (args.length < 1) {
            return;
        }
        GUI gui = new GUI("Bouncing Balls", WIDTHOFCANVAS, HEIGHTOFCANVAS);

        Ball[] balls = new Ball[args.length];
        Random r = new Random();
        for (int i = 0; i < args.length; i++) {
            int radius = Integer.parseInt(args[i]);
            if (radius < 0 || radius > WIDTHOFCANVAS) {
                radius = 10;
            }
            double x;
            double y;
            x = r.nextDouble() * (WIDTHOFCANVAS - 2 * radius - 10) + 10;
            y = r.nextDouble() * (HEIGHTOFCANVAS - 2 * radius - 10) + 10;

            int angle = r.nextInt(360);
            double speed = Math.max(1, 50 / radius);
            Color color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            Ball b = new Ball(x, y, radius, color);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            b.setVelocity(v);
            balls[i] = b;
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();  // Get it ONCE per frame

            for (Ball ball : balls) {
                //ball.moveOneStep();
                ball.drawOn(d);
            }

            gui.show(d);
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(20);
        }

    }
}
