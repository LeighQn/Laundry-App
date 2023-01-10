package com.example.laundry_app.API.MODELCLASS.Admin;

import com.example.laundry_app.API.MODELCLASS.User;

public class AdminHomeModel {

    private int numberOfBooking;
    private int numberOfPickup;
    private int dailyIncome;
    private int monthlyIncome;
    private User user;

    public AdminHomeModel() {
    }

    public AdminHomeModel(int numberOfBooking, int numberOfPickup, int dailyIncome, int monthlyIncome) {
        this.numberOfBooking = numberOfBooking;
        this.numberOfPickup = numberOfPickup;
        this.dailyIncome = dailyIncome;
        this.monthlyIncome = monthlyIncome;
    }

    public int getNumberOfBooking() {
        return numberOfBooking;
    }

    public void setNumberOfBooking(int numberOfBooking) {
        this.numberOfBooking = numberOfBooking;
    }

    public int getNumberOfPickup() {
        return numberOfPickup;
    }

    public void setNumberOfPickup(int numberOfPickup) {
        this.numberOfPickup = numberOfPickup;
    }

    public int getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(int dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
