package com.example.laundry_app.API.MODELCLASS.Customer;

import com.example.laundry_app.API.MODELCLASS.User;

public class CustomerHomeModel {

    private int bookings;
    private int delivered;
    private User user;
    private String token;



    public CustomerHomeModel(User user, String token){
        this.user = (User) user;
        this.token = token;
    }

    public CustomerHomeModel(int bookings, int delivered, User user) {
        this.bookings = bookings;
        this.delivered = delivered;
        this.user = user;
    }

    public int getBookings() {
        return bookings;
    }

    public void setBookings(int bookings) {
        this.bookings = bookings;
    }

    public int getDelivered() {
        return delivered;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
