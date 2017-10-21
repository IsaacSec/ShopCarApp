package com.example.isaac.shopcar.model;

/**
 * Created by isaac on 10/21/17.
 */

public class BuyList {
    private String id;
    private String date;
    private String total;

    public BuyList(String id, String date, String total) {
        this.id = id;
        this.date = date;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
