package com.example.laundry_app.API.MODELCLASS;

import com.example.laundry_app.API.MODELCLASS.Customer.LaundryBookModel;

public class SalesModel {

    private User user;
    private Login login;
    private LaundryBookModel laundryBookModel; // total
    private String token;

    public SalesModel() {
    }

    public SalesModel(User user, LaundryBookModel laundryBookModel) {
        this.user = user;
        this.laundryBookModel = laundryBookModel;
    }

    public SalesModel(LaundryBookModel laundryBookModel) {
        this.laundryBookModel = laundryBookModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public LaundryBookModel getLaundryBookModel() {
        return laundryBookModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLaundryBookModel(LaundryBookModel laundryBookModel) {
        this.laundryBookModel = laundryBookModel;
    }
}
