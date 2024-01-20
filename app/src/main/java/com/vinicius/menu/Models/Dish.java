package com.vinicius.menu.Models;

public class Dish {
    public String price;
    public String time;
    public String name;
    public String description;
    public int imgFood;
    public boolean selected;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgFood() {
        return imgFood;
    }

    public void setImgFood(int imgFood) {
        this.imgFood = imgFood;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // Construtor
    public Dish(String price, String time, String name, String description, int imgFood, boolean selected) {
        this.price = price;
        this.time = time;
        this.name = name;
        this.description = description;
        this.imgFood = imgFood;

    }

}
