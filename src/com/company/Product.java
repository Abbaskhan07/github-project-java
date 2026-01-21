package com.company;
public abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        setName(name); setPrice(price);
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Название пустое");
        this.name = name;
    }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Цена отрицательная");
        this.price = price;
    }
    public abstract String getDetails();
}