package com.example.isaac.shopcar.model;

import java.io.Serializable;

/**
 * Created by isaac on 10/21/17.
 */

public class BuyList implements Serializable{
    private String id;
    private String date;
    private String total;
    private String elements;

    public BuyList(String id, String date, String total, String e) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.elements = e;
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

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }
}
