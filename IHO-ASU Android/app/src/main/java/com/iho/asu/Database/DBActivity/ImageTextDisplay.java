package com.iho.asu.Database.DBActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iho.asu.Database.Tables.Lecturer;
import com.iho.asu.R;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

public class ImageTextDisplay extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> values;
    private final Map<String, Lecturer> lecturers;

    public ImageTextDisplay(Context context, List<String> values, Map<String,Lecturer> lecturers) {
        super(context, R.layout.simple_db_text_img, values);
        this.context = context;
        this.values = values;
        this.lecturers = lecturers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.simple_db_text_img, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        String s = values.get(position);
        Log.d("ImgTextDisplay",s );
        textView.setText(s);
        Lecturer currentLecturer = lecturers.get(s);
        ByteArrayInputStream img = new ByteArrayInputStream(currentLecturer.getImage());
        Bitmap displayImg = BitmapFactory.decodeStream(img);
        imageView.setImageBitmap(displayImg);
        return rowView;
    }
}