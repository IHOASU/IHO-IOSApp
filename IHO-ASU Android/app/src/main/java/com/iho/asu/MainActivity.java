package com.iho.asu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import static android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener{
    static boolean isVideoLayoutVisible = false;
    static boolean isIHOLayoutVisible = false;
    static boolean isBecomingHumanLayoutVisible = false;
    static boolean isContactLayoutVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment fv = new MainFragment(this);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_layout, fv);
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Uri uri;
        Intent intent;
        LinearLayout ll;
        switch (v.getId()) {
            case R.id.about:
                About about = new About();
                fragmentTransaction.replace(R.id.main_layout, about);
                fragmentTransaction.commit();
                break;
            case R.id.connect:
                Connect connect = new Connect();
                fragmentTransaction.replace(R.id.main_layout, connect);
                fragmentTransaction.commit();
                break;
            case R.id.donate:
                Donate donate = new Donate();
                fragmentTransaction.replace(R.id.main_layout, donate);
                fragmentTransaction.commit();
                break;
            case R.id.gallery:
                Gallery gallery = new Gallery();
                fragmentTransaction.replace(R.id.main_layout, gallery);
                fragmentTransaction.commit();
                break;
            case R.id.investButton:
                uri = Uri.parse("https://securelb.imodules.com/s/1469/foundation/Inner2Columns3.aspx?sid=1469&gid=2&pgid=426&cid=1155&bledit=1&dids=216");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.linkToLocation:
                uri = Uri.parse("https://www.google.com/maps/preview?q=951+South+Cady+Mall,+Tempe,+AZ&hl=en&ll=33.420231,-111.930749&spn=0.011158,0.014999&sll=33.41972,-111.934757&sspn=0.002933,0.002591&oq=951+South+Cady+Mall&hnear=951+S+Cady+Mall,+Tempe,+Maricopa,+Arizona+85281&t=m&z=16");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.linkToContact:
                uri = Uri.parse("https://iho.asu.edu/contact/contact-us");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.fbButton:
                uri = Uri.parse("https://www.facebook.com/pages/Lucy-and-ASU-Institute-of-Human-Origins/146317035387367");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.twButton:
                uri = Uri.parse("https://twitter.com/LucyASUIHO");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.creditsButton:
                Credits credits = new Credits();
                fragmentTransaction.replace(R.id.main_layout, credits);
                fragmentTransaction.commit();
                break;
            case R.id.customBackButton:
                MainFragment mainFragment = new MainFragment();
                fragmentTransaction.replace(R.id.main_layout, mainFragment);
                fragmentTransaction.commit();
                break;
            case R.id.youtubeBtn:
                uri = Uri.parse("https://www.youtube.com/user/LucyASUIHO");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.vimeoBtn:
                uri = Uri.parse("http://vimeo.com/user5956652");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.video:
                ll = (LinearLayout)findViewById(R.id.videoLayout);
                if(!isVideoLayoutVisible){
                    ll.setVisibility(View.VISIBLE);
                    isVideoLayoutVisible = true;
                } else{
                    ll.setVisibility(View.INVISIBLE);
                    isVideoLayoutVisible=false;
                }
                break;
            case R.id.connect1:
                ll = (LinearLayout)findViewById(R.id.iholayout);
                if(!isIHOLayoutVisible){
                    ll.setVisibility(View.VISIBLE);
                    isIHOLayoutVisible = true;
                } else{
                    ll.setVisibility(View.INVISIBLE);
                    isIHOLayoutVisible=false;
                }
                break;
            case R.id.connect2:
                ll = (LinearLayout)findViewById(R.id.becomingHumanLayout);
                if(!isBecomingHumanLayoutVisible){
                    ll.setVisibility(View.VISIBLE);
                    isBecomingHumanLayoutVisible = true;
                } else{
                    ll.setVisibility(View.INVISIBLE);
                    isBecomingHumanLayoutVisible=false;
                }
                break;
            case R.id.contact:
                ll = (LinearLayout)findViewById(R.id.contactLayout);
                if(!isContactLayoutVisible){
                    ll.setVisibility(View.VISIBLE);
                    isContactLayoutVisible = true;
                } else{
                    ll.setVisibility(View.INVISIBLE);
                    isContactLayoutVisible=false;
                }
                break;
            case R.id.watchNow:
                uri = Uri.parse("http://video.nationalgeographic.com/video/news/112811-prehistoric-caves-ngtoday");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.website:
                uri = Uri.parse("https://iho.asu.edu/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.visitBecomingHumanBtn:
                uri = Uri.parse("http://www.becominghuman.org/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;


        }
    }
}
