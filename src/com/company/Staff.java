package com.company;
public abstract class Staff {
    protected String name;
    public Staff(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Имя сотрудника пустое");
        this.name = name;
    }
    public String getName() { return name; }
    public abstract void work();
}