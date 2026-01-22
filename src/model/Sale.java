package model;

public class Sale {
    private int saleId;
    private String date;
    private double amount;

    public Sale(int saleId, String date, double amount) {
        setSaleId(saleId);
        setDate(date);
        setAmount(amount);
    }

    public void setSaleId(int saleId) {
        if (saleId <= 0) {
            throw new IllegalArgumentException("Sale ID must be positive");
        }
        this.saleId = saleId;
    }

    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty");
        }
        this.date = date;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public void printReceipt() {
        System.out.println("Чек #" + saleId + " от " + date + " на сумму: " + amount + " KZT");
    }


    public void validateSale() {
        if (amount == 0) {
            throw new IllegalStateException("Sale amount cannot be zero");
        }
        System.out.println("Sale validated successfully.");
    }
}