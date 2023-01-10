package com.example.laundry_app.USERS.Customer.Screens.DataSetAndAdapter;

public class CustomerBookings {
    String cBookingDates;
    String cBookingPayables;
    String cBookingStatus;

    public CustomerBookings(String date, String payables, String status) {
        this.cBookingDates = date;
        this.cBookingPayables = payables;
        this.cBookingStatus = status;
    }
}
