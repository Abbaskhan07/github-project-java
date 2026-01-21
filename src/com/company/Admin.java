package com.company;
public class Admin extends Staff {
    public Admin(String name) { super(name); }
    @Override
    public void work() { System.out.println("Администратор " + name + " управляет складом."); }
}