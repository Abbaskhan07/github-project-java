import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> inventory = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Инициализация тестовых данных (Требование Week 3)
        inventory.add(new Product(1, "Хлеб", 150, 20));
        inventory.add(new PerishableProduct(2, "Молоко", 480, 10, "2024-12-30"));
        customers.add(new Customer(101, "Арман", 5000));
        customers.add(new VIPCustomer(102, "Алия", 25000, 0.15));

        while (true) {
            System.out.println("\n---  СИСТЕМА УПРАВЛЕНИЯ МАГАЗИНОМ ---");
            System.out.println("1. Показать все товары ");
            System.out.println("2. Показать всех клиентов");
            System.out.println("3. Добавить обычный товар");
            System.out.println("4. Добавить скоропортящийся товар");
            System.out.println("5. Добавить VIP-клиента");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1 -> {
                    System.out.println("\n ТОВАРЫ В НАЛИЧИИ:");
                    for (Product p : inventory) p.display();
                }
                case 2 -> {
                    System.out.println("\n БАЗА КЛИЕНТОВ:");
                    for (Customer c : customers) c.showInfo();
                }
                case 3 -> addProduct(false);
                case 4 -> addProduct(true);
                case 5 -> {
                    System.out.print("Имя: "); String name = scanner.nextLine();
                    System.out.print("Скидка (например, 0,1): "); double d = scanner.nextDouble();
                    customers.add(new VIPCustomer(customers.size()+1, name, 0, d));
                    System.out.println(" VIP добавлен!");
                }
                default -> System.out.println(" Ошибка выбора!");
            }
        }
        System.out.println("Программа завершена.");
    }

    private static void addProduct(boolean perishable) {
        System.out.print("Название: "); String name = scanner.nextLine();
        System.out.print("Цена: "); double price = scanner.nextDouble();
        System.out.print("Количество: "); int qty = scanner.nextInt();
        scanner.nextLine();
        if (perishable) {
            System.out.print("Дата (ГГГГ-ММ-ДД): "); String date = scanner.nextLine();
            inventory.add(new PerishableProduct(inventory.size()+1, name, price, qty, date));
        } else {
            inventory.add(new Product(inventory.size()+1, name, price, qty));
        }
        System.out.println(" Товар успешно добавлен!");
    }
}