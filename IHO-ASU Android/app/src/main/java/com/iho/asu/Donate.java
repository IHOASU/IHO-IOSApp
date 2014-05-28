package com.iho.asu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class Donate extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScrollView sv = new ScrollView(getActivity().getApplicationContext());
        sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        sv.addView(inflater.inflate(
                R.layout.fragment_donate, container, false));
        return sv;
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}