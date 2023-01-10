package com.example.laundry_app.API.MODELCLASS;

public class BookingLaundryModel {
    private int regular;
    private int white;
    private int maong;
    private int comforter;

    public BookingLaundryModel() {
    }

    public BookingLaundryModel(int regular, int white, int maong, int comforter) {
        this.regular = regular;
        this.white = white;
        this.maong = maong;
        this.comforter = comforter;
    }

    public int getRegular() {
        return regular;
    }

    public void setRegular(int regular) {
        this.regular = regular;
    }

    public int getWhite() {
        return white;
    }

    public void setWhite(int white) {
        this.white = white;
    }

    public int getMaong() {
        return maong;
    }

    public void setMaong(int maong) {
        this.maong = maong;
    }

    public int getComforter() {
        return comforter;
    }

    public void setComforter(int comforter) {
        this.comforter = comforter;
    }
}
