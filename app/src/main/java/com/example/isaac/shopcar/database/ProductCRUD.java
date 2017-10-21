package com.example.isaac.shopcar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.isaac.shopcar.model.Product;

import java.util.ArrayList;

/**
 * Created by isaac on 10/17/17.
 */

public class ProductCRUD {

    private CarShopDbHelper helper;

    public ProductCRUD(Context context){
        helper = new CarShopDbHelper(context);
    }

    public void registerProduct(Product item){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CarShopContract.Product.NAME, item.getName());
        values.put(CarShopContract.Product.PRICE, item.getPrice());
        values.put(CarShopContract.Product.PHOTO_URL, item.getPrice());

        long newRowId = db.insert(CarShopContract.Product.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        String columns[] = {
            CarShopContract.Product.ID,
            CarShopContract.Product.NAME,
            CarShopContract.Product.PRICE,
            CarShopContract.Product.PHOTO_URL
        };

        Cursor cursor = db.query(
            CarShopContract.Product.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        );

        while (cursor.moveToNext()){
            String id = getColumnValue(cursor, CarShopContract.Product.ID);
            String name = getColumnValue(cursor, CarShopContract.Product.NAME);
            String price = getColumnValue(cursor, CarShopContract.Product.PRICE);
            String photo = getColumnValue(cursor, CarShopContract.Product.PHOTO_URL);

            products.add(new Product(id, name, price, photo));
        }

        return products;
    }

    public void updateProduct(Product item){
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(CarShopContract.Product.ID, item.getID());
        values.put(CarShopContract.Product.NAME, item.getName());
        values.put(CarShopContract.Product.PRICE, item.getPrice());
        values.put(CarShopContract.Product.PHOTO_URL, item.getPrice());

        db.update(
            CarShopContract.Product.TABLE_NAME,
            values,
            "id = ?",
            new String[]{item.getID()}
        );
    }

    public void deleteProduct(Product item){
        SQLiteDatabase db = helper.getReadableDatabase();

        db.delete(
                CarShopContract.Product.TABLE_NAME,
                "id = ?",
                new String[]{item.getID()}
        );
    }

    public Product getProductByName(String target){
        SQLiteDatabase db = helper.getReadableDatabase();
        Product p = null;

        String columns[] = {
                CarShopContract.Product.ID,
                CarShopContract.Product.NAME,
                CarShopContract.Product.PRICE,
                CarShopContract.Product.PHOTO_URL
        };

        String selectionArgs[] = {target};

        Cursor cursor = db.query(
                CarShopContract.Product.TABLE_NAME,
                columns,
                CarShopContract.Product.NAME + "=?",
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            String id = getColumnValue(cursor, CarShopContract.Product.ID);
            String name = getColumnValue(cursor, CarShopContract.Product.NAME);
            String price = getColumnValue(cursor, CarShopContract.Product.PRICE);
            String photo = getColumnValue(cursor, CarShopContract.Product.PHOTO_URL);

            p = new Product(id, name, price, photo);
        }


        return p;
    }

    private String getColumnValue (Cursor c, String column){
        return c.getString(c.getColumnIndexOrThrow(column));
    }
}
