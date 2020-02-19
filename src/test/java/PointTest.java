import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class PointTest {
  Point p5_7, p10_10, p1_1;
  @Before
  public void setUp() throws Exception {
    p5_7 = new Point(5,7);
    p10_10 = new Point(10,10);
    p1_1 = new Point(1,1);
  }

  @Test
  public void testSlopeToNormal() {
    assertEquals(0.6, p5_7.slopeTo(p10_10), 0.01);
  }

  @Test
  public void testSlopeToHorizontal() {
    Point p1_10 = new Point(1, 10);
    assertEquals(0.0, p1_10.slopeTo(p10_10), 0.00001);
  }

  @Test
  public void testSlopeToVertical() {
    Point p10_1 = new Point(10, 1);
    assertEquals(Double.POSITIVE_INFINITY, p10_1.slopeTo(p10_10), 0.0001);
  }

  @Test
  public void testSlopeToEqual() {
    Point p10_10_2 = new Point(10, 10);
    assertEquals(Double.NEGATIVE_INFINITY, p10_10_2.slopeTo(p10_10), 0.0001);
  }



  @Test
  public void testCompareToNegative() {
    assertTrue(p5_7.compareTo(p10_10) < 0);
  }

  @Test
  public void testCompareToNegativeHorizontal() {
    Point p1_10 = new Point(1, 10);
    assertTrue(p1_10.compareTo(p10_10) < 0);
  }

  @Test
  public void testCompareToNegativeVertical() {
    Point p10_1 = new Point(10, 1);
    assertTrue(p10_1.compareTo(p10_10) < 0);
  }

  @Test
  public void testCompareToEqual() {
    Point p10_10_2 = new Point(10, 10);
    assertEquals(0, p10_10_2.compareTo(p10_10));
  }

  @Test
  public void testCompareToPositive() {
    assertTrue(p10_10.compareTo(p1_1) > 0);
  }

  @Test
  public void testCompareToPositiveHorizontal() {
    Point p1_10 = new Point(1, 10);
    assertTrue(p10_10.compareTo(p1_10) > 0);
  }

  @Test
  public void testCompareToPositiveVertical() {
    Point p10_1 = new Point(10, 1);
    assertTrue(p10_10.compareTo(p10_1) > 0);
  }

  @Test
  public void testSlopeOrder() {
    Point[] arr = new Point[7];
    Point p0_0 = new Point(0,0);
    Comparator<Point> comp = p0_0.slopeOrder();
    Point p1_1 = new Point(1,1);// slope of 1
    arr[0] = p1_1;
    Point p1_3  = new Point(1,3); // slope of 3
    arr[1] = p1_3;
    Point p5_4  = new Point(5,4);// 0.8
    arr[2] = p5_4;
    Point p8_1  = new Point(8,1); // 0.125
    arr[3] = p8_1;
    Point p9_3  = new Point(9,3);// 0.333
    arr[4] = p9_3;
    Point p2_5  = new Point(2,5); //2.5
    arr[5] = p2_5;
    Point p3_1  = new Point(3,1);// 0.333
    arr[6] = p3_1;
    Arrays.sort(arr,comp);
    System.out.println(Arrays.toString(arr));
  }

}
