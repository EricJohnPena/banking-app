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
    public Accounts updateCash(double amount, String type, int userID, int toID, int fromID) {

        String updateSql = "UPDATE account SET balance = ? WHERE fk_user_id = ?";
        String insertSql = "INSERT INTO transactions (amount, type, to_id, from_id, fk_user_id) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Update balance (ADD, not overwrite)
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setDouble(1, amount);
            updateStmt.setInt(2, userID);

            int rows = updateStmt.executeUpdate();
            if (rows == 0) {
                conn.rollback();
                throw new RuntimeException("Account not found");
            }

            // 2. Insert transaction
            PreparedStatement txnStmt = conn.prepareStatement(insertSql);
            txnStmt.setDouble(1, amount);
            txnStmt.setString(2, type);
            txnStmt.setInt(3, toID);
            txnStmt.setInt(4, fromID);
            txnStmt.setInt(5, userID);
            txnStmt.executeUpdate();

            conn.commit();

            return findAccount(userID);

        } catch (SQLException e) {
            try {
                conn.rollback(); // ❌ rollback on error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Accounts cashOut(int userID, double amount) {
        return null;
    }
}
