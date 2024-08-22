package com.ecommerce.dao;

import com.ecommerce.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=EcommerceDB;encrypt=true;trustServerCertificate=true;", "sa", "123456")) {
            String sql = "SELECT * FROM Products";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("imageUrl"),
                        resultSet.getDouble("price")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}
