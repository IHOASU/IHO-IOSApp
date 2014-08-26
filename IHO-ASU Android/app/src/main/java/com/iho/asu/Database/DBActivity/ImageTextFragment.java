package com.iho.asu.Database.DBActivity;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iho.asu.Database.Tables.Lecturer;
import com.iho.asu.R;

import java.io.ByteArrayInputStream;
import java.util.Map;


public class ImageTextFragment extends Fragment{
    private final String value;
    private final Map<String, Lecturer> lecturers;
    public ImageTextFragment(String value, Map<String,Lecturer> lecturers) {
        this.value = value;
        this.lecturers = lecturers;
    }
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.simple_db_text_img, container, false);
        Lecturer currentLecturer = lecturers.get(value);
        TextView textView = (TextView) v.findViewById(R.id.label);
        ImageView imageView = (ImageView) v.findViewById(R.id.logo);
        ByteArrayInputStream img = new ByteArrayInputStream(currentLecturer.getImage());
        Bitmap displayImg = BitmapFactory.decodeStream(img);
        imageView.setImageBitmap(displayImg);
        textView.setText(value);
        Log.d("ImageTextFragment","In here:"+value);
        return v;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

}