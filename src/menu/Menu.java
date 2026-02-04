package menu;

import java.util.ArrayList;
import model.Customer;
import model.Product;
import model.Sale;

public interface Menu {
    void displayOptions();
    void handleInput(int choice, ArrayList<Product> inventory,
                     ArrayList<Customer> customers, ArrayList<Sale> sales);
}