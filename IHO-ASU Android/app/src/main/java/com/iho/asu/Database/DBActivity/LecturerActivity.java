package com.iho.asu.Database.DBActivity;

import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iho.asu.Database.Columns;
import com.iho.asu.Database.DataBaseHelper;
import com.iho.asu.Database.Tables.Lecturer;
import com.iho.asu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LecturerActivity extends ListActivity {

    private static final String DB_NAME = "asuIHO.db";

    //A good practice is to define database field names as constants
    private static final String TABLE_NAME = "Lecturer";

    private SQLiteDatabase database;
    private ListView listView;
    private ArrayList<String> lecturerNames = new ArrayList<String>();
    protected Map<String,Lecturer> lecturers = new HashMap<String, Lecturer>();
    private  ImageTextFragment imgFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);
        DataBaseHelper dbOpenHelper = new DataBaseHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        getLecturers();
        setUpList();
    }

    private void setUpList() {
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lecturerNames));
        listView = getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view,
                                    int position, long id) {
                imgFragment = new ImageTextFragment(lecturerNames.get(position),lecturers);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.lecturer_layout, imgFragment);
                ft.commit();
            }
        });
    }

    //Extracting elements from the database
    private void getLecturers() {
        String[] columns = Columns.getLecturerColumnNames();
        Cursor lecCursor = database.query(TABLE_NAME, columns, null, null, null, null, Columns.KEY_LECTURER_ID.getColumnName());
        lecCursor.moveToFirst();
        lecCursor.moveToFirst();
        while (!lecCursor.isAfterLast()) {
            cursorToLecturer(lecCursor);
            lecCursor.moveToNext();
        }
        lecCursor.close();
    }

    private void cursorToLecturer(Cursor cursor) {
        Lecturer l = new Lecturer();
        String name = cursor.getString(1);
        l.setId(cursor.getLong(0));
        l.setName(name);
        l.setImage(cursor.getBlob(2));
        l.setBio(cursor.getString(3));
        l.setEmail(cursor.getString(4));
        l.setLink(cursor.getString(5));
        l.setTitle(cursor.getString(6));
        lecturerNames.add(name);
        lecturers.put(name,l);
    }
}
