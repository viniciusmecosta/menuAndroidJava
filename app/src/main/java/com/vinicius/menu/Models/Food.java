package com.vinicius.menu.Models;

// A classe Food representa um item de comida no menu.
public class Food {
    // Variáveis para armazenar as propriedades de um item de comida.
    private final double price; // Preço do item.
    private final int time; // Tempo estimado de preparo em minutos.
    private String name; // Nome do item.
    private final String description; // Descrição do item.
    private final int imgFood; // ID do recurso da imagem do item.
    private boolean isSelected; // Indica se o item foi selecionado no menu.
    private final String category; // Categoria do item (ex: prato principal, sobremesa).
    private String observation; // Observações adicionais feitas para o item.

    // Construtor da classe Food para inicializar um novo item de comida com as propriedades fornecidas.
    public Food(double price, int time, String name, String description, int imgFood, String category) {
        this.price = price;
        this.time = time;
        this.name = name;
        this.description = description;
        this.imgFood = imgFood;
        this.isSelected = false; // Inicialmente, o item não está selecionado.
        this.category = category;
        this.observation = ""; // Inicialmente, não há observações.
    }

    // Métodos getters para acessar as propriedades do item de comida.
    public double getPrice() {
        return price;
    }

    public int getTime() {
        return time;
    }

    public String getName() {
        return name;
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

    public String getCategory() {
        return category;
    }

    public String getObservation() {
        return observation;
    }

    // Métodos setters para modificar algumas propriedades do item de comida.
    public void setName(String name) {
        this.name = name; // Atualiza o nome do item.
    }

    public void setSelected(boolean selected) {
        isSelected = selected; // Atualiza o estado de seleção do item.
    }

    public void setObservation(String observation) {
        this.observation = observation; // Atualiza as observações do item.
    }
}
