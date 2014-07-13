package com.iho.asu.Database.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iho.asu.Database.Columns;
import com.iho.asu.Database.DataBaseHandler;
import com.iho.asu.Database.Tables.Events;
import com.iho.asu.Database.Tables.News;

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

    public News createEvents(byte[] news_img, String link, String title, String text) {
        ContentValues values = new ContentValues();
        values.put(Columns.KEY_NEWS_IMAGE.getColumnName(), news_img);
        values.put(Columns.KEY_NEWS_LINK.getColumnName(), link);
        values.put(Columns.KEY_NEWS_TITLE.getColumnName(), title);
        values.put(Columns.KEY_NEWS_TEXT.getColumnName(), text);
        long insertId = database.insert(DataBaseHandler.TABLE_NEWS, null,
                values);
        Cursor news = database.query(DataBaseHandler.TABLE_NEWS,
                allColumns, Columns.KEY_NEWS_ID.getColumnName() + " = " + insertId, null,
                null, null, null);
        news.moveToFirst();
        News newNews = cursorToNews(news);
        news.close();
        return newNews;
    }

    public void deleteEvent(Events news) {
        long id = news.getId();
        System.out.println("Event deleted with id: " + id);
        database.delete(DataBaseHandler.TABLE_NEWS, Columns.KEY_NEWS_ID
                + " = " + id, null);
    }

    public List<News> getAllNews() {
        List<News> news = new ArrayList<News>();

        Cursor cursor = database.query(DataBaseHandler.TABLE_NEWS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            News n = cursorToNews(cursor);
            news.add(n);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return news;
    }

    private News cursorToNews(Cursor cursor) {
        News n = new News();
        n.setId(cursor.getLong(0));
        n.setTitle(cursor.getString(1));
        n.setWhen(cursor.getString(2));
        ev.setWhere(cursor.getString(3));
        ev.setLocation_link(cursor.getString(4));
        ev.setDescription(cursor.getString(5));
        ev.setReg(cursor.getString(6));
        return ev;
    }
}
