package model;

public class VIPCustomer extends Customer {
    private double discountRate;

    public VIPCustomer(int id, String name, double totalSpent, double discountRate) {
        super(id, name, totalSpent);
        setDiscountRate(discountRate);
    }

    public void setDiscountRate(double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            throw new IllegalArgumentException("Discount rate must be between 0 and 1");
        }
        this.discountRate = discountRate;
    }

    @Override
    public void showInfo() {
        System.out.printf("[VIP] ID: %s | Имя: %-10s | Скидка: %.0f%% | Итого потрачено: %.2f%n",
                "V-" + getName().hashCode()%100, getName(), discountRate * 100, totalSpent);
    }

    @Override
    public double getDiscount() {
        return discountRate;
    }


    public void applyDiscount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        double discounted = amount * (1 - discountRate);
        System.out.printf("Discounted amount: %.2f KZT%n", discounted);
    }
}