package com.iho.asu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener{

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
        switch (v.getId()) {
            case R.id.about:
                About about = new About();
                fragmentTransaction.replace(R.id.main_layout, about);
                fragmentTransaction.commit();
                break;
            case R.id.donate:
                Donate donate = new Donate();
                fragmentTransaction.replace(R.id.main_layout, donate);
                fragmentTransaction.commit();
                break;
            case R.id.investButton:
                Log.d("Invest","Clicked Invest Button");
                Uri uri = Uri.parse("https://securelb.imodules.com/s/1469/foundation/Inner2Columns3.aspx?sid=1469&gid=2&pgid=426&cid=1155&bledit=1&dids=216");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }
}
