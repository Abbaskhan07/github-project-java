package Database;

import model.Manager;
import model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {


    public boolean insertStaff(Staff staff) {
        String sql = "INSERT INTO staff (name, salary, experience_years, staff_type, team_size) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, staff.getName());
            ps.setDouble(2, staff.getSalary());
            ps.setInt(3, staff.getExperienceYears());
            ps.setString(4, staff.getRole());                     // 'Manager'

            if (staff instanceof Manager m) {
                ps.setInt(5, m.getTeamSize());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✓ Staff inserted" : "✗ Insert failed");
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }


    public List<Staff> getAllStaff() {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM staff ORDER BY salary DESC";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Staff s = extractStaffFromResultSet(rs);
                if (s != null) list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return list;
    }


    public Staff getStaffById(int id) {
        String sql = "SELECT * FROM staff WHERE staff_id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return extractStaffFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }


    public boolean updateStaff(Staff staff) {
        String sql = "UPDATE staff SET name = ?, salary = ?, experience_years = ?, team_size = ? " +
                "WHERE staff_id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, staff.getName());
            ps.setDouble(2, staff.getSalary());
            ps.setInt(3, staff.getExperienceYears());

            if (staff instanceof Manager m) {
                ps.setInt(4, m.getTeamSize());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.setInt(5, staff.getStaffId());

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✓ Staff updated" : "✗ Update failed");
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }


    public boolean deleteStaff(int id) {
        String sql = "DELETE FROM staff WHERE staff_id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✓ Staff deleted" : "✗ No staff with this ID");
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }


    public List<Staff> searchByName(String name) {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE name ILIKE ? ORDER BY name";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Staff s = extractStaffFromResultSet(rs);
                    if (s != null) list.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return list;
    }

    public List<Staff> searchBySalaryRange(double min, double max) {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE salary BETWEEN ? AND ? ORDER BY salary DESC";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Staff s = extractStaffFromResultSet(rs);
                    if (s != null) list.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return list;
    }

    public List<Staff> searchByMinSalary(double min) {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE salary >= ? ORDER BY salary DESC";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return list;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, min);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Staff s = extractStaffFromResultSet(rs);
                    if (s != null) list.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return list;
    }


    private Staff extractStaffFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("staff_id");
        String name = rs.getString("name");
        double salary = rs.getDouble("salary");
        int exp = rs.getInt("experience_years");
        String type = rs.getString("staff_type");
        int teamSize = rs.getInt("team_size");

        if ("MANAGER".equalsIgnoreCase(type)) {
            return new Manager(id, name, salary, exp, teamSize);
        }

        return null;
    }
}