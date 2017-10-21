package com.example.isaac.shopcar.model;

import java.io.Serializable;

/**
 * Created by isaac on 10/21/17.
 */

public class ProductBuy implements Serializable{

    private Product product;
    private String quantity;

    public ProductBuy(Product product, String quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
