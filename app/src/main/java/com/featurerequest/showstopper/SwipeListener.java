package com.featurerequest.showstopper;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.graphics.PointF;
import android.view.View;
import android.widget.Toast;


/**
 * Created by kannon on 8/29/2015.
 */
public abstract class SwipeListener implements GestureDetector.OnGestureListener
{
    Context context;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public SwipeListener( Context c )
    {
        this.context = c;
    }

    @Override
    public boolean onFling( MotionEvent e1, MotionEvent e2, float velocityX, float velocityY )
    {
        Toast.makeText(this.context, "Got to onFling event", Toast.LENGTH_LONG ).show();

        //fast enough
        Point start = new Point( e1.getX(), e1.getY() );
        Point end = new Point( e2.getX(), e2.getY() );
        Log.d( "Event1:", e1.getX() + ":" + e1.getY() );
        Log.d( "Event2:", e2.getX() + ":" + e2.getY() );

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
    public boolean onDown( MotionEvent e )
    {
        Log.i("onDown", "got here");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e)
    {
        Log.i("onShowPress", "got here");
    }

    @Override
    public void onLongPress( MotionEvent e )
    {
        Log.i("onLongPress", "got here");
    }

    @Override
    public boolean onSingleTapUp( MotionEvent e )
    {
        Log.i("onSingleTapUp", "got here");
        return true;
    }

    @Override
    public boolean onScroll( MotionEvent e1, MotionEvent e2, float distanceX, float distanceY )
    {
//        Log.i("onScroll", "got here");
        return true;
    }

    public abstract boolean onSwipeLeft();
    public abstract boolean onSwipeRight();
    public abstract boolean onSwipeUp();
    public abstract boolean onSwipeDown();
}
