package com.example.laundry_app.API.MODELCLASS;

public class ActivationRequest {
    private String message;
    private User user;

    public ActivationRequest() {
    }

    public ActivationRequest(String message, User user) {
        this.message = message;
        this.user = user;
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
