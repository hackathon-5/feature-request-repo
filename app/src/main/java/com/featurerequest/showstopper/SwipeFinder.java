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

        double xMovement = end.x - start.x;
        double yMovement = end.y - start.y;

        if( xMovement >= yMovement )
            return xMovement > 0 ? SWIPE_TYPE.RIGHT : SWIPE_TYPE.LEFT;

        return yMovement > 0 ? SWIPE_TYPE.UP : SWIPE_TYPE.DOWN;
    }
}
