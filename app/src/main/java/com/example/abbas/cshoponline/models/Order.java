package com.example.abbas.cshoponline.models;

public class Order {


    private String buyerName;
    private String phoneNumber;
    private String address;
    private String orderNumber;
    private String totalCost;
    private String orderTitle;
    private String orderQuantity;
    private String orderPrice;


    public Order() {
    }

    public Order(String buyerName, String phoneNumber,
                 String address, String orderNumber,
                 String totalCost, String orderTitle,
                 String orderQuantity, String orderPrice) {
        this.buyerName = buyerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderNumber = orderNumber;
        this.totalCost = totalCost;
        this.orderTitle = orderTitle;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
    }

    public Order(String buyerName, String phoneNumber, String address, String orderNumber, String totalCost) {
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

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
}
