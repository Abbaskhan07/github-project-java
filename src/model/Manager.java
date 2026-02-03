package model;

public class Manager extends Staff {
    private int teamSize;

    public Manager(int staffId, String name, double salary, int experienceYears, int teamSize) {
        super(staffId, name, salary, experienceYears);
        setTeamSize(teamSize);
    }

    public void setTeamSize(int teamSize) {
        if (teamSize < 0) {
            throw new IllegalArgumentException("Team size cannot be negative");
        }
        this.teamSize = teamSize;
    }

    public int getTeamSize() {  // Добавь это
        return teamSize;
    }

    @Override
    public void work() {
        System.out.println("Manager " + name + " is overseeing " + teamSize + " staff.");
    }

    @Override
    public String getRole() {
        return "Manager";
    }

    public void approveInventory(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        System.out.println("Approved " + quantity + " items.");
    }
}