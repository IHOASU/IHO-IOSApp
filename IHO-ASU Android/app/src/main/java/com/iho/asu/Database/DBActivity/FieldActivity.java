package com.iho.asu.Database.DBActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.iho.asu.Database.DAO.LecturerDAO;
import com.iho.asu.Database.Tables.Lecturer;
import com.iho.asu.R;

import java.sql.SQLException;
import java.util.List;

public class FieldActivity extends ListActivity {
    private LecturerDAO lecturerDAO;
    private static final String TAG = "ListActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        lecturerDAO = new LecturerDAO(this);
        try {
            lecturerDAO.open();
        } catch (SQLException e) {
            Log.e(TAG,"Issue opening lecturer table: "+e.getLocalizedMessage());
        }

        List<Lecturer> values = lecturerDAO.getAllLecturer();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Lecturer> adapter = new ArrayAdapter<Lecturer>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }
}