package dao.impl;

import dao.TransactionDAO;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAOImpl implements TransactionDAO {

    public void insertTransaction(double amount, String type, String toNumber, String fromNumber, int userId){
        String sql = "INSERT INTO transactions (amount, type, to_number, from_number, fk_user_id) VALUES (?, ?, ?, ?, ?)";
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setString(2, type);
            stmt.setString(3, toNumber);
            stmt.setString(4, fromNumber);
            stmt.setInt(5, userId);

            stmt.executeUpdate();
        }catch (SQLException ex){

        }

    }


}
