/******************
 Name: Shaked Levy
 ID: 212730311
 Assignment: ass1
 *******************/
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.util.Random;
import java.awt.Color;

/**
 * Class BouncingBallAnimation for drawings a ball and animating it.
 */
public class BouncingBallAnimation {
    //constants for easier changes
    private static final int NUMOFLINES = 10;
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
        ball.setScreen(WIDTHOFCANVAS, 0, HEIGHTOFCANVAS, 0);
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
        if (args.length == 4) {
//            Random r = new Random();
//            int radius = Integer.parseInt(args[0]);
//            double x = r.nextDouble() * (WIDTHOFCANVAS - 2 * radius) + radius;
//            double y = r.nextDouble() * (HEIGHTOFCANVAS - 2 * radius) + radius;
//            int angle = r.nextInt(360);
//            double speed;
//            if (radius >= 50) {
//                speed = 1;
//            } else {
//                speed = radius * 0.5;
//            }
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            int dx = Integer.parseInt(args[2]);
            int dy = Integer.parseInt(args[3]);
            Point p = new Point(x, y);
            drawAnimation(p, dx, dy);
        } else {
            System.out.println("Please enter 4 numbers");
        }
    }
}
