public class Customer {

    private String name;
    private int id;
    private double balance;
    private int loyaltyPoints;

    public Customer(String name, int id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.loyaltyPoints = 0;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean pay(double amount) {
        if (amount <= balance) {
            balance -= amount;
            loyaltyPoints += 10;
            return true;
        }
        return false;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}

