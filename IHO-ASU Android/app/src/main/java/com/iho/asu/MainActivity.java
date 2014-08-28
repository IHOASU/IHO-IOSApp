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

import com.iho.asu.Database.DisplayDataFromDB.LecturerFragment;
import com.iho.asu.Pages.About;
import com.iho.asu.Pages.Connect;
import com.iho.asu.Pages.Credits;
import com.iho.asu.Pages.Donate;
import com.iho.asu.Pages.FieldNotes;
import com.iho.asu.Pages.Gallery;
import com.iho.asu.Pages.Images;
import com.iho.asu.Pages.Lucy;
import com.iho.asu.Pages.MainFragment;
import com.iho.asu.Pages.NewsEvents;

import static android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener{
    private static final String[] TEXTS = { "Image #1", "Image #2", "Image #3" };
    private static final int[] IMAGES = { R.drawable.img1, R.drawable.img2,
            R.drawable.img3 };
    private int mPosition = 0;

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
        FieldNotes fieldNotes;
        NewsEvents ne;
        Connect connect;
        switch (v.getId()) {
            case R.id.about:
                About about = new About();
                fragmentTransaction.replace(R.id.main_layout, about);
                fragmentTransaction.commit();
                break;
            case R.id.customConnectBackButton:
            case R.id.connect:
                connect = new Connect(R.layout.fragment_connect);
                fragmentTransaction.replace(R.id.main_layout, connect);
                fragmentTransaction.commit();
                break;
            case R.id.customNEBackButton:
            case R.id.ne:
                ne = new NewsEvents(R.layout.fragment_news_events);
                fragmentTransaction.replace(R.id.main_layout, ne);
                fragmentTransaction.commit();
                break;
            case R.id.tr1:
                uri = Uri.parse("https://iho.asu.edu/outreach/travel/galapagos2014");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.tr2:
                uri = Uri.parse("https://iho.asu.edu/outreach/travel/france2013");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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
            case R.id.connect1:
                connect = new Connect(R.layout.fragment_iho);
                fragmentTransaction.replace(R.id.main_layout, connect);
                fragmentTransaction.commit();
                break;
            case R.id.connect2:
                connect = new Connect(R.layout.fragment_bh);
                fragmentTransaction.replace(R.id.main_layout, connect);
                fragmentTransaction.commit();
                break;
            case R.id.connect3:
                connect = new Connect(R.layout.fragment_sign_news);
                fragmentTransaction.replace(R.id.main_layout, connect);
                fragmentTransaction.commit();
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
            case R.id.galleryBtn:
                Intent imgIntent = new Intent(getApplicationContext(), Images.class);
                startActivity(imgIntent);
                break;
            case R.id.mapItBtn:
                uri = Uri.parse("https://www.google.com/maps/place/951+Cady+Mall/@33.419944,-111.9345045,17z/data=!3m1!4b1!4m2!3m1!1s0x872b08db89afde97:0x2a9f7cbb1d7f4e64");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.customFNBackbutton:
            case R.id.field:
                fieldNotes = new FieldNotes(R.layout.fragment_field);
                fragmentTransaction.replace(R.id.main_layout, fieldNotes);
                fragmentTransaction.commit();
                break;
            case R.id.customLecturerBackbutton:
            case R.id.fn1:
                LecturerFragment lfragment = new LecturerFragment();
                fragmentTransaction.replace(R.id.main_layout, lfragment);
                fragmentTransaction.commit();
                break;
            case R.id.fn3:
                Lucy lucyFragment = new Lucy();
                fragmentTransaction.replace(R.id.field_layout, lucyFragment);
                fragmentTransaction.commit();
                break;
            case R.id.fn4:
                uri = Uri.parse("http://asuiho.wordpress.com/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;


        }
    }
}
