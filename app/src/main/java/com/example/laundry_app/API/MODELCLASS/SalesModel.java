package com.example.laundry_app.API.MODELCLASS;

import com.example.laundry_app.API.MODELCLASS.Customer.LaundryBookModel;

public class SalesModel {

    private User user;
    private LaundryBookModel laundryBookModel; // total

    public SalesModel(User user, LaundryBookModel laundryBookModel) {
        this.user = user;
        this.laundryBookModel = laundryBookModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LaundryBookModel getLaundryBookModel() {
        return laundryBookModel;
    }

    public void setLaundryBookModel(LaundryBookModel laundryBookModel) {
        this.laundryBookModel = laundryBookModel;
    }
}
