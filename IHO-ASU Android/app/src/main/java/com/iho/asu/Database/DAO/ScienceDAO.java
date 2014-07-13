package com.iho.asu.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iho.asu.Database.Columns;
import com.iho.asu.Database.DataBaseHandler;
import com.iho.asu.Database.Tables.Science;
import com.iho.asu.Database.Tables.Science;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barathi on 7/5/2014.
 */
public class ScienceDAO {
    private SQLiteDatabase database;
    private DataBaseHandler dbHandler;
    private String[] allColumns = Columns.getScienceColumnNames();
    public ScienceDAO(Context context) {
        dbHandler = new DataBaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        dbHandler.close();
    }

    public Science createScience(String link, String title) {
        ContentValues values = new ContentValues();
        values.put(Columns.KEY_SCIENCE_LINK.getColumnName(), link);
        values.put(Columns.KEY_SCIENCE_TITLE.getColumnName(), title);
        long insertId = database.insert(DataBaseHandler.TABLE_SCIENCE, null,
                values);
        Cursor science = database.query(DataBaseHandler.TABLE_SCIENCE,
                allColumns, Columns.KEY_SCIENCE_ID.getColumnName() + " = " + insertId, null,
                null, null, null);
        science.moveToFirst();
        Science newScience = cursorToScience(science);
        science.close();
        return newScience;
    }

    public void deleteScience(Science science) {
        long id = science.getId();
        System.out.println("Science deleted with id: " + id);
        database.delete(DataBaseHandler.TABLE_SCIENCE, Columns.KEY_SCIENCE_ID
                + " = " + id, null);
    }

    public List<Science> getAllScienceItems() {
        List<Science> science = new ArrayList<Science>();

        Cursor cursor = database.query(DataBaseHandler.TABLE_SCIENCE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Science n = cursorToScience(cursor);
            science.add(n);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return science;
    }

    private Science cursorToScience(Cursor cursor) {
        Science sc = new Science();
        sc.setId(cursor.getLong(0));
        sc.setTitle(cursor.getString(1));
        sc.setLink(cursor.getString(2));
        return sc;
    }
}
