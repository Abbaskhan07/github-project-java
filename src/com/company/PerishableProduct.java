package com.company;
public class PerishableProduct extends Product {
    private String expiryDate;
    public PerishableProduct(String name, double price, String expiryDate) {
        super(name, price);
        this.expiryDate = expiryDate;
    }
    @Override
    public String getDetails() {
        return "[Продукт] " + name + " | Цена: " + price + " | Срок до: " + expiryDate;
    }
}