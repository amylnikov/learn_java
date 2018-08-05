package ru.stqa.prt.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance(){
    Point p = new Point(2, 4, 6,7);
    Assert.assertEquals(p.distance(),5.0);
  }

  @Test
  public void testDistance1(){
    Point p = new Point(1, 1, 7,1);
    Assert.assertEquals(p.distance(),6.0);
  }

  @Test
  public void testDistance2(){
    Point p = new Point(9.999, 12.5, 0,0);
    Assert.assertEquals(p.distance(),16.00718591758089);
  }

}
