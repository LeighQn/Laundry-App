package com.example.laundry_app.API.MODELCLASS.Staff;

import com.example.laundry_app.API.MODELCLASS.BookingModel;

public class BookingRequest {
    private BookingModel booking;
    private String message;

    public BookingRequest() {
    }

    public BookingRequest(BookingModel booking, String message) {
        this.booking = booking;
        this.message = message;
    }

    public BookingModel getBooking() {
        return booking;
    }

    public void setBooking(BookingModel booking) {
        this.booking = booking;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
