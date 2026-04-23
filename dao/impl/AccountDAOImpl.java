package dao.impl;

import dao.AccountDAO;
import model.Accounts;
import util.DBConnection;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO {
    private Connection conn;
    public AccountDAOImpl(Connection conn){
        this.conn = conn;
    }
    @Override
    public Accounts findAccount(int userID) {
        String sql = "SELECT * FROM account WHERE fk_user_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Accounts(
                        rs.getInt("id"),
                        rs.getDouble("balance"),
                        rs.getInt("fk_user_id")
                );
            }
            return null;

        } catch (SQLException e) {
            System.out.println("Query failed.");
        }
        return null;
    }

    @Override
    public Accounts updateCash(int userID, double amount) {
        String sql = "UPDATE account SET balance = ? WHERE fk_user_id = ? ";
        try {
            conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1,amount);
            stmt.setInt(2,userID);
            int rowsAffected = stmt.executeUpdate();  // Use executeUpdate for INSERT/UPDATE/DELETE
            if (rowsAffected > 0) {
                // Return the updated account
                return findAccount(userID);
            } else {
                System.out.println("Failed: Account not found or no update made.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error during update: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Accounts cashOut(int userID, double amount) {
        return null;
    }
}
