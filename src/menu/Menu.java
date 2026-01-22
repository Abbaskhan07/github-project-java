package menu;

import java.util.ArrayList;
import model.*;

public interface Menu {
    void displayOptions();

    void handleInput(int choice, ArrayList<Product> inventory, ArrayList<Customer> customers, ArrayList<Sale> sales) throws IllegalArgumentException;
}