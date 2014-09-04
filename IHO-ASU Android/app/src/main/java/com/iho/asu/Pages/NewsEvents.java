package com.iho.asu.Pages;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iho.asu.R;


public class NewsEvents extends Fragment{

    public int resource;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        ScrollView sv = new ScrollView(getActivity().getApplicationContext());
//        sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//        sv.addView(inflater.inflate(
//                R.layout.fragment_news_events, container, false));
//        return sv;
        return inflater.inflate(
                R.layout.fragment_news_events, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}