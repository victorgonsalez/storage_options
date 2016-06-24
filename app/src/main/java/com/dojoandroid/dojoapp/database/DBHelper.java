package com.dojoandroid.dojoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vgonsalez on 6/20/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database_dojo.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_PRODUCT = "Product";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_STATUS = "STATUS";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PRODUCT = "CREATE TABLE Product (_ID integer primary key autoincrement, NAME TEXT NOT NULL, PRICE REAL NOT NULL, STATUS TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
