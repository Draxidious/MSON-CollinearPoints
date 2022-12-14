
import java.util.ArrayList;
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
    /**
     * LineSegment Coordinates.
     */
    private LineSegment[] lineSegCoords;
    /**
     * ArrayList for segments.
     */
    private ArrayList<LineSegment> segs;

    /**
     * Main constructor to solve collinear points faster.
     * @param points points
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null || hasNull(points) || hasDup(points)) throw new IllegalArgumentException("Null point array");
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        segs = new ArrayList<>();
        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length; i++) {
            Point[] adjpoints = Arrays.copyOf(pointsCopy, pointsCopy.length);
            Arrays.sort(adjpoints);
            Arrays.sort(adjpoints, pointsCopy[i].slopeOrder());
            Point origin = pointsCopy[i];
            Double slopComp = null; // used to compare the slope

            int length = 1;
            /*
             * StartIndex is the index in which the line started to build
             * If the length is long enough, then the endpoint of segment
             * equals adjpoints[x+length]
             */
            int startIndex = -1;

            for (int x = 0; x < adjpoints.length; x++) {
                if (adjpoints[x] == pointsCopy[i]) continue;
                if (slopComp == null) {
                    slopComp = origin.slopeTo(adjpoints[x]);
                    startIndex = x;
                    length++;
                } else if (origin.slopeTo(adjpoints[x]) == slopComp) {
                    length++;
                    if (x + 1 == adjpoints.length) {
                        if (length >= 4) {
                            if (adjpoints[startIndex].compareTo(origin) > 0) {
                                segs.add(new LineSegment(origin, adjpoints[startIndex + length - 2]));
                            }
                        }
                    }
                    // if it gets here, then the slope was different
                } else if (length >= 4) {
                    if (adjpoints[startIndex].compareTo(origin) > 0) {
                        segs.add(new LineSegment(origin, adjpoints[startIndex + length - 2]));
                    }
                    slopComp = origin.slopeTo(adjpoints[x]);
                    startIndex = x;
                    length = 2;
                } else {
                    slopComp = origin.slopeTo(adjpoints[x]);
                    startIndex = x;
                    length = 2;
                }

            }
        }
        lineSegCoords = new LineSegment[segs.size()];
        lineSegCoords = segs.toArray(lineSegCoords);

    }

    /**
     * Checks if point array has a null element.
     * @param points points
     * @return true if has null
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
     * Returns number of segments.
     * @return num of segments
     */
    public int numberOfSegments() {
        return lineSegCoords.length;
    }
    /**
     * Returns specfic coordinates of segments.
     * @return line segment array
     */
    public LineSegment[] segments() {
        return segs.toArray(new LineSegment[0]);
    }
}