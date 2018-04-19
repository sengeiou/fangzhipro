package com.fangzhi.dafangzhi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fangzhi.dafangzhi.activity.city.bean.City;


/**
 * Created by smacr on 2016/9/20.
 */
public class MyDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME= "dfzcity";
    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS "+
                TABLE_NAME+ "("+
                City.ID +" integer primary key,"+
                City.LEVEL+ " varchar,"+
                City.E_NAME+ " varchar,"+
                City.C_NAME+ " varchar"+
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }
}
