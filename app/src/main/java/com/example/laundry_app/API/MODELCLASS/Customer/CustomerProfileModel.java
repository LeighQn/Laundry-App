package com.example.laundry_app.API.MODELCLASS.Customer;

import com.example.laundry_app.API.MODELCLASS.User;
import com.google.gson.annotations.SerializedName;

public class CustomerProfileModel {

    private User user;
    private String message;
    private String token;

    public CustomerProfileModel(){}

    public CustomerProfileModel(String message, User user){
        this.user = (User) user;
        this.message = message;
    }

    public CustomerProfileModel(String token) {

        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
