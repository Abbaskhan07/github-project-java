package model;

public abstract class Product implements Available {
    private int id;
    private String name;
    private double price;
    private int quantity;


    public Product(int id, String name, double price, int quantity) {
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        this.price = price;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        this.quantity = quantity;
    }

    public abstract void display(); // Абстрактный метод для полиморфизма
}

// Интерфейс для Week 6
interface Available {
    boolean isAvailable();
}

// Второй интерфейс (для customers или продуктов, если нужно)
interface Discountable {
    double getDiscount();
}