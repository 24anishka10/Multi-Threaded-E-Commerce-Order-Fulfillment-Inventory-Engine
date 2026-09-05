package com.ecommerce.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:h2:mem:ecommerce_db;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS products (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "price DOUBLE, " +
                "stock INT)";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            // Insert seed records
            stmt.execute("MERGE INTO products KEY(id) VALUES (101, 'Gaming Laptop', 100000.00, 5)");
            stmt.execute("MERGE INTO products KEY(id) VALUES (102, 'Wireless Mouse', 429.00, 50)");
            stmt.execute("MERGE INTO products KEY(id) VALUES (103, 'Mechanical Kbd', 1250.00, 3)");
        } catch (SQLException e) {
            System.err.println("Database Initialisation Failed: " + e.getMessage());
        }
    }
}