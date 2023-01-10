package com.example.laundry_app.API.MODELCLASS;

import com.google.gson.annotations.SerializedName;

public class NewStatusModel {

    @SerializedName("title")
    private String date;

    @SerializedName("body")
    private String customerName;

    private String type;     // walk-in? booking?

    @SerializedName("userId")
    private String total;
    private User user;

    private int id;

    public NewStatusModel(String date, String customerName, String classification, String total, int id, User user) {
        this.date = date;
        this.customerName = customerName;
        this.type = classification;
        this.total = total;
        this.id = id;
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getClassification() {
        return type;
    }

    public void setClassification(String classification) {
        this.type = classification;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setType(String type) {
        this.type = type;
    }
}
