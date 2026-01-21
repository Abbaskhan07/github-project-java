package com.company;
public class Cashier extends Staff {
    public Cashier(String name) { super(name); }
    @Override
    public void work() { System.out.println("Кассир " + name + " пробивает продукты."); }
}