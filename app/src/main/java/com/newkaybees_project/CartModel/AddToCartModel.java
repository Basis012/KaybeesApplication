package com.newkaybees_project.CartModel;

import android.text.TextUtils;

public class AddToCartModel {
    String title;
    String quantity;
    String price;
    String discountedprice;
    String tasteId;
    String userId;
    int mtotal;

    public AddToCartModel(String title, String quantity, String price, String discountedprice, String tasteId, String userId,int mtotal) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.discountedprice = discountedprice;
        this.tasteId = tasteId;
        this.userId = userId;
        this.mtotal = mtotal;
    }

    public AddToCartModel() {
    }

    public int getMtotal() {
        return mtotal;
    }

    public void setMtotal(int mtotal) {
        this.mtotal = mtotal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountedprice() {
        return discountedprice;
    }

    public void setDiscountedprice(String discountedprice) {
        this.discountedprice = discountedprice;
    }

    public String getTasteId() {
        return tasteId;
    }

    public void setTasteId(String tasteId) {
        this.tasteId = tasteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
