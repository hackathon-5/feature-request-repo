package com.featurerequest.showstopper;


import android.os.Bundle;
import android.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class content extends Fragment {


    private ImageView content_image;
    private String content_title = "";
    private String content_year = "";
    private String content_length = "";
    private String content_genre = "";
    private String content_synopsis = "";
    private String content_type = "";
    private String content_url = "";
    public content() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_content, container, false);
        ViewGroup rootView  = (ViewGroup) inflater.inflate(R.layout.fragment_content, container, false);
        //Bundle bundle  = this.getArguments();
        //content_title = bundle.getString("Title");
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        TextView content_desc = (TextView) view.findViewById(R.id.content_desc);
        content_desc.setText("Type: " + content_type + "\n"
                + "Genre: " +content_genre + "\n"
                + "Length: " +content_length + "\n"
                + "Year: " +content_year + "\n"
                + "Synopsis:\n" +content_synopsis);
        TextView textView_Title = (TextView) view.findViewById(R.id.content_title);
        textView_Title.setText(content_title);
        content_desc.setMovementMethod(new ScrollingMovementMethod());
        content_image = (ImageView) view.findViewById(R.id.content_image);
        content_image.setImageResource(R.drawable.placeholder);
    }

    public void setTitle(String title){
        this.content_title = title;
    }

    public void setYear(String year){
        this.content_year = year;
    }

    public void setLength(String length){
        this.content_length = length;
    }

    public void setGenre(String genre){
        this.content_genre = genre;
    }

    public void setSynopsis(String synopsis){
        this.content_synopsis = synopsis;
    }

    public void setType(String type){
        this.content_type = type;
    }

    public void setURL(String url){
        this.content_url = url;
    }

    public String getTitle(){
        return content_title;
    }

    public String getYear(){
        return content_year;
    }

    public String getLength(){
        return content_length;
    }

    public String getGenre(){
        return content_genre;
    }

    public String getSynopsis(){
        return content_synopsis;
    }

    public String getType(){
        return content_type;
    }

    public String getURL(){
        return content_url;
    }

}
