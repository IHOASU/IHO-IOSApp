package com.iho.asu.Database.DisplayDataFromDB;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.iho.asu.Database.Columns;
import com.iho.asu.Database.DataBaseHelper;
import com.iho.asu.R;

public class GalleryFragment extends Fragment {

    private static final String[] TEXTS = { "Hadar, Ethiopia Landscape", "Student Survey", "Mossel Bay Caves" };
    private static final int[] IMAGES = { R.drawable.img1, R.drawable.img2,
            R.drawable.img3 };
    private int mPosition = 0;
    private TextSwitcher mTextSwitcher;
    private ImageSwitcher mImageSwitcher;
    private static final String DB_NAME = "asuIHO.db";
    private static final String TABLE_NAME = "Gallery";
    private SQLiteDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.gallery, container, false);
        final Context context = this.getActivity().getApplicationContext();
        DataBaseHelper dbOpenHelper = new DataBaseHelper(this.getActivity(), DB_NAME);
        database = dbOpenHelper.openDataBase();
        mTextSwitcher = (TextSwitcher) v.findViewById(R.id.textSwitcher);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(context);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });

        mTextSwitcher.setInAnimation(context, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(context, android.R.anim.fade_out);

        mImageSwitcher = (ImageSwitcher) v.findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(context);
                return imageView;
            }
        });
        mImageSwitcher.setInAnimation(context, android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(context, android.R.anim.slide_out_right);
        getGalleryItems();
        onSwitch(null);
        return v;
    }

    public void onSwitch(View view) {
        mTextSwitcher.setText(TEXTS[mPosition]);
        mImageSwitcher.setBackgroundResource(IMAGES[mPosition]);
        mPosition = (mPosition + 1) % TEXTS.length;
    }

    private void getGalleryItems() {
        String[] columns = Columns.getGalleryColumnNames();
        String query = "Select * from Gallery";
        Cursor galleryCursor = database.rawQuery(query,null);

        if(galleryCursor.moveToFirst()) {
            while (!galleryCursor.isAfterLast()) {
                cursorToGallery(galleryCursor);
                galleryCursor.moveToNext();
            }
        }
        galleryCursor.close();
    }

    private void cursorToGallery(Cursor cursor) {
        com.iho.asu.Database.Tables.Gallery n = new com.iho.asu.Database.Tables.Gallery();
        if(cursor.getBlob(1)!=null){

        }

        Log.d("cursorToGallery","Received");
    }
}