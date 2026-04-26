package dao.impl;

import dao.TransactionDAO;
import model.Transaction;
import util.DBConnection;

import javax.xml.namespace.QName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Transaction> viewUserTransactions(int id) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE fk_user_id = ?";
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Transaction transaction = new Transaction(
                    rs.getInt("id"),
                    rs.getDouble("amount"),
                    rs.getString("type"),
                    rs.getInt("fk_user_id"),
                    rs.getDate("date"),
                    rs.getString("to_number"),
                    rs.getString("from_number")
            );
                transactions.add(transaction);

            }
            return  transactions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
