package model;

public class Cashier extends Staff {
    private String shift;

    public Cashier(int staffId, String name, double salary, int experienceYears, String shift) {
        super(staffId, name, salary, experienceYears);
        setShift(shift);
    }

    public void setShift(String shift) {
        if (shift == null || shift.trim().isEmpty()) {
            throw new IllegalArgumentException("Shift cannot be empty");
        }
        this.shift = shift;
    }

    @Override
    public void work() {
        System.out.println("Cashier " + name + " is handling payments during " + shift + " shift.");
    }

    @Override
    public String getRole() {
        return "Cashier";
    }

    public void processSale(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Sale amount cannot be negative");
        }
        System.out.println("Processed sale of " + amount + " KZT.");
    }
}