package com.example.isaac.shopcar.model;

import java.io.Serializable;

/**
 * Created by isaac on 10/21/17.
 */

public class BuyRecord implements Serializable {
    private String listId;
    private String productId;
    private String quantity;

    public BuyRecord(String listId, String productId, String quantity) {
        this.listId = listId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String id) {
        this.listId = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
