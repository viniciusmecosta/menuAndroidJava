package com.vinicius.menu.Models;

public class Dish {
    public double price;
    public int time;
    public String name;
    public String description;
    public int imgFood;
    public boolean selected;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
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

    // Construtor
    public Dish(double price, int time, String name, String description, int imgFood) {
        this.price = price;
        this.time = time;
        this.name = name;
        this.description = description;
        this.imgFood = imgFood;

    }

}
