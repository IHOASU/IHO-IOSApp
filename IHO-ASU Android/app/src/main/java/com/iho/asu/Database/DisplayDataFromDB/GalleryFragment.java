package com.iho.asu.Database.DisplayDataFromDB;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import com.iho.asu.Database.Tables.Gallery;
import com.iho.asu.R;

import java.util.ArrayList;
import java.util.List;

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
    protected List<Gallery> galleryItems = new ArrayList<Gallery>();
    protected Gallery[] galleryArray;
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
        galleryArray = new Gallery[galleryItems.size()];
        galleryItems.toArray(galleryArray);
        onSwitch(null);
        return v;
    }

    public void onSwitch(View view) {
        Gallery currentItem = galleryArray[mPosition];
        Drawable image = new BitmapDrawable(BitmapFactory.decodeByteArray(currentItem.getName(), 0, currentItem.getName().length));
        mImageSwitcher.setImageDrawable(image);
        mPosition = (mPosition + 1) % galleryItems.size();
    }

    private void getGalleryItems() {
        String[] columns = Columns.getGalleryColumnNames();
        Cursor galleryCursor = database.query(TABLE_NAME, columns, null, null, null, null, null);
        galleryCursor.moveToFirst();
        while (!galleryCursor.isAfterLast()) {
            cursorToGallery(galleryCursor);
            galleryCursor.moveToNext();
        }
        galleryCursor.close();
    }

    private void cursorToGallery(Cursor cursor) {
        com.iho.asu.Database.Tables.Gallery n = new com.iho.asu.Database.Tables.Gallery();
        if(!cursor.isNull( 0 ))n.setId(cursor.getLong(0));
        if(!cursor.isNull( 1 ))n.setName(cursor.getBlob(1));
        galleryItems.add(n);
        Log.d("cursorToGallery","Received");
    }

}