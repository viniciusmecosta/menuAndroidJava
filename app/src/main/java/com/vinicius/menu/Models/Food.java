package com.vinicius.menu.Models;

public class Food {
    private final double price;
    private final int time;
    private String name;
    private final String description;
    private final int imgFood;
    private boolean isSelected;
    private final String category;

    public Food(double price, int time, String name, String description, int imgFood, String category) {
        this.price = price;
        this.time = time;
        this.name = name;
        this.description = description;
        this.imgFood = imgFood;
        this.isSelected = false;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public int getTime() {
        return time;
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

    public int getImgFood() {
        return imgFood;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCategory() {
        return category;
    }
}
