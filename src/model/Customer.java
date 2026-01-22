package model;

public abstract class Customer {
    protected int id;
    protected String name;
    protected double totalSpent;

    public Customer(int id, String name, double totalSpent) {
        setId(id);
        setName(name);
        setTotalSpent(totalSpent);
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setTotalSpent(double totalSpent) {
        if (totalSpent < 0) {
            throw new IllegalArgumentException("Total spent cannot be negative");
        }
        this.totalSpent = totalSpent;
    }

    public String getName() { return name; }

    public abstract double getDiscount();

    public void showInfo() {
        System.out.printf("[Клиент] ID: %d | Имя: %-10s | Покупки: %.2f KZT%n", id, name, totalSpent);
    }
}