package com.example.laundry_app.API.MODELCLASS;

public class LaundryMaterialsModel {
    private String material;
    private int unit;
    private User user;

    public LaundryMaterialsModel() {
    }

    public void setaundryMaterialsData(String material, int unit, User user) {
        this.material = material;
        this.unit = unit;
        this.user = user;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
