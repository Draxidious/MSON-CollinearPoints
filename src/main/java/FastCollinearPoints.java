import java.util.Arrays;

/*************************************************************************
 *  Compilation:  javac-algs4 FastCollinearPoints.java
 *  Execution:    none
 *  Dependencies: Point.java LineSegment.java
 *
 *   Given a point p, the following method determines 
 *   whether p participates in a set of 4 or more collinear points.
 *   Think of p as the origin.
 *   - For each other point q, determine the slope it makes with p.
 *   - Sort the points according to the slopes they makes with p.
 *      -Use Arrays.sort with your slopeOrder comparator
 *   - Check if any 3 (or more) adjacent points in 
 *      the sorted order have equal slopes with respect to p. 
 *      If so, these points, together with p, are collinear.
 *
 *************************************************************************/
public class FastCollinearPoints {
    private int lineSegs = 0;
    private LineSegment[] lineSegCoords;

    public FastCollinearPoints(Point[] points) {
        lineSegCoords = new LineSegment[points.length];
        int index = 0;
        for (int i = 0; i < points.length; i++) {
            Point[] adjpoints = new Point[points.length - i];
            for (int x = i; x < points.length; x++) {
                adjpoints[i] = adjpoints[x];
            }
            Arrays.sort(adjpoints, points[i].slopeOrder());
            for (int y = 0; y < adjpoints.length - 3; y++) {
                int move = 1;
                while (points[i].slopeTo(adjpoints[y]) == points[i].slopeTo(adjpoints[y + move])) {
                    move++;
                }
                if (move >= 3) {
                    lineSegCoords[index] = new LineSegment(points[i], adjpoints[y + move]);
                    index++;
                }

            }
        }

    }

    public int numberOfSegments() {
        return lineSegs;
    }

    public LineSegment[] segments() {
        return lineSegCoords;
    }
}