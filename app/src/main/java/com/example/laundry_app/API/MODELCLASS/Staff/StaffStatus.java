package com.example.laundry_app.API.MODELCLASS.Staff;

import com.example.laundry_app.API.MODELCLASS.Customer.LaundryBookModel;
import com.example.laundry_app.API.MODELCLASS.LaundryModel;
import com.example.laundry_app.API.MODELCLASS.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StaffStatus {

//    @SerializedName("title")                // must change to the exact columnName
//    private String date;
//
//    @SerializedName("body")                // must change to the exact columnName
//    private String customerName;
//
//    @SerializedName("userId")
//    private int total;
//    private int role;
//
//    // ============================== POST ============================== //
//    // ============================== POST ============================== //
//
//    // Right-click to Generate constructor
//    // Make id as comment since we are just using fake api
//
//
//    public StaffStatus(String date, String customerName, int total, int id) {
//        this.date = date;
//        this.customerName = customerName;
//        this.total = total;
//      //  this.id = id;
//    }
//
//    // ============================== GET ============================== //
//    // ============================== GET ============================== //
//
//    // Right-click to Generate getter
//
//
//    public String getDate() {
//        return date;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public int getTotal() {
//        return total;
//    }
//
//    public int getRole() {
//        return role;
//    }

    private User user;
    private String message;
    private String token;
    private LaundryBookModel laundryBookModel;


    @SerializedName("title")
    private String date;

    @SerializedName("body")
    private String name;

    @SerializedName("userId")
    private String total;


//    public StaffStatus(String message, User user){
//        this.user = (User) user;
//        this.message = message;
//    }
//
//    public StaffStatus(String token) {
//
//        this.token = token;
//    }


    public StaffStatus(User user, String message, String token, LaundryBookModel laundryBookModel, String date, String name, String total) {
        this.user = user;
        this.message = message;
        this.token = token;
        this.laundryBookModel = laundryBookModel;
        this.date = date;
        this.name = name;
        this.total = total;
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

    public LaundryBookModel getLaundryBookModel() {
        return laundryBookModel;
    }

    public void setLaundryBookModel(LaundryBookModel laundryBookModel) {
        this.laundryBookModel = laundryBookModel;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
