/*************************************************************************
 *  Compilation:  javac Point.java
 *  
 *  Point in the plane.
 *
 *************************************************************************/

public class Point {
    private final static double SCALEX = 0.0001 * 1000.0;
    private final static double SCALEY = 0.0001 * 1000.0 * 1.3;

    private int x;     // x component
    private int y;     // y component

    public Point(int x, int y) { this.x = x; this.y = y; }


    // convert to string
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // return Euclidean distance between this and p
    public double distanceTo(Point p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx*dx + dy*dy);
    }


    // plot in turtle graphics
    public void draw() {
        Turtle.fly(x * SCALEX, y * SCALEY);
        Turtle.spot(2);
    }

    // draw line from this to q in Turtle graphics
    public void drawTo(Point q) {
        Point p = this;
        Turtle.fly(p.x * SCALEX, p.y * SCALEY);
        Turtle.go (q.x * SCALEX, q.y * SCALEY);
    }
}
