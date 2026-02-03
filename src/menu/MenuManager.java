package menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Database.StaffDAO;
import model.Customer;
import model.Sale;
import model.Staff;
import model.Manager; // Assume your child class

public class MenuManager {

    private StaffDAO staffDAO = new StaffDAO();

    private static ArrayList<Customer> customers = new ArrayList<>(); // Keep for customers, add DAO if needed

    private static ArrayList<Sale> sales = new ArrayList<>(); // Keep, add DAO if needed

    private Scanner scanner = new Scanner(System.in);

    public static void runSystem() {
        MenuManager menuManager = new MenuManager();

        // Initialization moved to DB if needed

        while (true) {
            menuManager.displayOptions();
            int choice = menuManager.scanner.nextInt();
            menuManager.scanner.nextLine();
            try {
                menuManager.handleInput(choice);
                if (choice == 0) break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Программа завершена.");
        menuManager.scanner.close();
    }

    public void displayOptions() {
        System.out.println("\n--- Grocery Management System Menu ---");
        System.out.println("1. View all products (placeholder, add DAO)");
        System.out.println("2. View all customers");
        System.out.println("3. Add regular product (placeholder)");
        System.out.println("4. Add perishable product (placeholder)");
        System.out.println("5. Add VIP customer");
        System.out.println("6. View all staff");
        System.out.println("7. Add staff (manager)");
        System.out.println("8. Update staff");
        System.out.println("9. Delete staff");
        System.out.println("10. Search staff by name");
        System.out.println("11. Search staff by salary range");
        System.out.println("12. Search staff by min salary");
        System.out.println("13. View sales");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void handleInput(int choice) throws IllegalArgumentException {
        if (choice < 0 || choice > 13) {
            throw new IllegalArgumentException("Invalid menu choice: " + choice);
        }
        switch (choice) {
            case 1:
                // View products - add ProductDAO if needed
                System.out.println("View products placeholder");
                break;
            case 2:
                viewAllCustomers();
                break;
            case 3:
                // Add regular product - add ProductDAO
                System.out.println("Add regular product placeholder");
                break;
            case 4:
                // Add perishable - add ProductDAO
                System.out.println("Add perishable product placeholder");
                break;
            case 5:
                addVIPCustomer();
                break;
            case 6:
                viewAllStaff();
                break;
            case 7:
                addStaff();
                break;
            case 8:
                updateStaff();
                break;
            case 9:
                deleteStaff();
                break;
            case 10:
                searchStaffByName();
                break;
            case 11:
                searchStaffBySalaryRange();
                break;
            case 12:
                searchStaffByMinSalary();
                break;
            case 13:
                viewAllSales();
                break;
            case 0:
                System.out.println("Exiting system.");
                break;
        }
    }

    private void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }
        System.out.println("\n ЗАРЕГИСТРИРОВАННЫЕ КЛИЕНТЫ:");
        for (Customer c : customers) {
            c.showInfo();
        }
    }

    private void addVIPCustomer() {
        // Existing code
    }

    private void viewAllSales() {
        // Existing code
    }

    // New methods for staff
    private void viewAllStaff() {
        List<Staff> staff = staffDAO.getAllStaff();
        if (staff.isEmpty()) {
            System.out.println("No staff found.");
            return;
        }
        System.out.println("\n STAFF:");
        for (Staff s : staff) {
            s.displayInfo();
        }
    }

    private void addStaff() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Experience years: ");
        int exp = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Team size: ");
        int teamSize = scanner.nextInt();

        Manager manager = new Manager(0, name, salary, exp, teamSize); // ID from DB
        staffDAO.insertStaff(manager);
        System.out.println("Staff added!");
    }

    private void updateStaff() {
        System.out.print("Enter Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Staff staff = staffDAO.getStaffById(id);
        if (staff == null) {
            System.out.println("Staff not found.");
            return;
        }

        System.out.println("Current Info:");
        staff.displayInfo();

        System.out.print("New Name [" + staff.getName() + "]: ");
        String newName = scanner.nextLine();
        if (newName.trim().isEmpty()) newName = staff.getName();

        System.out.print("New Salary [" + staff.getSalary() + "]: ");
        String salaryInput = scanner.nextLine();
        double newSalary = salaryInput.trim().isEmpty() ? staff.getSalary() : Double.parseDouble(salaryInput);

        System.out.print("New Experience Years [" + staff.getExperienceYears() + "]: ");
        String expInput = scanner.nextLine();
        int newExp = expInput.trim().isEmpty() ? staff.getExperienceYears() : Integer.parseInt(expInput);

        staff.setName(newName);
        staff.setSalary(newSalary);
        staff.setExperienceYears(newExp);

        if (staff instanceof Manager) {
            System.out.print("New Team Size [" + ((Manager) staff).getTeamSize() + "]: ");
            String teamInput = scanner.nextLine();
            int newTeam = teamInput.trim().isEmpty() ? ((Manager) staff).getTeamSize() : Integer.parseInt(teamInput);
            ((Manager) staff).setTeamSize(newTeam);
        }

        staffDAO.updateStaff(staff);
        System.out.println("Staff updated!");
    }

    private void deleteStaff() {
        System.out.print("Enter Staff ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Staff staff = staffDAO.getStaffById(id);
        if (staff == null) {
            System.out.println("Staff not found.");
            return;
        }

        System.out.println("Staff to delete:");
        staff.displayInfo();

        System.out.print("Are you sure (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y")) {
            staffDAO.deleteStaff(id);
            System.out.println("Staff deleted!");
        } else {
            System.out.println("Cancelled.");
        }
    }

    private void searchStaffByName() {
        System.out.print("Enter name (partial ok): ");
        String name = scanner.nextLine();
        List<Staff> results = staffDAO.searchByName(name);
        System.out.println("Results:");
        for (Staff s : results) {
            s.displayInfo();
        }
    }

    private void searchStaffBySalaryRange() {
        System.out.print("Enter min salary: ");
        double min = scanner.nextDouble();
        System.out.print("Enter max salary: ");
        double max = scanner.nextDouble();
        scanner.nextLine();
        List<Staff> results = staffDAO.searchBySalaryRange(min, max);
        System.out.println("Results (DESC by salary):");
        for (Staff s : results) {
            s.displayInfo();
        }
    }

    private void searchStaffByMinSalary() {
        System.out.print("Enter min salary: ");
        double min = scanner.nextDouble();
        scanner.nextLine();
        List<Staff> results = staffDAO.searchByMinSalary(min);
        System.out.println("Results (DESC by salary):");
        for (Staff s : results) {
            s.displayInfo();
        }
    }
}