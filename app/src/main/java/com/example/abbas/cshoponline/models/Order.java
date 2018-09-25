package com.example.abbas.cshoponline.models;

public class Order {


    private String buyerName;
    private String phoneNumber;
    private String address;
    private String orderNumber;
    private String totalCost;

    public Order() {
    }

    public Order(String buyerName, String phoneNumber, String address, String orderNumber,String totalCost) {
        this.buyerName = buyerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderNumber = orderNumber;
        this.totalCost = totalCost;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }
}
