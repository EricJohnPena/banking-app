package dao.impl;

import dao.AccountDAO;
import model.Accounts;

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

            return new Accounts(
                        rs.getInt("id"),
                        rs.getDouble("balance"),
                        rs.getInt("fk_user_id")
                );

        } catch (SQLException e) {
            System.out.println("Query failed.");
        }
        return null;
    }
}
