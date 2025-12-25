public class Product {

    private String name;
    private int price;

    public Product() {
        this.name = "Unknown";
        this.price = 0;
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public String toString() {
        return name + " - " + price + " tg";
    }
}
