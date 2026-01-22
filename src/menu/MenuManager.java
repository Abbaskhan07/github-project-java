package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {
    private static ArrayList<Product> inventory = new ArrayList<>();

    private static ArrayList<Customer> customers = new ArrayList<>();

    private static ArrayList<Sale> sales = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public static void runSystem() {

        Scanner scanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager();

        try {
            inventory.add(new Product(1, "Хлеб", 150, 20) {
                @Override
                public boolean isAvailable() { return quantity > 0; }
            });
            inventory.add(new PerishableProduct(2, "Молоко", 480, 10, "2024-12-30"));
            customers.add(new Customer(101, "Арман", 5000) {
                @Override
                public double getDiscount() { return 0; }
            });
            customers.add(new VIPCustomer(102, "Алия", 25000, 0.15));
            sales.add(new Sale(1, "2024-01-22", 630));
        } catch (IllegalArgumentException e) {
            System.out.println("Error initializing data: " + e.getMessage());
        }
        while (true) {
            menuManager.displayOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                menuManager.handleInput(choice, inventory, customers, sales);
                if (choice == 0) break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Программа завершена.");
        scanner.close();
    }

    @Override
    public void displayOptions() {
        System.out.println("\n--- Grocery Management System Menu ---");
        System.out.println("1. View all products");
        System.out.println("2. View all customers");
        System.out.println("3. Add regular product");
        System.out.println("4. Add perishable product");
        System.out.println("5. Add VIP customer");
        System.out.println("6. Manage staff");
        System.out.println("7. View sales");
        System.out.println("0. Exit ");
        System.out.print(" Enter your choice: ");
    }

    @Override
    public void handleInput(int choice, ArrayList<Product> inventory, ArrayList<Customer> customers, ArrayList<Sale> sales) throws IllegalArgumentException {
        if (choice < 0 || choice > 7) {
            throw new IllegalArgumentException("Invalid menu choice: " + choice);
        }
        switch (choice) {
            case 1:
                viewAllProducts(inventory);
                break;
            case 2:
                viewAllCustomers(customers);
                break;
            case 3:
                addProduct(false, inventory);
                break;
            case 4:
                addProduct(true, inventory);
                break;
            case 5:
                addVIPCustomer(customers);
                break;
            case 6:
                System.out.println("Managing staff... (add logic here)");
                break;
            case 7:
                viewAllSales(sales);
                break;
            case 0:
                System.out.println("Exiting system.");
                break;
        }
    }


    private void viewAllProducts(ArrayList<Product> inventory) {
        if (inventory.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("\n ТОВАРЫ В НАЛИЧИИ:");
        for (Product p : inventory) {
            p.display();
            if (!p.isAvailable()) {
                System.out.println("    (Not available)");
            }
        }
    }


    private void viewAllCustomers(ArrayList<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }
        System.out.println("\n БАЗА КЛИЕНТОВ:");
        for (Customer c : customers) {
            c.showInfo();
            System.out.println("    Discount: " + (c.getDiscount() * 100) + "%");
        }
    }


    private void viewAllSales(ArrayList<Sale> sales) {
        if (sales.isEmpty()) {
            System.out.println("No sales recorded.");
            return;
        }
        System.out.println("\n ПРОДАЖИ:");
        for (Sale s : sales) {
            s.printReceipt();
            try {
                s.validateSale();
            } catch (IllegalStateException e) {
                System.out.println("    (Invalid sale: " + e.getMessage() + ")");
            }
        }
    }

    private void addProduct(boolean perishable, ArrayList<Product> inventory) {
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Цена: ");
        double price = scanner.nextDouble();
        System.out.print("Количество: ");
        int qty = scanner.nextInt();
        scanner.nextLine();
        if (perishable) {
            System.out.print("Дата (ГГГГ-ММ-ДД): "); String date = scanner.nextLine();
            inventory.add(new PerishableProduct(inventory.size() + 1, name, price, qty, date));
        } else {
            inventory.add(new Product(inventory.size() + 1, name, price, qty) {
                @Override
                public boolean isAvailable() { return qty > 0; }
            });
        }
        System.out.println(" Товар успешно добавлен!");
    }

    private void addVIPCustomer(ArrayList<Customer> customers) {
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        System.out.print("Скидка (например, 0,1): ");
        double d = scanner.nextDouble();
        customers.add(new VIPCustomer(customers.size() + 1, name, 0, d));
        System.out.println(" VIP добавлен!");
    }
}