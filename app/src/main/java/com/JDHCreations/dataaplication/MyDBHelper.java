package com.JDHCreations.dataaplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "areas.db";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table area (id INTEGER PRIMARY KEY, name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists area");
        onCreate(db);
    }

    public void insertArea (SQLiteDatabase db, Area area){
        ContentValues values = new ContentValues();

        values.put("id", area.getId());
        values.put("name", area.getName());

        db.insert("area", null, values);
    }

    public ArrayList<Area> selectArea (SQLiteDatabase db){
        ArrayList<Area> areas = new ArrayList<Area>();
        Cursor filas = db.rawQuery("Select * from area", null);
        if (filas.moveToFirst()){
            do{
                Area area = new Area(filas.getInt(0), filas.getString(1));
                areas.add(area);
            }while(filas.moveToNext());
        }
        return areas;
    }
}
