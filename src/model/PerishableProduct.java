package model;

public class PerishableProduct extends Product {
    private String expiryDate;

    public PerishableProduct(int id, String name, double price, int quantity, String expiryDate) {
        super(id, name, price, quantity); // Вызов конструктора родителя
        setExpiryDate(expiryDate);
    }

    public void setExpiryDate(String expiryDate) {
        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Expiry date cannot be empty");
        }
        this.expiryDate = expiryDate;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("    Срок годности: " + expiryDate);
    }

    @Override
    public boolean isAvailable() {

        return quantity > 0 && !expiryDate.equals("expired");
    }


    public void checkExpiry() {
        if (expiryDate.equals("expired")) {
            throw new IllegalStateException("Product is expired");
        }
        System.out.println("Product is still valid until " + expiryDate);
    }
}