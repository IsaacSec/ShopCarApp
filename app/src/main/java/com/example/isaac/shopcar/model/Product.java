package com.example.isaac.shopcar.model;

/**
 * Created by isaac on 10/20/17.
 */

public class Product {
    private String ID;
    private String name;
    private String price;
    private String photoUrl;

    public Product(String ID, String name, String price, String photoUrl) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.photoUrl = photoUrl;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
