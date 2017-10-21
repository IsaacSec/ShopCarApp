package com.example.isaac.shopcar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.isaac.shopcar.model.BuyRecord;
import com.example.isaac.shopcar.model.Product;

import java.util.ArrayList;

/**
 * Created by isaac on 10/21/17.
 */

public class BuyRecordCRUD {
    private CarShopDbHelper helper;

    public BuyRecordCRUD(Context context){
        helper = new CarShopDbHelper(context);
    }

    public void registerRecord(BuyRecord item){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CarShopContract.BuyRecord.ID_LIST, item.getListId());
        values.put(CarShopContract.BuyRecord.ID_PRODUCT, item.getProductId());
        values.put(CarShopContract.BuyRecord.QUANTITY, item.getQuantity());

        long newRowId = db.insert(CarShopContract.BuyRecord.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<BuyRecord> getRecords(){
        ArrayList<BuyRecord> records = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        String columns[] = {
                CarShopContract.BuyRecord.ID_LIST,
                CarShopContract.BuyRecord.ID_PRODUCT,
                CarShopContract.BuyRecord.QUANTITY
        };

        Cursor cursor = db.query(
                CarShopContract.BuyRecord.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            String id = getColumnValue(cursor, CarShopContract.BuyRecord.ID_LIST);
            String productId = getColumnValue(cursor, CarShopContract.BuyRecord.ID_PRODUCT);
            String quantity = getColumnValue(cursor, CarShopContract.BuyRecord.QUANTITY);

            records.add(new BuyRecord(id, productId, quantity));
        }

        return records;
    }

    public void updateBuyRecord(BuyRecord item){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(CarShopContract.BuyRecord.ID_LIST, item.getListId());
        values.put(CarShopContract.BuyRecord.ID_PRODUCT, item.getProductId());
        values.put(CarShopContract.BuyRecord.QUANTITY, item.getQuantity());

        db.update(
                CarShopContract.BuyRecord.TABLE_NAME,
                values,
                CarShopContract.BuyRecord.ID_LIST+"= ?",
                new String[]{item.getListId()}
        );
    }

    public void deleteBuyRecord(BuyRecord item){
        SQLiteDatabase db = helper.getReadableDatabase();

        db.delete(
                CarShopContract.BuyRecord.TABLE_NAME,
                CarShopContract.BuyRecord.ID_LIST +"= ?",
                new String[]{item.getListId()}
        );
    }

    public ArrayList<BuyRecord> getRecordsByListId(String target){
        ArrayList<BuyRecord> records = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        String columns[] = {
                CarShopContract.BuyRecord.ID_LIST,
                CarShopContract.BuyRecord.ID_PRODUCT,
                CarShopContract.BuyRecord.QUANTITY
        };

        String selectionArgs[] = {target};

        Cursor cursor = db.query(
                CarShopContract.BuyRecord.TABLE_NAME,
                columns,
                CarShopContract.BuyRecord.ID_LIST+"=?",
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            String id = getColumnValue(cursor, CarShopContract.BuyRecord.ID_LIST);
            String productId = getColumnValue(cursor, CarShopContract.BuyRecord.ID_PRODUCT);
            String quantity = getColumnValue(cursor, CarShopContract.BuyRecord.QUANTITY);

            records.add(new BuyRecord(id, productId, quantity));
        }

        return records;

    }

    private String getColumnValue (Cursor c, String column){
        return c.getString(c.getColumnIndexOrThrow(column));
    }
}
