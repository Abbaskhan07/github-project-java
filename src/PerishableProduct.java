public class PerishableProduct extends Product {
    private String expiryDate;

    public PerishableProduct(int id, String name, double price, int quantity, String expiryDate) {
        super(id, name, price, quantity); // Вызов конструктора родителя
        this.expiryDate = expiryDate;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("    Срок годности: " + expiryDate);
    }
}