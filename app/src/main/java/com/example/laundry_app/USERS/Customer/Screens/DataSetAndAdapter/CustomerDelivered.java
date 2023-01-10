package com.example.laundry_app.USERS.Customer.Screens.DataSetAndAdapter;

public class CustomerDelivered {
    String cDeliveredDates;
    String cDeliveredPayables;
    String cDeliveredStatus;

    public CustomerDelivered(String date, String payables, String status) {
        this.cDeliveredDates = date;
        this.cDeliveredPayables = payables;
        this.cDeliveredStatus = status;
    }
}
