package Database;

import Database.DatabaseConnection; // Assume this package
import model.Staff;
import model.Manager; // Assume Manager is your child class; replace with Chef/Waiter if needed
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    // Existing methods from Week 7 (assume they exist)
    public boolean insertStaff(Staff staff) {
        String sql = "INSERT INTO staff (name, salary, staff_type, experience_years) VALUES (?, ?, 'MANAGER', ?)"; // Adjust for Manager

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, staff.getName());
            statement.setDouble(2, staff.getSalary());
            statement.setInt(3, staff.getExperienceYears());
            // Add more for specialization or other fields if Manager has them

            int rowsInserted = statement.executeUpdate();
            statement.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return staffList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                int exp = resultSet.getInt("experience_years");
                // Add more fields

                Manager manager = new Manager(id, name, salary, exp, 0); // Adjust
                staffList.add(manager);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return staffList;
    }

    public Staff getStaffById(int id) {
        String sql = "SELECT * FROM staff WHERE staff_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                int exp = resultSet.getInt("experience_years");
                // Add more

                Manager manager = new Manager(id, name, salary, exp, 0); // Adjust
                return manager;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return null;
    }

    // New for Week 8: UPDATE
    public boolean updateStaff(Staff staff) {
        String sql = "UPDATE staff SET name = ?, salary = ?, experience_years = ? WHERE staff_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, staff.getName());
            statement.setDouble(2, staff.getSalary());
            statement.setInt(3, staff.getExperienceYears());
            statement.setInt(4, staff.getStaffId());
            // Add more fields if needed

            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // New for Week 8: DELETE
    public boolean deleteStaff(int staffId) {
        String sql = "DELETE FROM staff WHERE staff_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // New for Week 8: Search by Name
    public List<Staff> searchByName(String name) {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE name ILIKE ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return staffList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String sName = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                int exp = resultSet.getInt("experience_years");
                // Add more

                Manager manager = new Manager(id, sName, salary, exp, 0); // Adjust
                staffList.add(manager);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return staffList;
    }

    // New for Week 8: Search by Salary Range
    public List<Staff> searchBySalaryRange(double min, double max) {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE salary BETWEEN ? AND ? ORDER BY salary DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return staffList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                int exp = resultSet.getInt("experience_years");
                // Add more

                Manager manager = new Manager(id, name, salary, exp, 0); // Adjust
                staffList.add(manager);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return staffList;
    }

    // New for Week 8: Search by Min Salary
    public List<Staff> searchByMinSalary(double minSalary) {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE salary >= ? ORDER BY salary DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return staffList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minSalary);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                int exp = resultSet.getInt("experience_years");
                // Add more

                Manager manager = new Manager(id, name, salary, exp, 0); // Adjust
                staffList.add(manager);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return staffList;
    }
}