import Database.StaffDAO;      // ← важно: Database с большой буквы!
import model.Manager;

public class TestStaffDAO {
    public static void main(String[] args) {

        // 5 параметров (последний — teamSize)
        Manager manager = new Manager(1, "Айбек", 550000, 7, 12);

        StaffDAO dao = new StaffDAO();
        dao.insertStaff(manager);      // добавляем в базу
        dao.getAllStaff();             // выводим всех сотрудников
    }
}