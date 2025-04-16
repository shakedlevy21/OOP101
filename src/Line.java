import biuoop.DrawSurface;

/**
 * Line - Line?
 */
public class Line {

    private final Point start;
    private final Point end;
    private double m;
    private double b;

    /*
    //////////////////////////////////////////////
                    CONSTRUCTORS
    //////////////////////////////////////////////
     */

    /**
     * Constructor.
     * @param start - gets an x and y points for the start of the line
     * @param end - gets an x and y points for the end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        this.b = this.start.getY() - (this.m * this.start.getX());
    }
    /**
     * Constructor.
     * @param x1 coordinates for start X
     * @param y1 coordinates for start Y
     * @param x2 coordinates for end X
     * @param y2 coordinates for end Y
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        this.b = this.start.getY() - (this.m * this.start.getX());
    }

    /*
    //////////////////////////////////////////////
                SIMPLE CALCULATIONS
    //////////////////////////////////////////////
     */

    /**
     * Length of the line.
     * @return the length of the line
     */
    public double length() {
        //uses the point's distance function
        return this.start.distance(this.end);
    }

    /**
     * Middle of the line.
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }

    /*
    //////////////////////////////////////////////
                    INTERSECTIONS
    //////////////////////////////////////////////
     */

    /**
     * help method for intersection to check intersecting for 3 points.
     * it checks the third point relative to the other points
     * (formula I found on Wiki).
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point 3
     * @return True if the points are colliding and False otherwise
     */
    private boolean intersecting(Point p1, Point p2, Point p3) {
        return (p3.getY() - p1.getY()) * (p2.getX() - p1.getX())
                > (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
        //positive if p3 is to the left of p1->p2 and negative if p3 is to the right
    }

    /**
     * isIntersecting - checks if two lines intersect.
     * @param other is the line to check with
     * @return boolean if they intersect or not, if they are the same it returns TRUE!
     */
    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        //check if has the same f(x) -> checks mx + b
        if (this.equals(other)) {
            //check if they intersect only once
            if (this.end.equals(other.getStart())) {
                double intersect = this.end.getX();
                if (this.start.getX() < intersect && other.end.getX() > intersect) {
                    return true;
                }
            } else if (this.start.equals(other.getEnd())) {
                double intersect = this.start.getX();
                if (this.start.getX() > intersect && other.start.getX() < intersect) {
                    return true;
                }
            }
        }
        //else check if intersecting with help function
        return intersecting(this.start, other.start, other.end)
                != intersecting(this.end, other.start, other.end)
                && intersecting(this.start, this.end, other.start)
                != intersecting(this.start, this.end, other.end);
    }
    /**
     *isIntersecting() checks with intersecting() help functions if this and 2 other lines intersect.
     * !!checks mainly for triangle!!
     * @param other1 - line 2
     * @param other2 - line 3
     * @return true if these 2 lines intersect with this line, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2) && other1.isIntersecting(other2);
    }


    /**
     * intersectionWith() takes two lines and finds the point in which they intersect.
     * using the determinant of them.
     * (formula I found on Wiki).
     * @param other - the second line to check with
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        //if they have the same slope
        if (this.equals(other)) {
            if (this.start.equals(other.getEnd())) {
                return new Point(this.start.getX(), this.start.getY());
            } else if (this.end.equals(other.getStart())) {
                return new Point(this.end.getX(), this.end.getY());
            }
        }
        //variables for easier reading
        double x1 = this.getStart().getX();
        double y1 = this.getStart().getY();
        double x2 = this.getEnd().getX();
        double y2 = this.getEnd().getY();
        double x3 = other.getStart().getX();
        double y3 = other.getStart().getY();
        double x4 = other.getEnd().getX();
        double y4 = other.getEnd().getY();

        //check not to divide by 0
        double divider = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (divider == 0) {
            return null; // Lines are parallel or coincident
        }
        //determinant
        double px = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) / divider;
        double py = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) / divider;
        //return the point
        return new Point(px, py);
    }

    /**
     * equals - checks if this and another line are the same.
     * @param other line to check with
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        //return this.m == other.m && this.b == other.b;
        return Math.abs(this.m - other.m) < 0.00001 && Math.abs(this.b - other.b) < 0.00001;
    }

    /*
    //////////////////////////////////////////////
                        GETS
    //////////////////////////////////////////////
     */

    /**
     * getStart().
     * @return the start point of the line
     */
    public Point getStart() {
        return start;
    }
    /**
     * getEnd().
     * @return the end point of the line
     */
    public Point getEnd() {
        return end;
    }
    /**
     * getM().
     * @return the M value -> f(x) = MX + B
     */
    public double getM() {
        return this.m;
    }
    /**
     * etB().
     * @return the B value -> f(x) = MX + B
     */
    public double getB() {
        return this.b;
    }

    /*
    //////////////////////////////////////////////
                        SETS
    //////////////////////////////////////////////
     */

    /**
     * setStart() - sets a new start point.
     * changes the M and B value accordingly
     * @param x new start X value
     * @param y new start Y value
     */
    public void setStart(double x, double y) {
        this.start.setX(x);
        this.start.setY(y);
        this.m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        this.b = this.start.getY() - (this.m * this.start.getX());
    }

    /**
     * setEnd() - sets a new end point.
     * changes the M and B value accordingly
     * @param x new end X value
     * @param y new end Y value
     */
    public void setEnd(double x, double y) {
        this.end.setX(x);
        this.end.setY(y);
        this.m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        this.b = this.start.getY() - (this.m * this.start.getX());
    }

    /**
     * drawLine - static function that draws the line on a given surface.
     * @param line - to draw
     * @param surface - to draw on
     */
    public static void drawLine(Line line, DrawSurface surface) {
        surface.drawLine((int) line.getStart().getX(), (int)  line.getStart().getY(),
                (int) line.getEnd().getX(), (int) line.getEnd().getY());
    }
}
