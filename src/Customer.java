public class Customer {

    private String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String toString() {
        return "Customer: " + customerName;
    }
}
