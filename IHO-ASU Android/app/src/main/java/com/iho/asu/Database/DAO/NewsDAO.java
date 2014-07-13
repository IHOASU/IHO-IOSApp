package com.iho.asu.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iho.asu.Database.Columns;
import com.iho.asu.Database.DataBaseHandler;
import com.iho.asu.Database.Tables.Events;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Barathi on 7/5/2014.
 */
public class NewsDAO {
    private SQLiteDatabase database;
    private DataBaseHandler dbHandler;
    private String[] allColumns = Columns.getEventColumnNames();
    public NewsDAO(Context context) {
        dbHandler = new DataBaseHandler(context);
    }

    public void open() throws SQLException {
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        dbHandler.close();
    }

    public Events createEvents(String when, String where, String title, String mapLink, String reg) {
        ContentValues values = new ContentValues();
        values.put(Columns.KEY_NEWS_WHEN.getColumnName(), when);
        values.put(Columns.KEY_NEWS_WHERE.getColumnName(), where);
        values.put(Columns.KEY_NEWS_TITLE.getColumnName(), title);
        values.put(Columns.KEY_NEWS_MAP.getColumnName(), mapLink);
        values.put(Columns.KEY_NEWS_REG.getColumnName(), reg);
        long insertId = database.insert(DataBaseHandler.TABLE_NEWS, null,
                values);
        Cursor news = database.query(DataBaseHandler.TABLE_NEWS,
                allColumns, Columns.KEY_NEWS_ID.getColumnName() + " = " + insertId, null,
                null, null, null);
        news.moveToFirst();
        Events newEvent = cursorToEvent(news);
        news.close();
        return newEvent;
    }

    public void deleteEvent(Events news) {
        long id = news.getId();
        System.out.println("Event deleted with id: " + id);
        database.delete(DataBaseHandler.TABLE_NEWS, Columns.KEY_NEWS_ID
                + " = " + id, null);
    }

    public List<Events> getAllEvents() {
        List<Events> newss = new ArrayList<Events>();

        Cursor cursor = database.query(DataBaseHandler.TABLE_NEWS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Events ev = cursorToEvent(cursor);
            newss.add(ev);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return newss;
    }

    private Events cursorToEvent(Cursor cursor) {
        Events ev = new Events();
        ev.setId(cursor.getLong(0));
        ev.setTitle(cursor.getString(1));
        ev.setWhen(cursor.getString(2));
        ev.setWhere(cursor.getString(3));
        ev.setLocation_link(cursor.getString(4));
        ev.setDescription(cursor.getString(5));
        ev.setReg(cursor.getString(6));
        return ev;
    }
}
