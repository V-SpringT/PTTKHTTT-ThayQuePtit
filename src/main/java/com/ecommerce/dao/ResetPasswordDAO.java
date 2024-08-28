package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ecommerce.model.ResetPasswordToken;

public class ResetPasswordDAO {
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=EcommerceDB;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public void saveToken(ResetPasswordToken token) {
        String query = "INSERT INTO reset_password_tokens (token, email, expiry_date) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, token.getToken());
            ps.setString(2, token.getEmail());
            ps.setDate(3, new java.sql.Date(token.getExpiryDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResetPasswordToken findByToken(String token) {
        String query = "SELECT * FROM reset_password_tokens WHERE token = ?";
        ResetPasswordToken resetToken = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resetToken = new ResetPasswordToken();
                resetToken.setToken(rs.getString("token"));
                resetToken.setEmail(rs.getString("email"));
                resetToken.setExpiryDate(rs.getDate("expiry_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resetToken;
    }

    public void deleteToken(String token) {
        String query = "DELETE FROM reset_password_tokens WHERE token = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, token);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
