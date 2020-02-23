import java.util.ArrayList;
import java.util.Arrays;

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
    /**
     * Coordinates of LineSegments.
     */
    private LineSegment[] lineSegCoords;
    /**
     * ArrayList of Line Segments.
     */
    private ArrayList<LineSegment> segs;

    /**
     * Solves collinear points problem with brute force.
     * @param points points to be passed
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null || hasNull(points) || hasDup(points)) throw new IllegalArgumentException("Null point array");
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        segs = new ArrayList<>();
        for (int p = 0; p < pointsCopy.length - 3; p++) {
            for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                for (int r = q + 1; r < pointsCopy.length - 1; r++) {
                    for (int s = r + 1; s < pointsCopy.length; s++) {
                        if ((pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]))
                                && (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])
                                && ((pointsCopy[p].slopeTo(pointsCopy[r]) == (pointsCopy[p].slopeTo(pointsCopy[s])))))) {
                            LineSegment temp = new LineSegment(pointsCopy[p], pointsCopy[s]);
                            if (!segs.contains(temp)) {
                                segs.add(temp);
                            }

                        }
                    }
                }
            }
        }
        lineSegCoords = segs.toArray(new LineSegment[0]);
        // finds all line segments containing 4 points
    }

    /**
     * Returns number of segments.
     * @return num of segments
     */
    public int numberOfSegments() {
        // the number of line segments
        return lineSegCoords.length;
    }

    /**
     * Checks if the point array has a null element.
     * @param points points to be passed in
     * @return true if has a null element
     */
    private boolean hasNull(Point[] points) {
        for (Point p : points) {
            if (p == null) return true;
        }
        return false;
    }

    /**
     * Checks if point array has a duplicate.
     * @param points points to be passed
     * @return true if has a duplicate
     */
    private boolean hasDup(Point[] points) {
        Point[] pointcopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointcopy);

        Point prev = null;
        for (Point point : pointcopy) {
            if (prev != null && point.compareTo(prev) == 0) {
                return true;
            }
            prev = point;
        }
        return false;
    }
    /**
     * Returns specfic coordinates of segments.
     * @return line segment array
     */
    public LineSegment[] segments() {
        // the line segments
        return segs.toArray(new LineSegment[0]);
    }
}