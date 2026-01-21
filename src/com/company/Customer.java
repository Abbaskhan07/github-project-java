package com.company;
public class Customer {
    protected String name;
    public Customer(String name) { this.name = name; }
    public String getInfo() { return "Клиент: " + name; }
}