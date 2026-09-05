package com.ecommerce.repository;

import com.ecommerce.config.DatabaseConfig;
import com.ecommerce.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("stock")));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
        }
        return products;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product: " + e.getMessage());
        }
        return null;
    }

    public boolean updateStock(int id, int newStock) {
        String sql = "UPDATE products SET stock = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newStock);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating stock: " + e.getMessage());
            return false;
        }
    }
}