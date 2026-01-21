package com.company;
public class Main {
    public static void main(String[] args) {
        MenuManager mm = new MenuManager();
        while (true) { mm.displayMenu(); mm.handleSelection(); }
    }
}