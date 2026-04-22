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
    public User createUser(String name, String email, String number, String pin) {
        String sql = "INSERT into users (name, email, number, pin) VALUES (?,?,?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, number);
            stmt.setString(4, pin);
            stmt.executeUpdate();
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
                return null;
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
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("number"),
                    rs.getInt("pin")
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
            if (rs.next()){
                return new User(
                    rs.getInt("ID"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("number"),
                    rs.getString("pin"));
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

}
