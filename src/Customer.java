public class Customer {
    private int id;
    private String name;
    protected double totalSpent;

    public Customer(int id, String name, double totalSpent) {
        this.id = id;
        this.name = name;
        this.totalSpent = (totalSpent >= 0) ? totalSpent : 0;
    }

    public String getName() { return name; }

    public void showInfo() {
        System.out.printf("[Клиент] ID: %d | Имя: %-10s | Покупки: %.2f KZT%n", id, name, totalSpent);
    }
}