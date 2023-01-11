package com.example.laundry_app.API.MODELCLASS;

public class User {
    private String name;
    private String username;
    private String mobileNumber;
    private String address;
    private int role;
    private String token;
    private String longitude;
    private String latitude;
    private String _id;

 //   public User(){}

    public User(String name, String username, String mobileNumber, String address, int role, String longitude, String latitude, String _id) {
        this.name = name;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.role = role;
        this.longitude = longitude;
        this.latitude = latitude;
        this._id = _id;
    }

//    public User(String name, String mobileNumber, String address, int role) {
//        this.name = name;
//        this.mobileNumber = mobileNumber;
//        this.address = address;
//        this.role = role;
//    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
