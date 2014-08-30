package com.iho.asu.Database.DisplayDataFromDB;

import android.app.Activity;
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
        PerLecturerViewFragment perLecturerViewFragment = new PerLecturerViewFragment();
        Intent i = getIntent();
        link = i.getStringExtra(Columns.KEY_LECTURER_LINK.getColumnName());
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.per_view, perLecturerViewFragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lectureLink:
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.customLecturerBackbutton:
                LecturerFragment lecturerFragment = new LecturerFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.per_view,lecturerFragment);
                ft.commit();
                break;
        }
    }

    @Override
    public void onStart(){
        super.onStart();
    }

}