package model;

public class PerishableProduct extends Product implements Perishable {
    private String expiryDate;

    public PerishableProduct(int id, String name, double price, int quantity, String expiryDate) {
        super(id, name, price, quantity);
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
                                        // ← ВАЖНО: вызываем родительский метод
        System.out.println("    Срок годности: " + expiryDate);
    }

    @Override
    public boolean isAvailable() {
        return getQuantity() > 0 && !expiryDate.equals("expired");
    }

    public void checkExpiry() {
        if (expiryDate.equals("expired")) {
            throw new IllegalStateException("Product is expired");
        }
        System.out.println("Product is still valid until " + expiryDate);
    }
}