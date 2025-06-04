package game.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;
import game.gui.shapes.Ball;
import game.gui.shapes.Velocity;
import game.gui.shapes.Rectangle;

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
                150, 150);
        Rectangle grayrect = new Rectangle(GRAYSTART, GRAYSTART, 450, 450);
        //input balls to an array and assign all the variables
        for (int i = 0; i < args.length; i++) {
            int radius = Integer.parseInt(args[i]) + 7;
            if (radius < 0) {
                radius = 20;
            }
            if (radius >= Math.min(WIDTHOFCANVAS, HEIGHTOFCANVAS) / 2
                    || radius >= Math.abs((GRAYEND - GRAYSTART) / 2)) {
                radius = 60;
            }
            double x;
            double y;
            if (i <= args.length / 2 - 1) { //first half is inside the gray
                double minX = GRAYSTART + radius;
                double maxX = GRAYEND - 3 * radius - 20;
                x = r.nextDouble() * (maxX - minX) + minX;

                double minY = GRAYSTART + radius;
                double maxY = GRAYEND - 3.5 * radius - 20;
                y = r.nextDouble() * (maxY - minY) + minY;
            } else { //second half outside of the gray frame
                x = (r.nextDouble() * (WIDTHOFCANVAS - 2 * radius - GRAYEND)) + GRAYEND;
                y = (r.nextDouble() * (HEIGHTOFCANVAS - 2 * radius - 150));
            }
            //check if is inside of yellow rectangle
            if (x > YELLOWSTART - 2 && x + radius < YELLOWEND + 2
                    && y > YELLOWSTART - 2 && y + radius < YELLOWEND + 2) {
                x = WIDTHOFCANVAS / 2.0;
                y = HEIGHTOFCANVAS / 2.0;
            }

            int angle = r.nextInt(360);
            double speed = Math.max(1, 50 / radius);
            Color color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            Ball b = new Ball(x, y, radius, color);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            b.setVelocity(v);
//            if (i <= args.length / 2 - 1) {
//                //b.setScreen(GRAYSTART, GRAYEND, GRAYSTART, GRAYEND);
//            } else {
//                //b.setScreen(0, WIDTHOFCANVAS, 0, HEIGHTOFCANVAS);
//            }
            balls[i] = b;
        }


        while (true) {
            DrawSurface d = gui.getDrawSurface();

            grayrect.drawOn(d);

            // check outside balls intersection
            for (int i = args.length / 2; i < args.length; i++) {
                balls[i].collideRectangle(grayrect);
            }

            for (Ball ball : balls) {
                ball.collideRectangle(yellowrect);
                //ball.moveOneStep();
                ball.drawOn(d);
            }

            yellowrect.drawOn(d);

            gui.show(d);
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(30);
        }

    }
}
