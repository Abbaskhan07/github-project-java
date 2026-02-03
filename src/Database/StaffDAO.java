package Database;

import model.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDAO {

    // INSERT
    public void insertStaff(Staff staff) {
        String sql = "INSERT INTO staff (name, salary, experience_years) VALUES (?, ?, ?)";

        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, staff.getName());
            stmt.setDouble(2, staff.getSalary());
            stmt.setInt(3, staff.getExperienceYears());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Сотрудник успешно добавлен в базу!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Ошибка при вставке!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    // SELECT (вывод всех сотрудников)
    public void getAllStaff() {
        String sql = "SELECT * FROM staff";
        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- СПИСОК СОТРУДНИКОВ ИЗ БАЗЫ ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("staff_id"));
                System.out.println("Имя: " + rs.getString("name"));
                System.out.println("Зарплата: " + rs.getDouble("salary"));
                System.out.println("Опыт: " + rs.getInt("experience_years") + " лет");
                System.out.println("--------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("❌ Ошибка при чтении!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
}