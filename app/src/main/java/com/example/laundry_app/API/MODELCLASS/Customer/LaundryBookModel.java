package com.example.laundry_app.API.MODELCLASS.Customer;

import com.example.laundry_app.API.MODELCLASS.LaundryModel;
import com.example.laundry_app.API.MODELCLASS.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaundryBookModel {

    private User customer;
    private int type;
    private String date;
    private List<LaundryModel> laundries;
    private float subTotal;
    private float total;
    private boolean paid;
    private int status;
    private LaundryModel weight;

    public LaundryModel getWeight(){
        return weight;
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

    public List<LaundryModel> getLaundries() {
        return laundries;
    }

    public void setLaundries(List<LaundryModel> laundries) {
        this.laundries = laundries;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setWeight(LaundryModel weight) {
        this.weight = weight;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
//    private int regularPrice;
//    private int regularMin;
//    private int whitePrice;
//    private int whiteMin;
//    private int maongPrice;
//    private int maongMin;
//    private int comforterPrice;
//    private int comforterMin;
//
//    //POST
//
//
//    public int getRegularPrice() {
//        return regularPrice;
//    }
//
//    public void setRegularPrice(int regularPrice) {
//        this.regularPrice = regularPrice;
//    }
//
//    public int getRegularMin() {
//        return regularMin;
//    }
//
//    public void setRegularMin(int regularMin) {
//        this.regularMin = regularMin;
//    }
//
//    public int getWhitePrice() {
//        return whitePrice;
//    }
//
//    public void setWhitePrice(int whitePrice) {
//        this.whitePrice = whitePrice;
//    }
//
//    public int getWhiteMin() {
//        return whiteMin;
//    }
//
//    public void setWhiteMin(int whiteMin) {
//        this.whiteMin = whiteMin;
//    }
//
//    public int getMaongPrice() {
//        return maongPrice;
//    }
//
//    public void setMaongPrice(int maongPrice) {
//        this.maongPrice = maongPrice;
//    }
//
//    public int getMaongMin() {
//        return maongMin;
//    }
//
//    public void setMaongMin(int maongMin) {
//        this.maongMin = maongMin;
//    }
//
//    public int getComforterPrice() {
//        return comforterPrice;
//    }
//
//    public void setComforterPrice(int comforterPrice) {
//        this.comforterPrice = comforterPrice;
//    }
//
//    public int getComforterMin() {
//        return comforterMin;
//    }
//
//    public void setComforterMin(int comforterMin) {
//        this.comforterMin = comforterMin;
//    }
//
//    public LaundryBookModel(int regularPrice, int regularMin, int whitePrice, int whiteMin, int maongPrice, int maongMin, int comforterPrice, int comforterMin) {
//        this.regularPrice = regularPrice;
//        this.regularMin = regularMin;
//        this.whitePrice = whitePrice;
//        this.whiteMin = whiteMin;
//        this.maongPrice = maongPrice;
//        this.maongMin = maongMin;
//        this.comforterPrice = comforterPrice;
//        this.comforterMin = comforterMin;
//    }
}
