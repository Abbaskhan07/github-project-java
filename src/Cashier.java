public class Cashier extends Staff {

    private int registerNumber;

    public Cashier(int id, String name, double salary, int registerNumber) {
        super(id, name, salary);
        this.registerNumber = registerNumber;
    }

    @Override
    public String work() {
        return "Cashier works at register " + registerNumber;
    }
}
