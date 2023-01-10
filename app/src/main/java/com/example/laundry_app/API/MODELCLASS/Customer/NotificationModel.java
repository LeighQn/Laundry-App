package com.example.laundry_app.API.MODELCLASS.Customer;

import com.example.laundry_app.API.MODELCLASS.User;

public class NotificationModel {
    private User user;
    private String message;
    private String token;
    private String notification;

    public NotificationModel(String message, User user){
        this.user = (User) user;
        this.message = message;
    }

    public NotificationModel(User user, String message, String token, String notification) {
        this.user = user;
        this.message = message;
        this.token = token;
        this.notification = notification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
