package com.featurerequest.showstopper;

/**
 * Created by kannon on 8/29/2015.
 */
public class Point
{
    double x;
    double y;

    public Point( double x, double y )
    {
        this.x = x;
        this.y = y;
    }

    public double distance( Point p )
    {
        double xDis = p.x - this.x;
        double yDis = p.y - this.y;
        return Math.sqrt( xDis*xDis + yDis*yDis );
    }

}
