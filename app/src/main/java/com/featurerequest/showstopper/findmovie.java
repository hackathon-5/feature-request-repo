package com.featurerequest.showstopper;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Toast;

public class findmovie extends Activity implements GestureDetector.OnGestureListener {

    private int titleCount = 1;
    private MovieInfo frag_movie_info;
    private SwipeListener swipeListener;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findmovie);
        mDetector = new GestureDetector(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_findmovie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent( MotionEvent e )
    {
        this.mDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
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
        Log.i("onScroll", "got here");
        return true;
    }

    public boolean onFling( MotionEvent e1, MotionEvent e2, float velocityX, float velocityY )
    {
        Log.i("onFling", "got here");
        return true;
    }

    public void generateContent(View view){
        frag_movie_info = new MovieInfo();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        frag_movie_info.setTitle("SampleTitle" + titleCount);
        frag_movie_info.setGenre("SampleGenre");
        frag_movie_info.setYear("SampleYear");
        frag_movie_info.setLength("SampleLength");
        frag_movie_info.setSynopsis("SampleSynopsis");
        frag_movie_info.setURL("SampleURL");
        frag_movie_info.setType("SampleType");
        ft.replace(R.id.frag_content, frag_movie_info);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        titleCount++;
    }

    public void selectContent(View view) {
        Intent intent = new Intent(this, showContent.class);
        intent.putExtra("Title", frag_movie_info.getTitle());
        intent.putExtra("Genre", frag_movie_info.getGenre());
        intent.putExtra("Year", frag_movie_info.getYear());
        intent.putExtra("Length", frag_movie_info.getLength());
        intent.putExtra("Synopsis", frag_movie_info.getSynopsis());
        intent.putExtra("URL", frag_movie_info.getURL());
        intent.putExtra("Type", frag_movie_info.getType());
        startActivity(intent);
    }
}
