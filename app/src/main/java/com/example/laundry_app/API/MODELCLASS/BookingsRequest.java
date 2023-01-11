package com.example.laundry_app.API.MODELCLASS;

import java.util.ArrayList;
import java.util.List;

public class BookingsRequest {
    private ArrayList<BookingModel> bookings;
    private String message;
    private User user;

    public BookingsRequest() {
    }

    public BookingsRequest(ArrayList<BookingModel> bookings, String message) {
        this.bookings = bookings;
        this.message = message;
    }

    public ArrayList<BookingModel> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<BookingModel> bookings) {
        this.bookings = bookings;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
