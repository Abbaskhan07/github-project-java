package com.company;
public class Sale {
    private Product product;
    private int quantity;

    public Sale(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Количество должно быть больше 0");
        this.product = product;
        this.quantity = quantity;
    }
    public String getSummary() {
        return "Продажа: " + product.getDetails() + " | Кол-во: " + quantity;
    }
}