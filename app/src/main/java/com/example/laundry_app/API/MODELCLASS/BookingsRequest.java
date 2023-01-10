package com.example.laundry_app.API.MODELCLASS;

import java.util.List;

public class BookingsRequest {
    private List<BookingModel> bookings;
    private String message;

    public BookingsRequest() {
    }

    public BookingsRequest(List<BookingModel> bookings, String message) {
        this.bookings = bookings;
        this.message = message;
    }

    public List<BookingModel> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingModel> bookings) {
        this.bookings = bookings;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
