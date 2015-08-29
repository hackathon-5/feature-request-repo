package com.featurerequest.showstopper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class showContent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            TextView textView_title = (TextView) findViewById(R.id.showContent_title);
            textView_title.setText(bundle.getString("Title"));
            TextView textView_desc = (TextView) findViewById(R.id.showContent_desc);
            textView_desc.setText("Type: " + bundle.getString("Type") + "\n"
                    + "Genre: " + bundle.getString("Genre") + "\n"
                    + "Length: " + bundle.getString("Length") + "\n"
                    + "Year: " + bundle.getString("Year") + "\n"
                    + "Synopsis:\n" + bundle.getString("Synopsis"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_content, menu);
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
}
