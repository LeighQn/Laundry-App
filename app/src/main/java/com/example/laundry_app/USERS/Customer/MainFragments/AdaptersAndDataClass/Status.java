package com.example.laundry_app.USERS.Customer.MainFragments.AdaptersAndDataClass;


public class Status  {

    public String statusDate;
    String statusPayables;
    String statusStatus;

    public Status(String date, String payable, String status) {
        this.statusDate = date;
        this.statusPayables = payable;
        this.statusStatus = status;
    }
}