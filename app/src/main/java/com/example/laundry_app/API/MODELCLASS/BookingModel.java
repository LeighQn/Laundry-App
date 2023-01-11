package com.example.laundry_app.API.MODELCLASS;

import java.util.List;

public class BookingModel {
    private String _id;
    private User customer;
    private int type;
    private String date;
    private BookingLaundryModel laundry;
    private int subTotal;
    private int total;
    private boolean paid;
    private int status;

    public BookingModel() {
    }

    public BookingModel(String _id, User customer, int type, String date, BookingLaundryModel laundry, int subTotal, int total, boolean paid, int status) {
        this._id = _id;
        this.customer = customer;
        this.type = type;
        this.date = date;
        this.laundry = laundry;
        this.subTotal = subTotal;
        this.total = total;
        this.paid = paid;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BookingLaundryModel getLaundry() {
        return laundry;
    }

    public void setLaundry(BookingLaundryModel laundry) {
        this.laundry = laundry;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
