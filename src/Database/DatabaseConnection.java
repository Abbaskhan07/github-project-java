package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static final String URL = "jdbc:postgresql://localhost:5432/grocery_db"; // название твоей БД
    private static final String USER = "postgres";
    private static final String PASSWORD = "Afrika_90";   // ← обязательно поменяй!

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Подключение к базе успешно!");
        } catch (SQLException e) {
            System.out.println(" Ошибка подключения!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение закрыто.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}