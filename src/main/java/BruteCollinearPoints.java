import javax.sound.sampled.Line;
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
    private LineSegment[] lineSegCoords;
    private ArrayList<LineSegment> segs;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Null point array");
        segs = new ArrayList<>();

        Arrays.sort(points);
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if ((points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]))
                                && (points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])
                                && ((points[p].slopeTo(points[r]) == (points[p].slopeTo(points[s])))))) {
                            LineSegment temp = new LineSegment(points[p], points[s]);
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

    public LineSegment[] segments() {
        // the line segments
        return segs.toArray(new LineSegment[segs.size()]);
    }
}