package com.company;
public class VIPCustomer extends Customer {
    public VIPCustomer(String name) { super(name); }
    @Override
    public String getInfo() { return "VIP Клиент: " + name + " (Скидка 10%)"; }
}