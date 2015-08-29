package com.featurerequest.showstopper;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Toast;

public class findmovie extends Activity{

    private int titleCount = 1;
    private MovieInfo frag_movie_info;
    private SwipeListener swipeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findmovie);
        swipeListener = new SwipeListener(this.getApplicationContext()) {
            @Override
            public boolean onSwipeLeft() {
                Toast.makeText( this.context, "Left", Toast.LENGTH_LONG ).show();
                return true;
            }

            @Override
            public boolean onSwipeRight() {
                Toast.makeText( this.context, "Right", Toast.LENGTH_LONG ).show();
                return true;
            }

            @Override
            public boolean onSwipeUp() {
                Toast.makeText( this.context, "Up", Toast.LENGTH_LONG ).show();
                return true;
            }

            @Override
            public boolean onSwipeDown() {
                Toast.makeText( this.context, "Down", Toast.LENGTH_LONG ).show();
                return true;
            }
        };
        this.findViewById(android.R.id.content).setOnTouchListener(swipeListener);
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
