package dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.UserDAO;
import model.User;

public class UserDAOImpl implements UserDAO {
    private Connection conn;
    public UserDAOImpl(Connection conn){
        this.conn = conn;
    }
    @Override
    public void createUser(String name, String email, String number, String pin) {

        double BALANCE = 0.0;

        String sql = "INSERT INTO users (username, email, number, pin) VALUES (?, ?, ?, ?)";
        String sql2 = "INSERT INTO account (balance, fk_user_id) VALUES (?, ?)";

        try {
            // Start transaction
            conn.setAutoCommit(false);

            //  Get generated ID
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, number);
            stmt.setString(4, pin);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            int userID = -1;
            if (rs.next()) {
                userID = rs.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            //Insert into account using the generated userID
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setDouble(1, BALANCE);
            stmt2.setInt(2, userID);
            stmt2.executeUpdate();

            //Commit both inserts together
            conn.commit();

            System.out.println("User and account created successfully.");

        } catch (SQLException e) {
            try {
                conn.rollback(); // rollback if anything fails
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("number"),
                    rs.getString("pin")
                ));
            }
            System.out.println("Users retrieved successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User findUser(String number, String pin) {
        String sql = "SELECT * FROM users WHERE number = ? AND pin = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, number);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("number"),
                        rs.getString("pin"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Invalid mobile number or PIN.");
        }
        return null;
    }

    @Override
public void updatePin(User user, String newPin) {
    String sql = "UPDATE users SET pin = ? WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newPin);
        stmt.setInt(2, user.getId());
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            user.setPin(newPin);
            System.out.println("PIN changed successfully for user: " + user.getName());
        } else {
            System.out.println("PIN change failed: User not found or no changes made.");
        }
    } catch (SQLException e) {
        System.out.println("Error changing PIN: " + e.getMessage());
        e.printStackTrace();
    }
}
@Override
    public User findUserByNumber(String number){
    String sql = "SELECT * FROM users WHERE number = ?";

    try {
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1, number);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("number"),
                    rs.getString("pin")
            );
        }else {
            System.out.println("No user");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return null;
}

}
