package com.vinicius.menu.Models;

public class Food {
    private double price;
    private int time;
    private String name;
    private String description;
    private int imgFood;
    private boolean isSelected; // Campo adicional para rastrear se o item está selecionado

    public Food(double price, int time, String name, String description, int imgFood) {
        this.price = price;
        this.time = time;
        this.name = name;
        this.description = description;
        this.imgFood = imgFood;
        this.isSelected = false; // Inicialmente, o item não está selecionado
    }

    // Getters e setters para todos os campos
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}

