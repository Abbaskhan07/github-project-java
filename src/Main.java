public class Main {
    public static void main(String[] args) {

        Product apple = new Product("Apple", 0.5, 20, "Fruits");
        Customer customer = new Customer("Ali", 1, 20.0);

        Sale sale = new Sale(apple, customer, 5);

        if (sale.processSale()) {
            System.out.println("Sale successful!");
        } else {
            System.out.println("Sale failed!");
        }

        System.out.println("Remaining quantity: " + apple.getQuantity());
        System.out.println("Customer balance: " + customer.getBalance());
        System.out.println("Loyalty points: " + customer.getLoyaltyPoints());
    }
}
