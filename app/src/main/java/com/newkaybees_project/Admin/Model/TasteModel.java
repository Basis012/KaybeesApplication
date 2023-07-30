package com.newkaybees_project.Admin.Model;

public class TasteModel {
    String tastename, tastedescription, tasteprice, discountedprice, tastequantity;

    public TasteModel(String tastename, String tastedescription, String tasteprice, String discountedprice, String tastequantity) {
        this.tastename = tastename;
        this.tastedescription = tastedescription;
        this.tasteprice = tasteprice;
        this.discountedprice = discountedprice;
        this.tastequantity = tastequantity;
    }
    public  TasteModel(){

    }

    public String getTastename() {
        return tastename;
    }

    public void setTastename(String tastename) {
        this.tastename = tastename;
    }

    public String getTastedescription() {
        return tastedescription;
    }

    public void setTastedescription(String tastedescription) {
        this.tastedescription = tastedescription;
    }

    public String getTasteprice() {
        return tasteprice;
    }

    public void setTasteprice(String tasteprice) {
        this.tasteprice = tasteprice;
    }

    public String getDiscountedprice() {
        return discountedprice;
    }

    public void setDiscountedprice(String discountedprice) {
        this.discountedprice = discountedprice;
    }

    public String getTastequantity() {
        return tastequantity;
    }

    public void setTastequantity(String tastequantity) {
        this.tastequantity = tastequantity;
    }
}
