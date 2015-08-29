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


    ImageView content_image;
    public content() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_content, container, false);
        ViewGroup rootView  = (ViewGroup) inflater.inflate(R.layout.fragment_content, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        TextView content_desc = (TextView) view.findViewById(R.id.content_desc);
        content_desc.setMovementMethod(new ScrollingMovementMethod());
        content_image = (ImageView) view.findViewById(R.id.content_image);
        content_image.setImageResource(R.drawable.placeholder);
    }

}
