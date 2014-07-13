package com.iho.asu.Database;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Barathi on 4/7/2014.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "ASUIHO";

    // Table names
    public static final String TABLE_EVENTS= "EVENTS";
    public static final String TABLE_GALLERY= "GALLERY";
    public static final String TABLE_LECTURER= "LECTURER";
    public static final String TABLE_NEWS= "NEWS";
    public static final String TABLE_SCIENCE= "SCIENCE";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_EVENTS = "CREATE TABLE "+TABLE_EVENTS+"(\""+Columns.KEY_EVENT_ID+"\"INTEGER PRIMARY KEY  AUTOINCREMENT , \""+Columns.KEY_EVENT_TITLE+"\" VARCHAR,\""+Columns.KEY_EVENT_WHEN+"\" VARCHAR, \""+Columns.KEY_EVENT_WHERE+"\" VARCHAR,\""+Columns.KEY_EVENT_MAP+"\" VARCHAR,\""+Columns.KEY_EVENT_DESC+"\" VARCHAR,\""+Columns.KEY_EVENT_REG+"\" VARCHAR);";
        db.execSQL(CREATE_TABLE_EVENTS);
        String CREATE_TABLE_GALLERY = "CREATE TABLE \""+TABLE_GALLERY+"\" (\""+Columns.KEY_GALLERY_ID+"\" INTEGER PRIMARY KEY  AUTOINCREMENT, \""+Columns.KEY_GALLERY_NAME+"\" BLOB);";
        db.execSQL(CREATE_TABLE_GALLERY);
        String CREATE_TABLE_LECTURER = "CREATE TABLE \""+TABLE_LECTURER+"\" (\""+Columns.KEY_LECTURER_ID+"\" INTEGER PRIMARY KEY  AUTOINCREMENT ,\""+Columns.KEY_LECTURER_NAME+"\" TEXT,\""+Columns.KEY_LECTURER_IMAGE+"\" BLOB,\""+Columns.KEY_LECTURE_TITLE+"\" TEXT,\""+Columns.KEY_LECTURER_BIO+"\" TEXT,\""+Columns.KEY_LECTURER_LINK+"\" TEXT,\""+Columns.KEY_LECTURER_EMAIL+"\" TEXT);";
        String CREATE_TABLE_NEWS="CREATE TABLE \""+TABLE_NEWS+"\" (\""+Columns.KEY_NEWS_ID+"\" INTEGER PRIMARY KEY  AUTOINCREMENT , \""+Columns.KEY_NEWS_TITLE+"\"  VARCHAR, \""+Columns.KEY_NEWS_IMAGE+"\" BLOB, \""+Columns.KEY_NEWS_TEXT+"\" TEXT check(typeof(\""+Columns.KEY_NEWS_TEXT+"\") = 'text') , \""+Columns.KEY_NEWS_LINK+"\" VARCHAR)";
        String CREATE_TABLE_SCIENCE = "CREATE TABLE \""+TABLE_SCIENCE+"\" (\""+Columns.KEY_SCIENCE_ID+"\" INTEGER PRIMARY KEY  UTOINCREMENT  , \""+Columns.KEY_SCIENCE_TITLE+"\" TEXT, \""+Columns.KEY_SCIENCE_LINK+"\" TEXT)";
        db.execSQL(CREATE_TABLE_LECTURER);
        db.execSQL(CREATE_TABLE_SCIENCE);
        db.execSQL(CREATE_TABLE_NEWS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCIENCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LECTURER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);
        // Create tables again
        onCreate(db);
    }
}
