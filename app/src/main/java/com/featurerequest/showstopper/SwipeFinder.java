package com.featurerequest.showstopper;

/**
 * Created by kannon on 8/29/2015.
 */
public class SwipeFinder
{
    public enum SWIPE_TYPE { NONE, LEFT, RIGHT, UP, DOWN };

    public static SWIPE_TYPE getSwipeType( Point start, Point end, double minDistance )
    {
        if( start.distance(end) < minDistance )
            return SWIPE_TYPE.NONE;

        double xDis = Math.abs( end.x - start.x );
        double yDis = Math.abs( end.y - start.y );

        //more horixontal movement
        if( xDis >= yDis )
        {
            if( start.x > end.x )
                return SWIPE_TYPE.LEFT;
            return SWIPE_TYPE.RIGHT;
        }

        //more vertical
        if( end.y > start.y )
            return SWIPE_TYPE.DOWN;
        return SWIPE_TYPE.UP;
    }
}
