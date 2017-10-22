package com.example.isaac.shopcar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.isaac.shopcar.model.BuyList;
import com.example.isaac.shopcar.model.BuyRecord;

import java.util.ArrayList;

/**
 * Created by isaac on 10/21/17.
 */

public class BuyListCRUD {
    private CarShopDbHelper helper;

    public BuyListCRUD(Context context){
        helper = new CarShopDbHelper(context);
    }

    public void registerBuyList(BuyList item){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CarShopContract.BuyList.DATE, item.getDate());
        values.put(CarShopContract.BuyList.TOTAL, item.getTotal());
        values.put(CarShopContract.BuyList.ELEMENTS, item.getElements());

        long newRowId = db.insert(CarShopContract.BuyList.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<BuyList> getBuyLists(){
        ArrayList<BuyList> records = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        String columns[] = {
                CarShopContract.BuyList.ID,
                CarShopContract.BuyList.DATE,
                CarShopContract.BuyList.TOTAL,
                CarShopContract.BuyList.ELEMENTS
        };

        Cursor cursor = db.query(
                CarShopContract.BuyList.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            String id = getColumnValue(cursor, CarShopContract.BuyList.ID);
            String date = getColumnValue(cursor, CarShopContract.BuyList.DATE);
            String total = getColumnValue(cursor, CarShopContract.BuyList.TOTAL);
            String elements = getColumnValue(cursor, CarShopContract.BuyList.ELEMENTS);

            records.add(new BuyList(id, date, total, elements));
        }

        return records;
    }

    public void updateBuyList(BuyList item){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(CarShopContract.BuyList.ID, item.getId());
        values.put(CarShopContract.BuyList.DATE, item.getDate());
        values.put(CarShopContract.BuyList.TOTAL, item.getTotal());
        values.put(CarShopContract.BuyList.ELEMENTS, item.getElements());

        db.update(
                CarShopContract.BuyList.TABLE_NAME,
                values,
                CarShopContract.BuyList.ID+"= ?",
                new String[]{item.getId()}
        );
    }

    public void deleteBuyList(BuyList item){
        SQLiteDatabase db = helper.getReadableDatabase();

        db.delete(
                CarShopContract.BuyList.TABLE_NAME,
                CarShopContract.BuyList.ID +"= ?",
                new String[]{item.getId()}
        );
    }

    public BuyList getBuyListById(String target){
        ArrayList<BuyList> records = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        String columns[] = {
                CarShopContract.BuyList.ID,
                CarShopContract.BuyList.DATE,
                CarShopContract.BuyList.TOTAL,
                CarShopContract.BuyList.ELEMENTS
        };

        String selectionArgs[] = {target};

        Cursor cursor = db.query(
                CarShopContract.BuyList.TABLE_NAME,
                columns,
                CarShopContract.BuyList.ID+"=?",
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            String id = getColumnValue(cursor, CarShopContract.BuyList.ID);
            String date = getColumnValue(cursor, CarShopContract.BuyList.DATE);
            String total = getColumnValue(cursor, CarShopContract.BuyList.TOTAL);
            String e =  getColumnValue(cursor, CarShopContract.BuyList.TOTAL);

            records.add(new BuyList(id, date, total, e));
        }

        return records.get(0);

    }

    private String getColumnValue (Cursor c, String column){
        return c.getString(c.getColumnIndexOrThrow(column));
    }
}
