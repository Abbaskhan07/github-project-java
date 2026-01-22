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
        // Example business logic: check if not expired (simplified)
        return quantity > 0 && !expiryDate.equals("expired");  // Can be enhanced with date parsing
    }

    // Additional method for validation/business logic
    public void checkExpiry() {
        if (expiryDate.equals("expired")) {  // Placeholder for real date check
            throw new IllegalStateException("Product is expired");
        }
        System.out.println("Product is still valid until " + expiryDate);
    }
}