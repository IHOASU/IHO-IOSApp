package com.iho.asu.Database.DisplayDataFromDB;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.iho.asu.Database.Columns;
import com.iho.asu.R;


public class ViewActivity extends Activity implements View.OnClickListener {
    private String link ="";

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_view);
        Intent i = getIntent();
        String typeOfView = i.getStringExtra("ViewNeeded");
        Fragment returnFragment = getTheTypeOfFragment(typeOfView,i);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.per_view, returnFragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.lectureLink:
                startActivity(intent);
                break;
            case R.id.customLecturerBackbutton:
                LecturerFragment lecturerFragment = new LecturerFragment();
                ft.replace(R.id.per_view,lecturerFragment);
                ft.commit();
                break;
            case R.id.newsLink:
                startActivity(intent);
                break;
            case R.id.customNewsBackbutton:
                NewsFragment newsFragment = new NewsFragment();
                ft.replace(R.id.per_view, newsFragment);
                ft.commit();
                break;
        }
    }

    private Fragment getTheTypeOfFragment(String type, Intent i){
        Fragment returnFragment = new Fragment();
        if(type.equalsIgnoreCase("Lecturer")){
            returnFragment = new PerLecturerViewFragment();
            link = i.getStringExtra(Columns.KEY_LECTURER_LINK.getColumnName());
        } else if(type.equalsIgnoreCase("News")){
            returnFragment = new PerNewsViewFragment();
            link = i.getStringExtra(Columns.KEY_NEWS_LINK.getColumnName());
        }

        return returnFragment;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

}