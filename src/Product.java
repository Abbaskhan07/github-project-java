public class Product {

    private int productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int productId, String name, double price, int stockQuantity) {
        setProductId(productId);
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
    }

    public Product() {
        this.productId = 0;
        this.name = "Unknown";
        this.price = 0.0;
        this.stockQuantity = 0;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        if (productId > 0) {
            this.productId = productId;
        } else {
            this.productId = 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown";
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 0;
        }
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        } else {
            this.stockQuantity = 0;
        }
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void restock(int amount) {
        if (amount > 0) {
            stockQuantity += amount;
        }
    }

    @Override
    public String toString() {
        return "Product{id=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stockQuantity + '}';
    }
}
