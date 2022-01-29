package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTestes{
    @Test
    public void  testDistance() {
        Point p1 = new Point(-1, 0);
        Point p2 = new Point(5, 1);
        Assert.assertEquals(Point.distance(p1, p2), 6.0);
    }
}
