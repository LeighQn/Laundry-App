package com.example.laundry_app.API.MODELCLASS;


import com.google.gson.annotations.SerializedName;

public class Login {

    private String username;
    private String password;
    private String message;
    private String token;
    private User user;

    // __________ POST __________ //
    // __________ POST __________ //


    public Login(){

    }
    // add String userId to the parameter
    public Login(String message, String token, User user) {
        this.message = message;
        this.token = token;
        this.user = (User) user;
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;

    }


    // __________ GETTER / SETTER __________ //
    // __________ GETTER / SETTER __________ //


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
