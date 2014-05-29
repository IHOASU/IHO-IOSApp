package com.iho.asu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Barathi on 5/26/2014.
 */
public class MainFragment extends Fragment {

    public Activity mainActivity;
    public MainFragment(){}
    public MainFragment(Activity mainActivity){
        this.mainActivity = mainActivity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.fragment_main, container, false);
        WebView webView = (WebView)(v.findViewById(R.id.webView));
        webView.loadUrl("file:///android_asset/index.html");
        return v;
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}
