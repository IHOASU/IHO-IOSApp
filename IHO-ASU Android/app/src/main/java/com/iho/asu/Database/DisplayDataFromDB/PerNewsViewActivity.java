package com.iho.asu.Database.DisplayDataFromDB;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iho.asu.Database.Columns;
import com.iho.asu.Database.Tables.Lecturer;
import com.iho.asu.R;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;


public class PerNewsViewActivity extends Activity implements View.OnClickListener {
    private String link ="";
    private Map<String, Lecturer> lecturers = new HashMap<String, Lecturer>();

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.per_newsitem);
        TextView textView = (TextView) findViewById(R.id.title);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        TextView textView1 = (TextView)findViewById(R.id.text);
        Intent i = getIntent();
        link = i.getStringExtra(Columns.KEY_NEWS_LINK.getColumnName());
        ByteArrayInputStream img = new ByteArrayInputStream(i.getByteArrayExtra(Columns.KEY_NEWS_IMAGE.getColumnName()));
        Bitmap displayImg = BitmapFactory.decodeStream(img);
        imageView.setImageBitmap(displayImg);
        textView.setText(i.getStringExtra(Columns.KEY_NEWS_TITLE.getColumnName()));
        textView1.setText(i.getStringExtra(Columns.KEY_NEWS_TEXT.getColumnName()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newsLink:
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.customNewsBackbutton:
                NewsFragment newsFragment = new NewsFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.per_news, newsFragment);
                ft.commit();
                break;
        }
    }

    @Override
    public void onStart(){
        super.onStart();
    }

}