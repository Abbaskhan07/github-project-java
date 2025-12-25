public class Sale {

    private Product product;
    private Customer customer;
    private int quantity;
    private double totalPrice;

    public Sale(Product product, Customer customer, int quantity) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    // ЛОГИКА №1
    public boolean processSale() {
        if (product.sell(quantity) && customer.pay(totalPrice)) {
            return true;
        }
        return false;
    }

    // ЛОГИКА №2
    public double getTotalPrice() {
        return totalPrice;
    }
}
