package com.example.laundry_app.API.MODELCLASS;

public class PriceModel {
    private int regularPrice;
    private int regularMin;
    private int whitePrice;
    private int whiteMin;
    private int maongPrice;
    private int maongMin;
    private int comforterPrice;
    private int comforterMin;

    public PriceModel() {
    }

    public PriceModel(int regularPrice, int regularMin, int whitePrice, int whiteMin, int maongPrice, int maongMin, int comforterPrice, int comforterMin) {
        this.regularPrice = regularPrice;
        this.regularMin = regularMin;
        this.whitePrice = whitePrice;
        this.whiteMin = whiteMin;
        this.maongPrice = maongPrice;
        this.maongMin = maongMin;
        this.comforterPrice = comforterPrice;
        this.comforterMin = comforterMin;
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(int regularPrice) {
        this.regularPrice = regularPrice;
    }

    public int getRegularMin() {
        return regularMin;
    }

    public void setRegularMin(int regularMin) {
        this.regularMin = regularMin;
    }

    public int getWhitePrice() {
        return whitePrice;
    }

    public void setWhitePrice(int whitePrice) {
        this.whitePrice = whitePrice;
    }

    public int getWhiteMin() {
        return whiteMin;
    }

    public void setWhiteMin(int whiteMin) {
        this.whiteMin = whiteMin;
    }

    public int getMaongPrice() {
        return maongPrice;
    }

    public void setMaongPrice(int maongPrice) {
        this.maongPrice = maongPrice;
    }

    public int getMaongMin() {
        return maongMin;
    }

    public void setMaongMin(int maongMin) {
        this.maongMin = maongMin;
    }

    public int getComforterPrice() {
        return comforterPrice;
    }

    public void setComforterPrice(int comforterPrice) {
        this.comforterPrice = comforterPrice;
    }

    public int getComforterMin() {
        return comforterMin;
    }

    public void setComforterMin(int comforterMin) {
        this.comforterMin = comforterMin;
    }
}
