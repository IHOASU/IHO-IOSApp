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
public class EventsDAO {
    private SQLiteDatabase database;
    private DataBaseHandler dbHandler;
    private String[] allColumns = Columns.getEventColumnNames();
    public EventsDAO(Context context) {
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
        values.put(Columns.KEY_EVENT_WHEN.getColumnName(), when);
        values.put(Columns.KEY_EVENT_WHERE.getColumnName(), where);
        values.put(Columns.KEY_EVENT_TITLE.getColumnName(), title);
        values.put(Columns.KEY_EVENT_MAP.getColumnName(), mapLink);
        values.put(Columns.KEY_EVENT_REG.getColumnName(), reg);
        long insertId = database.insert(DataBaseHandler.TABLE_EVENTS, null,
                values);
        Cursor event = database.query(DataBaseHandler.TABLE_EVENTS,
                allColumns, Columns.KEY_EVENT_ID.getColumnName() + " = " + insertId, null,
                null, null, null);
        event.moveToFirst();
        Events newEvent = cursorToEvent(event);
        event.close();
        return newEvent;
    }

    public void deleteEvent(Events event) {
        long id = event.getId();
        System.out.println("Event deleted with id: " + id);
        database.delete(DataBaseHandler.TABLE_EVENTS, Columns.KEY_EVENT_ID
                + " = " + id, null);
    }

    public List<Events> getAllEvents() {
        List<Events> events = new ArrayList<Events>();

        Cursor cursor = database.query(DataBaseHandler.TABLE_EVENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Events ev = cursorToEvent(cursor);
            events.add(ev);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return events;
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
