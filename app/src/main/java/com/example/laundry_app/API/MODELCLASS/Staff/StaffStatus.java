package com.example.laundry_app.API.MODELCLASS.Staff;

import com.google.gson.annotations.SerializedName;

public class StaffStatus {

    @SerializedName("title")                // must change to the exact columnName
    private String date;

    @SerializedName("body")                // must change to the exact columnName
    private String customerName;

    @SerializedName("userId")
    private int total;
    private int role;

    // ============================== POST ============================== //
    // ============================== POST ============================== //

    // Right-click to Generate constructor
    // Make id as comment since we are just using fake api


    public StaffStatus(String date, String customerName, int total, int id) {
        this.date = date;
        this.customerName = customerName;
        this.total = total;
      //  this.id = id;
    }

    // ============================== GET ============================== //
    // ============================== GET ============================== //

    // Right-click to Generate getter


    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getTotal() {
        return total;
    }

    public int getRole() {
        return role;
    }
}
