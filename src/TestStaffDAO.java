import Database.StaffDAO;
import model.Manager;

public class TestStaffDAO {
    public static void main(String[] args) {


        Manager manager = new Manager(1, "Айбек", 550000, 7, 12);

        StaffDAO dao = new StaffDAO();
        dao.insertStaff(manager);
        dao.getAllStaff();
    }
}