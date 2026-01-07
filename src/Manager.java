public class Manager extends Staff {

    private int teamSize;

    public Manager(int id, String name, double salary, int teamSize) {
        super(id, name, salary);
        this.teamSize = teamSize;
    }

    @Override
    public String work() {
        return "Manager manages " + teamSize + " employees";
    }
}
