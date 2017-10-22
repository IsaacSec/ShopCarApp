package com.example.isaac.shopcar.model;

/**
 * Created by isaac on 10/22/17.
 */

public class BuyHistory {
    private String date;
    private String elements;
    private String total;

    public BuyHistory(String date, String elements, String total) {
        this.date = date;
        this.elements = elements;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
