package com.featurerequest.showstopper;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.graphics.PointF;
import android.view.View;


/**
 * Created by kannon on 8/29/2015.
 */
public abstract class SwipeListener extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener
{
    Context context;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public SwipeListener()
    {
        super();
    }

    public SwipeListener( Context c )
    {
        this.context = c;
    }

    @Override
    public boolean onFling( MotionEvent e1, MotionEvent e2, float velocityX, float velocityY )
    {
        //fast enough
        Point start = new Point( e1.getX(), e1.getY() );
        Point end = new Point( e2.getX(), e2.getY() );

        //find swipe type
        SwipeFinder.SWIPE_TYPE s = SwipeFinder.getSwipeType( start, end, SWIPE_MIN_DISTANCE );

        switch( s )
        {
            case LEFT:
                return onSwipeLeft();
            case RIGHT:
                return onSwipeRight();
            case UP:
                return onSwipeUp();
            case DOWN:
                return onSwipeDown();
            default:
                return false;
        }
    }

    @Override
    public boolean onTouch( View v, MotionEvent e )
    {
        return false;
    }

    public abstract boolean onSwipeLeft();
    public abstract boolean onSwipeRight();
    public abstract boolean onSwipeUp();
    public abstract boolean onSwipeDown();
}
