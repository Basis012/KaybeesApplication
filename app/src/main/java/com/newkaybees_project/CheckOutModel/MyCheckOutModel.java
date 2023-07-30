package com.newkaybees_project.CheckOutModel;

public class MyCheckOutModel {
    String address;
    String phonenumber;

    public MyCheckOutModel(String address, String phonenumber) {
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public MyCheckOutModel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
