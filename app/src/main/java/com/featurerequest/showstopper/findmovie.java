package com.featurerequest.showstopper;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class findmovie extends Activity {

    private int titleCount = 1;
    private content frag_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findmovie);
        getWindow().getDecorView().findViewById(android.R.id.content).setOnTouchListener( new swipeHandle() );
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
        frag_content = new content();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        frag_content.setTitle("SampleTitle" + titleCount);
        frag_content.setGenre("SampleGenre");
        frag_content.setYear("SampleYear");
        frag_content.setLength("SampleLength");
        frag_content.setSynopsis("SampleSynopsis");
        frag_content.setURL("SampleURL");
        frag_content.setType("SampleType");
        ft.replace(R.id.frag_content, frag_content);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        titleCount++;
    }

    public void selectContent(View view) {
        Intent intent = new Intent(this, showContent.class);
        intent.putExtra("Title", frag_content.getTitle());
        intent.putExtra("Genre", frag_content.getGenre());
        intent.putExtra("Year", frag_content.getYear());
        intent.putExtra("Length", frag_content.getLength());
        intent.putExtra("Synopsis", frag_content.getSynopsis());
        intent.putExtra("URL", frag_content.getURL());
        intent.putExtra("Type", frag_content.getType());
        startActivity(intent);
    }

    private class swipeHandle extends SwipeListener implements View.OnTouchListener{

        @Override
        public boolean onSwipeLeft(){
            frag_content = new content();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            frag_content.setTitle("SampleTitle" + titleCount);
            frag_content.setGenre("SampleGenre");
            frag_content.setYear("SampleYear");
            frag_content.setLength("SampleLength");
            frag_content.setSynopsis("SampleSynopsis");
            frag_content.setURL("SampleURL");
            frag_content.setType("SampleType");
            ft.replace(R.id.frag_content, frag_content);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            titleCount++;
            return true;
        }
        @Override
        public boolean onSwipeRight(){
            return false;
        }
        @Override
        public boolean onSwipeUp(){
            return false;
        }
        @Override
        public boolean onSwipeDown(){
            return false;
        }
    }
}
