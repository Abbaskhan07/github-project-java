public class Main {
    public static void main(String[] args) {

        Sale sale = new Sale("My Grocery Store");
        Customer customer = new Customer("Alex");

        Product p1 = new Product("Bread", 150);
        Product p2 = new Product("Milk", 300);
        Product p3 = new Product();

        p3.setPrice(500);

        System.out.println(sale.getSaleName());
        System.out.println(customer);
        sale.showProduct(p1);
        sale.showProduct(p2);
        sale.showProduct(p3);
    }
}
