package com.iho.asu.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iho.asu.Database.Columns;
import com.iho.asu.Database.DataBaseHandler;
import com.iho.asu.Database.Tables.Gallery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barathi on 7/5/2014.
 */
public class GalleryDAO {
    private SQLiteDatabase database;
    private DataBaseHandler dbHandler;
    private String[] allColumns = Columns.getGalleryColumnNames();
    public GalleryDAO(Context context) {
        dbHandler = new DataBaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        dbHandler.close();
    }

    public Gallery createGallery(String name) {
        ContentValues values = new ContentValues();
        values.put(Columns.KEY_GALLERY_NAME.getColumnName(), name);
        long insertId = database.insert(DataBaseHandler.TABLE_GALLERY, null,
                values);
        Cursor event = database.query(DataBaseHandler.TABLE_GALLERY,
                allColumns, Columns.KEY_GALLERY_ID.getColumnName() + " = " + insertId, null,
                null, null, null);
        event.moveToFirst();
        Gallery newEvent = cursorToEvent(event);
        event.close();
        return newEvent;
    }

    public void deleteEvent(Gallery event) {
        long id = event.getId();
        System.out.println("Event deleted with id: " + id);
        database.delete(DataBaseHandler.TABLE_GALLERY, Columns.KEY_GALLERY_ID
                + " = " + id, null);
    }

    public List<Gallery> getAllGallery() {
        List<Gallery> events = new ArrayList<Gallery>();

        Cursor cursor = database.query(DataBaseHandler.TABLE_GALLERY,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Gallery ev = cursorToEvent(cursor);
            events.add(ev);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return events;
    }

    private Gallery cursorToEvent(Cursor cursor) {
        Gallery g = new Gallery();
        g.setId(cursor.getLong(0));
        g.setName(cursor.getString(1));
        return g;
    }
}
