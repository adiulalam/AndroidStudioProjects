package com.example.madpropertypal_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager
{
    private DatabaseHelper dbHepler;
    private Context context;
    private SQLiteDatabase database;

    //constructor
    public DBManager(Context c)
    {
        context = c;
    }

    public DBManager open() throws SQLException{
        dbHepler= new DatabaseHelper(context);
        database = dbHepler.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHepler.close();
    }

    //inserting database
    public void insert(String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.DESC, desc);
        database.insert(DatabaseHelper.TABLE_NAME, null , contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[] {DatabaseHelper._ID,
        DatabaseHelper.NAME,DatabaseHelper.DESC};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    // updating database
    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.DESC, desc);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    //deleting database
    public void delete (long _id) {
        database.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper._ID + " = " + _id, null);
    }


}
