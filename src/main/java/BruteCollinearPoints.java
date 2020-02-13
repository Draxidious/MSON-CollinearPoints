/*************************************************************************
 *  Compilation:  javac-algs4 BruteCollinearPoints.java
 *  Execution:    none
 *  Dependencies: Point.java LineSegment.java
 *
 *   A program that examines 4 points at a time 
 *   and checks whether they all lie on the same line segment, 
 *   returning all such line segments. 
 *   To check whether the 4 points p, q, r, and s are collinear, 
 *   check whether the three slopes between p and q, 
 *   between p and r, and between p and s are all equal.
 *
 *************************************************************************/
public class BruteCollinearPoints {
    private int lineSegs = 0;
    private LineSegment[] lineSegCoords;

    public BruteCollinearPoints(Point[] points) {
        lineSegCoords = new LineSegment[points.length];
        int index = 0;
        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if ((points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]))
                                && (points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])
                                && ((points[p].slopeTo(points[r]) == (points[p].slopeTo(points[s])))))) {

                            lineSegCoords[index] = new LineSegment(points[p], points[s]);
                            index++;
                        }
                    }
                }
            }
        }
        // finds all line segments containing 4 points
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegs;
    }

    public LineSegment[] segments() {
        // the line segments
        return lineSegCoords;
    }
}