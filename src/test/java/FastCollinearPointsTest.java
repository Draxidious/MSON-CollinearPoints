import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class FastCollinearPointsTest {
  
  FastCollinearPoints fcp;
  @Before
  public void setUp() throws Exception {
    fcp = generateFCP("input10.txt");
  }
  
  private FastCollinearPoints generateFCP(String filename) {
    In in = new In("collinear-test-files/" + filename);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    } 
    return new FastCollinearPoints(points);
  }

  @Test
  public void testNumberOfSegments() { // Add a timeout so you can test speed
    FastCollinearPoints bcptest = generateFCP("input6.txt");
    assertEquals(1,bcptest.numberOfSegments());
    bcptest = generateFCP("input8.txt");
    assertEquals(2,bcptest.numberOfSegments());
    bcptest = generateFCP("input9.txt");
    assertEquals(1,bcptest.numberOfSegments());
  }

  @Test
  public void testSegments() {
    Point[] arr = new Point[8];
    Point p0_0 = new Point(0, 0);
    arr[0] = p0_0;
    Point p1_1 = new Point(1, 1);
    arr[1] = p1_1;
    Point p1_3 = new Point(2, 2);
    arr[2] = p1_3;
    Point p5_4 = new Point(3, 3);
    arr[3] = p5_4;
    Point p8_1 = new Point(5, 5);
    arr[4] = p8_1;
    Point p9_3 = new Point(4, 4);
    arr[5] = p9_3;
    Point p2_5 = new Point(3, 3);
    arr[6] = p2_5;
    Point p3_1 = new Point(2, 2);
    arr[7] = p3_1;
    BruteCollinearPoints bcptest = new BruteCollinearPoints(arr);
    assertEquals(2,bcptest.numberOfSegments());
    // add one that directly tests segments when you get the stuff working

  }

}
