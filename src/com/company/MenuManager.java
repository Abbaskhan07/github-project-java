package com.company;
import java.util.*;

public class MenuManager implements Menu {
    private List<Product> products = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public MenuManager() {
        products.add(new PerishableProduct("Хлеб", 150.0, "2026-01-25"));
        products.add(new PerishableProduct("Молоко", 450.0, "2026-02-10"));
        staffList.add(new Cashier("Мадина"));
        staffList.add(new Admin("Аяулым"));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n--- ПАНЕЛЬ УПРАВЛЕНИЯ МАГАЗИНОМ ---");
        System.out.println("1. Продукты         | 2. Скоропортящиеся\n3. Добавить продукт | 4. Добавить скоропорт.");
        System.out.println("5. Администраторы   | 6. Кассиры\n7. VIP клиенты      | 8. Обычные клиенты");
        System.out.println("9. Нанять/Уволить   | 10. Продажи (Sale)\n0. Выход");
        System.out.print("Выбор: ");
    }

    @Override
    public void handleSelection() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1, 2 -> products.forEach(p -> System.out.println(p.getDetails()));
                case 3, 4 -> addProduct();
                case 5 -> staffList.stream().filter(s -> s instanceof Admin).forEach(Staff::work);
                case 6 -> staffList.stream().filter(s -> s instanceof Cashier).forEach(Staff::work);
                case 7 -> customers.stream().filter(c -> c instanceof VIPCustomer).forEach(c -> System.out.println(c.getInfo()));
                case 8 -> customers.forEach(c -> System.out.println(c.getInfo()));
                case 9 -> manageStaff();
                case 10 -> showSales();
                case 0 -> System.exit(0);
            }
        } catch (Exception e) { System.out.println("Ошибка: " + e.getMessage()); }
    }

    private void addProduct() {
        System.out.print("Название: "); String n = scanner.nextLine();
        System.out.print("Цена: "); double p = Double.parseDouble(scanner.nextLine());
        products.add(new PerishableProduct(n, p, "2026-12-31"));
    }

    private void manageStaff() {
        System.out.println("1. Нанять | 2. Уволить");
        int sub = Integer.parseInt(scanner.nextLine());
        if (sub == 1) {
            System.out.print("Имя: "); String name = scanner.nextLine();
            staffList.add(new Cashier(name));
        } else {
            System.out.print("Имя на увольнение: "); String name = scanner.nextLine();
            staffList.removeIf(s -> s.getName().equalsIgnoreCase(name));
        }
    }

    private void showSales() {
        if (sales.isEmpty()) System.out.println("Продаж пока нет.");
        else sales.forEach(s -> System.out.println(s.getSummary()));
    }
}