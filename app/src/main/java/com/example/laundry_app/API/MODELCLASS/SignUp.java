package com.example.laundry_app.API.MODELCLASS;

import com.google.gson.annotations.SerializedName;

public class SignUp {

    private String name;
    private String username;
    private String password;
    private String mobileNumber;
    private int role;
    private String latitude;
    private String longitude;
    private String address;
    private String message;

    //Post

    // ADD LAT AND LONG TO THE PARAMETER
    public SignUp(String name, String username, String mobileNumber, String password, int role, String address, String longitude, String latitude) {
        this.name = name;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.role = role;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
