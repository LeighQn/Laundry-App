package com.example.laundry_app.API.MODELCLASS;

import java.util.ArrayList;

public class StaffsRequest {
    private String message;
    private ArrayList<User> staffs;

    public StaffsRequest() {
    }

    public StaffsRequest(String message, ArrayList<User> staffs) {
        this.message = message;
        this.staffs = staffs;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<User> getStaffs() {
        return staffs;
    }

    public void setStaffs(ArrayList<User> staffs) {
        this.staffs = staffs;
    }
}
