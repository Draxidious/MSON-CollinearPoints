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
        lineSegCoords = segs.toArray(new LineSegment[segs.size()]);
        // finds all line segments containing 4 points
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegCoords.length;
    }

    private boolean hasNull(Point[] points) {
        for (Point p : points) {
            if (p == null) return true;
        }
        return false;
    }

    public boolean hasDup(Point[] points) {
        Point[] pointcopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointcopy);

        Point prev = null;
        for (int i = 0; i < pointcopy.length; i++) {
            if (prev != null && pointcopy[i].compareTo(prev) == 0) {
                return true;
            }
            prev = pointcopy[i];
        }
        return false;
    }

    public LineSegment[] segments() {
        // the line segments
        return segs.toArray(new LineSegment[segs.size()]);
    }
}