package com.featurerequest.showstopper;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.Random;

public class findmovie extends Activity
{
    private int titleCount = 1;
    private Random rand = new Random();
    private MovieInfo frag_movie_info;
    //    private SwipeListener swipeListener;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findmovie);
        MySwipeListener sl = new MySwipeListener( this.getBaseContext() );
        mDetector = new GestureDetector(this,sl);
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

    private class MySwipeListener extends SwipeListener
    {
        public MySwipeListener( Context c )
        {
            super(c);
        }

        @Override
        public boolean onSwipeUp()
        {
            Log.i( "onSwipeUp", "got here" );
            return true;
        }

        @Override
        public boolean onSwipeDown()
        {
            Log.i( "onSwipeDown", "got here" );
            return true;
        }

        @Override
        public boolean onSwipeLeft()
        {
            Log.i( "onSwipeLeft", "got here" );
            generateNewMovie();
            return true;
        }

        @Override
        public boolean onSwipeRight()
        {
            Log.i( "onSwipeRight", "got here" );
            selectNewMovie();
            return true;
        }
    }

    @Override
    public boolean onTouchEvent( MotionEvent e )
    {
        this.mDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }


    private void generateNewMovie(){
        frag_movie_info = new MovieInfo();
        ShowInfo asyncTask = new ShowInfo(new AsyncResponse() {

            @Override
            public void processFinish(Object output) {
                JSONObject showJSON=(JSONObject) output;

                frag_movie_info.setTitle((String) showJSON.get("Title"));
                frag_movie_info.setGenre((String) showJSON.get("Genre"));
                frag_movie_info.setYear((String) showJSON.get("Year"));
                frag_movie_info.setLength((String) showJSON.get("Runtime"));
                frag_movie_info.setSynopsis((String) showJSON.get("Plot"));
                frag_movie_info.setURL((String) showJSON.get("Poster"));
                frag_movie_info.setType((String) showJSON.get("Type"));

//                LoadImage asyncTask2 = new LoadImage(new AsyncResponse2() {
//
//                    @Override
//                    public void processImage(Object output) {
//                        frag_content.setImage((Drawable) output);
//                    }
//                });
//
//                asyncTask2.execute(frag_content.getURL());
            }
        });


        int randomNum = rand.nextInt((4000000 - 1000000) + 1) + 1000000;
        String param = "tt"+randomNum;
        asyncTask.execute(param);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frag_content, frag_movie_info);
        ft.addToBackStack(null);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        this.overridePendingTransition( R.anim.enter_right, R.anim.exit_left );
        ft.commit();
    }

    private void selectNewMovie(){
        if(frag_movie_info !=null) {
            Intent intent = new Intent(this, showContent.class);
            intent.putExtra("Title: ", frag_movie_info.getTitle());
            intent.putExtra("Genre: ", frag_movie_info.getGenre());
            intent.putExtra("Year: ", frag_movie_info.getYear());
            intent.putExtra("Length: ", frag_movie_info.getLength());
            intent.putExtra("Synopsis: ", frag_movie_info.getSynopsis());
            intent.putExtra("URL: ", frag_movie_info.getURL());
            intent.putExtra("Type: ", frag_movie_info.getType());
            startActivity(intent);
        }
    }

    public interface AsyncResponse {

        void processFinish(Object output);
    }

    public class ShowInfo extends AsyncTask<String, Integer, Object> {
        public String title = "";
        public String year = "";
        public String length = "";
        public String genre = "";
        public String synopsis = "";
        public String type = "";
        public String path = "";

        public AsyncResponse delegate = null;//Call back interface

        public ShowInfo(AsyncResponse asyncResponse) {
            delegate = asyncResponse;//Assigning call back interfacethrough constructor
        }


        @Override
        protected Object doInBackground(String... params) {
            System.out.println(params[0]);
//            String json = "{\"Title\":\"Batman\",\"Year\":\"1989\",\"Rated\":\"PG-13\",\"Released\":\"23 Jun 1989\",\"Runtime\":\"126 min\",\"Genre\":\"Action, Adventure\",\"Director\":\"Tim Burton\",\"Writer\":\"Bob Kane (Batman characters), Sam Hamm (story), Sam Hamm (screenplay), Warren Skaaren (screenplay)\",\"Actors\":\"Michael Keaton, Jack Nicholson, Kim Basinger, Robert Wuhl\",\"Plot\":\"Gotham City: dark, dangerous, 'protected' only by a mostly corrupt police department. Despite the best efforts of D.A. Harvey Dent and police commissioner Jim Gordon, the city becomes increasingly unsafe...until a Dark Knight arises. We all know criminals are a superstitious, cowardly lot...so his disguise must be able to strike terror into their hearts. He becomes a bat. Enter Vicky Vale, a prize-winning photo journalist who wants to uncover the secret of the mysterious \\\"bat-man\\\". And enter Jack Napier, one-time enforcer for Boss Grissom, horribly disfigured after a firefight in a chemical factory...who, devoid of the last vestiges of sanity, seizes control of Gotham's underworld as the psychotic, unpredictable Clown Prince of Crime...the Joker. Gotham's only hope, it seems, lies in this dark, brooding vigilante. And just how does billionaire playboy Bruce Wayne fit into all of this?\",\"Language\":\"English, French\",\"Country\":\"USA, UK\",\"Awards\":\"Won 1 Oscar. Another 9 wins & 21 nominations.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_SX300.jpg\",\"Metascore\":\"66\",\"imdbRating\":\"7.6\",\"imdbVotes\":\"246,305\",\"imdbID\":\"tt0096895\",\"Type\":\"movie\",\"Response\":\"True\"}";
//            try {
            int randomNum = rand.nextInt((4000000 - 1000000) + 1) + 1000000;
            String param = "tt"+randomNum;
            Object obj = null;
            String title = null;
            String type = null;
            while (title == null && type != "movie") {
                HttpRequest request = HttpRequest.get("http://www.omdbapi.com/", true, "i", param, "plot", "full", "r", "json");
                String json = request.body();
                obj = JSONValue.parse(json);
                JSONObject showJSON = (JSONObject) obj;
                title = (String) showJSON.get("Title");
                type = (String) showJSON.get("Type");
                randomNum = rand.nextInt((4000000 - 1000000) + 1) + 1000000;
                param = "tt"+randomNum;
                System.out.println(title);
                System.out.println(type);
            }

            return obj;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {}

        @Override
        protected void onPostExecute(Object result) {
            delegate.processFinish(result);
        }

    }
}
