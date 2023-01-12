package com.example.laundry_app.API.MODELCLASS;

public class PriceRequest {
    private PriceModel laundry;
    private String message;

    public PriceRequest() {

    }

    public PriceRequest(PriceModel laundry, String message) {
        this.laundry = laundry;
        this.message = message;
    }

    public PriceModel getLaundry() {
        return laundry;
    }

    public void setLaundry(PriceModel laundry) {
        this.laundry = laundry;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
