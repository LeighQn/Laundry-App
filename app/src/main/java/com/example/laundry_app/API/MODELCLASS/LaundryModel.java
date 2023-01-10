package com.example.laundry_app.API.MODELCLASS;

public class LaundryModel {
    private float regular;
    private float white;
    private float maong;
    private float comforter;
    private float regularMin;
    private float whiteMin;
    private float maongMin;
    private float comforterMin;


    public LaundryModel(float regular, float white, float maong, float comforter, float regularMin, float whiteMin, float maongMin, float comforterMin) {
        this.regular = regular;
        this.white = white;
        this.maong = maong;
        this.comforter = comforter;
        this.regularMin = regularMin;
        this.whiteMin = whiteMin;
        this.maongMin = maongMin;
        this.comforterMin = comforterMin;
    }

    public float getRegular() {
        return regular;
    }

    public void setRegular(float regular) {
        this.regular = regular;
    }

    public float getWhite() {
        return white;
    }

    public void setWhite(float white) {
        this.white = white;
    }

    public float getMaong() {
        return maong;
    }

    public void setMaong(float maong) {
        this.maong = maong;
    }

    public float getComforter() {
        return comforter;
    }

    public void setComforter(float comforter) {
        this.comforter = comforter;
    }

    public float getRegularMin() {
        return regularMin;
    }

    public void setRegularMin(float regularMin) {
        this.regularMin = regularMin;
    }

    public float getWhiteMin() {
        return whiteMin;
    }

    public void setWhiteMin(float whiteMin) {
        this.whiteMin = whiteMin;
    }

    public float getMaongMin() {
        return maongMin;
    }

    public void setMaongMin(float maongMin) {
        this.maongMin = maongMin;
    }

    public float getComforterMin() {
        return comforterMin;
    }

    public void setComforterMin(float comforterMin) {
        this.comforterMin = comforterMin;
    }
}
