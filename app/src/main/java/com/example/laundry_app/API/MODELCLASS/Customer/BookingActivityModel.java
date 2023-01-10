package com.example.laundry_app.API.MODELCLASS.Customer;

import com.example.laundry_app.API.MODELCLASS.User;

public class BookingActivityModel {

    private String name;
    private String address;
    private String mobileNumber;
    private String date;
    private String total;
    private User role;

    public BookingActivityModel(String name, String address, String mobileNumber, String date, String total, User role) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.date = date;
        this.total = total;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
