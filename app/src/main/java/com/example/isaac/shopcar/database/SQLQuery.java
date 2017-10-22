package com.example.isaac.shopcar.database;

/**
 * Created by isaac on 10/17/17.
 */

import com.example.isaac.shopcar.database.CarShopContract.*;

public class SQLQuery {
    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_PRODUCT_TABLE =
            "CREATE TABLE " + Product.TABLE_NAME + " (" +
                    Product.ID + " INTEGER PRIMARY KEY," +
                    Product.NAME + TEXT_TYPE + COMMA_SEP +
                    Product.PRICE + TEXT_TYPE + COMMA_SEP +
                    Product.PHOTO_URL + TEXT_TYPE +
                    " );";

    public static final String SQL_CREATE_BUY_LIST_TABLE =
            "CREATE TABLE " + BuyList.TABLE_NAME + " (" +
                    BuyList.ID + " INTEGER PRIMARY KEY," +
                    BuyList.DATE + TEXT_TYPE + COMMA_SEP +
                    BuyList.TOTAL + TEXT_TYPE + COMMA_SEP +
                    BuyList.ELEMENTS + TEXT_TYPE +
                    " );";

    public static final String SQL_CREATE_BUY_RECORD_TABLE =
            "CREATE TABLE " + BuyRecord.TABLE_NAME + " (" +
                    BuyRecord.ID_LIST + TEXT_TYPE + COMMA_SEP +
                    BuyRecord.ID_PRODUCT + TEXT_TYPE + COMMA_SEP +
                    BuyRecord.QUANTITY + TEXT_TYPE +
                    " );";

    public static final String SQL_CREATE_CARSHOP_TABLES =
            SQL_CREATE_PRODUCT_TABLE + "\n" +
            SQL_CREATE_BUY_LIST_TABLE + "\n" +
            SQL_CREATE_BUY_RECORD_TABLE;

    public static final String SQL_DELETE_TABLES =
            "DROP TABLE IF EXISTS " + Product.TABLE_NAME + "\n" +
            "DROP TABLE IF EXISTS " + BuyList.TABLE_NAME + "\n" +
            "DROP TABLE IF EXISTS " + BuyRecord.TABLE_NAME ;
            ;
}
