public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        setPrice(price); // Валидация через сеттер
        setQuantity(quantity);
    }

    public void setPrice(double price) {
        this.price = (price >= 0) ? price : 0;
    }

    public void setQuantity(int quantity) {
        this.quantity = (quantity >= 0) ? quantity : 0;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    // Метод для полиморфизма
    public void display() {
        System.out.printf("[Товар] ID: %d | %-12s | Цена: %.2f | Кол-во: %d%n", id, name, price, quantity);
    }
}