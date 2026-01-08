public class VIPCustomer extends Customer {
    private double discountRate;

    public VIPCustomer(int id, String name, double totalSpent, double discountRate) {
        super(id, name, totalSpent);
        this.discountRate = discountRate;
    }

    @Override
    public void showInfo() {
        System.out.printf("[VIP] ID: %s | Имя: %-10s | Скидка: %.0f%% | Итого потрачено: %.2f%n",
                "V-" + getName().hashCode()%100, getName(), discountRate * 100, totalSpent);
    }
}