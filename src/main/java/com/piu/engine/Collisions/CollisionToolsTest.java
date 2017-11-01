package com.piu.engine.Collisions;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;


public class CollisionToolsTest {

    @Test
    public void getIntersectionOrNull_returnsNotNull() {
        Point res = CollisionTools.getIntersectionOrNull(
                new Point(), new Point(10,10), new Point(0,10), new Point(10,0));
        Assert.assertNotNull(res);
    }

  //  @Test
    public void getIntersectionOrNull_OnePointIntersectionOnSegment_returnsNotNull() {
        Point res = CollisionTools.getIntersectionOrNull(
                new Point(), new Point(0,10), new Point(0,5), new Point(10,5));
        Assert.assertNotNull(res);
    }
  //  @Test
    public void getIntersectionOrNull_OnePointIntersection_returnsNotNull() {
        Point res = CollisionTools.getIntersectionOrNull(
                new Point(), new Point(0,10), new Point(0,0), new Point(10,0));
        Assert.assertNotNull(res);
    }
  //  @Test
    public void getIntersectionOrNull_OnePointIntersectionOnDiagonal_returnsNotNull() {
        Point res = CollisionTools.getIntersectionOrNull(
                new Point(), new Point(10,10), new Point(10,10), new Point(20,20));
        Assert.assertNotNull(res);
    }
    @Test
    public void getIntersectionOrNull_returnsCorrectPoint() {
        Point res = CollisionTools.getIntersectionOrNull(
                new Point(), new Point(10,10), new Point(0,10), new Point(10,0));
        Assert.assertNotNull(res);
        Assert.assertEquals(5,res.x);
        Assert.assertEquals(5,res.y);

    }
    @Test
    public void getIntersectionOrNull_returnsNull() {
        Point res = CollisionTools.getIntersectionOrNull(
                new Point(),new Point(10,10), new Point(-10,10), new Point(-20,-20));
        Assert.assertNull(res);
    }
    @Test
    public void getIntersectionOrNull_segmentsAreOnTheLine_returnsNull() {
        Point res = CollisionTools.getIntersectionOrNull(
                new Point(),new Point(10,0), new Point(11,0), new Point(20,0));
        Assert.assertNull(res);
    }
    @Test
    public void getIntersectionOrNull_segmentsAreOnTheDiagonalLine_returnsNull() {
        Point res = CollisionTools.getIntersectionOrNull(
                new Point(),new Point(10,10), new Point(11,11), new Point(20,20));
        Assert.assertNull(res);
    }
    @Test
    public void getIntersectionOrNull_notFails() {
        CollisionTools.getIntersectionOrNull(
                new Point(), new Point(), new Point(1,1), new Point(1,1));
    }

}