import javax.sound.sampled.Line;
import java.util.ArrayList;

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

    public BruteCollinearPoints(Point[] points) {
        ArrayList<LineSegment> segs = new ArrayList<>();
        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if ((points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]))
                                && (points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])
                                && ((points[p].slopeTo(points[r]) == (points[p].slopeTo(points[s])))))) {

                            segs.add(new LineSegment(points[p], points[s]));
                        }
                    }
                }
            }
        }
        lineSegCoords = new LineSegment[segs.size()];
        lineSegCoords = segs.toArray(lineSegCoords);
        // finds all line segments containing 4 points
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegCoords.length;
    }

    public LineSegment[] segments() {
        // the line segments
        return lineSegCoords;
    }
}