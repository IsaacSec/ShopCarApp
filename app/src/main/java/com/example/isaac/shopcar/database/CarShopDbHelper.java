package com.example.isaac.shopcar.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.isaac.shopcar.database.SQLQuery.*;
/**
 * Created by isaac on 10/17/17.
 */

public class CarShopDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CarShop.db";

    public CarShopDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(SQLQuery.SQL_CREATE_CARSHOP_TABLES);
        db.execSQL(SQLQuery.SQL_CREATE_PRODUCT_TABLE);
        db.execSQL(SQLQuery.SQL_CREATE_BUY_LIST_TABLE);
        db.execSQL(SQLQuery.SQL_CREATE_BUY_RECORD_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQLQuery.SQL_DELETE_TABLES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
