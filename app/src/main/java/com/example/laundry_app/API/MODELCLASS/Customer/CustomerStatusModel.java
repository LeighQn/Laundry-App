package com.example.laundry_app.API.MODELCLASS.Customer;

import com.google.gson.annotations.SerializedName;

public class CustomerStatusModel {


    private String token;

    @SerializedName("title")
    private String date;

    @SerializedName("body")
    private String total;

    @SerializedName("userId")
    private String status;

    public CustomerStatusModel(String date, String total, String status) {
        this.token = token;
        this.date = date;
        this.total = total;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() {
        return date;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    public String getTotal() {
        return total;
    }

//    public void setTotal(String total) {
//        this.total = total;
//    }

    public String getStatus() {
        return status;
    }

//    public void setStatus(String status) {
//        this.status = status;
//    }
}
