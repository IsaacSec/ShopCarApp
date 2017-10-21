package com.example.isaac.shopcar.database;

import android.provider.BaseColumns;

/**
 * Created by isaac on 10/17/17.
 */

public final class CarShopContract {

    private CarShopContract(){}

    public static class Product implements BaseColumns{
        public static final String TABLE_NAME = "product";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String PHOTO_URL = "photo_url";
    }

    public static class BuyList implements BaseColumns{
        public static final String TABLE_NAME = "buy_list";
        public static final String ID = "id";
        public static final String DATE = "buy_date";
    }

    public static class BuyRecord implements BaseColumns{
        public static final String TABLE_NAME = "buy_record";
        public static final String ID_LIST = "id_list";
        public static final String ID_PRODUCT = "id_product";
        public static final String QUANTITY = "quantity";
    }

}
