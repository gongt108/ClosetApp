package com.example.closetapp.Domain;

import java.io.Serializable;

public class ClothingDomain implements Serializable {
    private int id;
    public byte[] pic;
    public String brand;
    public int categoryPosition;
    public int colorPosition;
    public boolean isChecked;

//    public ClothingDomain (byte[] pic, String category, String color, String brand) {
//        this.pic = pic;
//        this.category = category;
//        this.color = color;
//        this.brand = brand;
//    }
//
//    public ClothingDomain (byte[] pic, String category, int categoryPosition, int colorPosition) {
//        this.pic = pic;
//        this.category = category;
//        this.categoryPosition = categoryPosition;
//        this.colorPosition = colorPosition;
//    }

    public ClothingDomain(int id, byte[] pic, String brand, int categoryPosition, int colorPosition, boolean isChecked) {
        this.id = id;
        this.pic = pic;
        this.brand = brand;
        this.categoryPosition = categoryPosition;
        this.colorPosition = colorPosition;
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCategoryPosition() {
        return categoryPosition;
    }

    public void setCategoryPosition(int categoryPosition) {
        this.categoryPosition = categoryPosition;
    }

    public int getColorPosition() {
        return colorPosition;
    }

    public void setColorPosition(int colorPosition) {
        this.colorPosition = colorPosition;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
