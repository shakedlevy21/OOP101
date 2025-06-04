package game.animation;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.util.Random;
import java.awt.Color;
import game.gui.shapes.Ball;
import game.gui.shapes.Point;

/**
 * task 2 - Class BouncingBallAnimation for drawings a ball and animating it.
 */
public class BouncingBallAnimation {
    //constants for easier changes
    private static final int WIDTHOFCANVAS = 400;
    private static final int HEIGHTOFCANVAS = 400;


    private static void drawAnimation(Point start, double dx, double dy) {
        Random r = new Random();
        GUI gui = new GUI("Bouncing Balls", WIDTHOFCANVAS, HEIGHTOFCANVAS);
        Sleeper sleeper = new Sleeper();
        int size = r.nextInt(80);
        Color color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        Ball ball = new Ball(start.getX(), start.getY(), size, color);
        //Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        ball.setVelocity(dx, dy);
        //ball.setScreen(0, WIDTHOFCANVAS, 0, HEIGHTOFCANVAS);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(20);
            // wait for 50 milliseconds.
        }
    }

    /**
     * Main - the main functions for drawing.
     *
     * @param args arguments from the command line
     */
    public static void main(String[] args) {
        if (args.length >= 4) {
            double x = Double.parseDouble(args[0]);
            if (x < 0 || x > WIDTHOFCANVAS) {
                x = WIDTHOFCANVAS / 2.0;
            }
            double y = Double.parseDouble(args[1]);
            if (y < 0 || y > HEIGHTOFCANVAS) {
                y = HEIGHTOFCANVAS / 2.0;
            }
            int dx = Integer.parseInt(args[2]);
            if (dx < -50 || dx > 50) {
                dx = 5;
            }
            int dy = Integer.parseInt(args[3]);
            if (dy < -50 || dy > 50) {
                dy = 5;
            }
            Point p = new Point(x, y);
            drawAnimation(p, dx, dy);
        } else {
            System.out.println("Please enter 4 numbers");
        }
    }
}
