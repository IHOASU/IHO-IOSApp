package com.iho.asu.Pages;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class FieldNotes extends Fragment{

    public int resource;
    public FieldNotes(){}
    public FieldNotes(int resource){
        this.resource = resource;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScrollView sv = new ScrollView(getActivity().getApplicationContext());
        sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        sv.addView(inflater.inflate(
                resource, container, false));
        return sv;
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}