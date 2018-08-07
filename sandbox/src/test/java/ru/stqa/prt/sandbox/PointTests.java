package ru.stqa.prt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance(){
    Point p1 = new Point(2, 4);
    Point p2 = new Point(6, 7);
    Assert.assertEquals(p1.distance(p2),5.0);
  }

  @Test
  public void testDistance1(){
    Point p1 = new Point(1, 1);
    Point p2 = new Point(7, 1);
    Assert.assertEquals(p1.distance(p2),6.0);
  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(9.999, 12.5);
    Point p2 = new Point (0, 0);
    Assert.assertEquals(p1.distance(p2),16.00718591758089);
  }

}
