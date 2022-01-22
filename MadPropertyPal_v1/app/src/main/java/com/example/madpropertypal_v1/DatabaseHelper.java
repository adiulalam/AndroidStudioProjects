package com.example.madpropertypal_v1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    //table name
    public static final String TABLE_NAME = "PROPERTIES";

    //table columns
    public static final String _ID = "_id"; //ID
    public static final String NAME = "name"; //PROPERTY NAME AND NUMBER
    public static final String DESC = "desc"; //DESCRIPTION

    //database info
    static final String DB_NAME =  "PROPERTIES_APP.DB";

    //database version
    static final int DB_VERSION = 1;

    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + DESC + " TEXT);";

    //constructor
    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //executing query
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
