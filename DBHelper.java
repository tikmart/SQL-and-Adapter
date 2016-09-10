package com.example.basic.myapplication;


import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{


    public DBHelper(Context context) {
        super(context, "MyDB", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

     db.execSQL("create table MyTable (" +
             "id integer primary key autoincrement, " +
             "name text" +
             ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table MyTable");
            onCreate(db);
        }
    }
}
