package com.iho.asu.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iho.asu.Database.Columns;
import com.iho.asu.Database.DataBaseHandler;
import com.iho.asu.Database.Tables.Lecturer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barathi on 7/5/2014.
 */
public class LecturerDAO {
    private SQLiteDatabase database;
    private DataBaseHandler dbHandler;
    private String[] allColumns = Columns.getLecturerColumnNames();
    public LecturerDAO(Context context) {
        dbHandler = new DataBaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        dbHandler.close();
    }

    public Lecturer createLecturer(String name, String title, byte[] image, String bio, String link) {
        ContentValues values = new ContentValues();
        values.put(Columns.KEY_LECTURER_NAME.getColumnName(), name);
        values.put(Columns.KEY_LECTURE_TITLE.getColumnName(), title);
        values.put(Columns.KEY_LECTURER_IMAGE.getColumnName(), image);
        values.put(Columns.KEY_LECTURER_LINK.getColumnName(), link);
        values.put(Columns.KEY_LECTURER_BIO.getColumnName(), bio);
        long insertId = database.insert(DataBaseHandler.TABLE_LECTURER, null,
                values);
        Cursor lecturer = database.query(DataBaseHandler.TABLE_LECTURER,
                allColumns, Columns.KEY_LECTURER_ID.getColumnName() + " = " + insertId, null,
                null, null, null);
        lecturer.moveToFirst();
        Lecturer newLecturer = cursorToLecturer(lecturer);
        lecturer.close();
        return newLecturer;
    }

    public void deleteLecturer(Lecturer l) {
        long id = l.getId();
        System.out.println("Lecturer deleted with id: " + id);
        database.delete(DataBaseHandler.TABLE_LECTURER, Columns.KEY_LECTURER_ID
                + " = " + id, null);
    }

    public List<Lecturer> getAllLecturer() {
        List<Lecturer> lecturers = new ArrayList<Lecturer>();

        Cursor cursor = database.query(DataBaseHandler.TABLE_LECTURER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Lecturer l = cursorToLecturer(cursor);
            lecturers.add(l);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return lecturers;
    }

    private Lecturer cursorToLecturer(Cursor cursor) {
        Lecturer l = new Lecturer();
        l.setId(cursor.getLong(0));
        l.setName(cursor.getString(1));
        l.setImage(cursor.getBlob(2));
        l.setBio(cursor.getString(3));
        l.setEmail(cursor.getString(4));
        l.setLink(cursor.getString(5));
        l.setTitle(cursor.getString(6));
        return l;
    }
}
