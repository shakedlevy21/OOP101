package game.animation;

import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;
import game.gui.shapes.Line;
import game.gui.shapes.Point;



/**
 * AbstractArtDrawing - class for canvas drawing.
 */
public class AbstractArtDrawing {
    //constants for easier changes
    private static final int NUMOFLINES = 10;
    private static final int WIDTHOFCANVAS = 800;
    private static final int HEIGHTOFCANVAS = 600;

    //constants for changing the draw area
    private static final int MAXINTER = NUMOFLINES * NUMOFLINES;
    private static final int MINWIDTHAREA = (int) (0);
    private static final int MAXWIDTHAREA = (int) (WIDTHOFCANVAS);
    private static final int MINHEIGHTAREA = (int) (0);
    private static final int MAXHEIGHTAREA = (int) (HEIGHTOFCANVAS);

    /**
     * generates a new line with random coordinates.
     * @return the new line created
     */
    public static Line generateRandomLine() {
        Random rand = new Random();
        //random number min and max is min + rand.nextDouble() *(max - min)
        double startx = MINWIDTHAREA + rand.nextDouble() * (MAXWIDTHAREA - MINWIDTHAREA);
        double starty = MINHEIGHTAREA + rand.nextDouble() * (MAXHEIGHTAREA - MINHEIGHTAREA);
        double endx = MINWIDTHAREA + rand.nextDouble() * (MAXWIDTHAREA - MINWIDTHAREA);
        double endy = MINHEIGHTAREA + rand.nextDouble() * (MAXHEIGHTAREA - MINHEIGHTAREA);
        Point startpoint = new Point(startx, starty);
        Point endpoint = new Point(endx, endy);
        return new Line(startpoint, endpoint);
    }

    /**
     * draws a line in the canvas.
     * @param l the line with the coordinates to draw
     * @param d the canvas to draw on
     */
    public static void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.getStart().getX(), (int) l.getStart().getY(),
                   (int) l.getEnd().getX(), (int) l.getEnd().getY());
    }

    /**
     * Main - the main functions for drawing.
     * @param args arguments from the command line
     */
    public static void main(String[] args) {

        GUI gui = new GUI("Random Circles", WIDTHOFCANVAS, HEIGHTOFCANVAS);
        DrawSurface d = gui.getDrawSurface();

        //to keep track of the lines
        Line[] lines = new Line[NUMOFLINES];

        //to keep track of the coalitions and make the runtime faster
        Point[] coalitionpoints = new Point[MAXINTER];
        int coalitioncounter = 0;

        //create lines
        for (int i = 0; i < NUMOFLINES; ++i) {
            lines[i] = generateRandomLine();
        }

        //draw lines
        for (int i = 0; i < NUMOFLINES; ++i) {

            drawLine(lines[i], d);
            //check for coalitions
            for (int j = i + 1; j < NUMOFLINES; ++j) {
                if (lines[i].isIntersecting(lines[j])) {
                    //check for intersections
                    Point p = lines[i].intersectionWith(lines[j]);
                    if (p == null) {
                        System.out.println("ERROR");
                        break;
                    }

                    //adding intersections to the coalitonpoints array for red circles
                    coalitionpoints[coalitioncounter] = p;
                    coalitioncounter++;

                    //draw the coalition circles RED
                    d.setColor(Color.RED);
                    d.fillCircle((int) p.getX(), (int) p.getY(), 3);
                }
            }
        }

        //draw the triangle sections GREEN
        for (int i = 0; i < NUMOFLINES - 2; ++i) {
            for (int j = i + 1; j < NUMOFLINES - 1; ++j) {
                for (int k = j + 1; k < NUMOFLINES; ++k) {
                    if (lines[i].isIntersecting(lines[j], lines[k])) {

                        Point p1 = lines[i].intersectionWith(lines[j]);
                        Point p2 = lines[i].intersectionWith(lines[k]);
                        Point p3 = lines[j].intersectionWith(lines[k]);

                        d.setColor(Color.GREEN);
                        d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
                        d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p3.getX(), (int) p3.getY());
                        d.drawLine((int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());
                    }
                }
            }
        }

        //draw middle line points BLUE
        for (int i = 0; i < NUMOFLINES; ++i) {
            Point middle = lines[i].middle();
            d.setColor(Color.BLUE);
            d.fillCircle((int) middle.getX(), (int) middle.getY(), 3);
        }

        //draw all the coalition circles RED
        for (int i = 0; i < coalitioncounter; ++i) {
            Point p = coalitionpoints[i];
            d.setColor(Color.RED);
            d.fillCircle((int) p.getX(), (int) p.getY(), 3);
        }
        gui.show(d);
    }
}