import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import dao.impl.UserDAOImpl;
import dao.UserDAO;
import model.User;
import service.impl.*;
import util.DBConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        try {
            Connection conn = DBConnection.getConnection();
            UserDAO userDAO = new UserDAOImpl(conn);
            AuthServiceImpl auth = new AuthServiceImpl(userDAO);

            System.out.println("Welcome to bank.");
            System.out.println("Enter name: ");
            String nameInput = scanner.next();
            System.out.println("Enter email: ");
            String emailInput = scanner.next();
            System.out.println("Enter mobile number: ");
            String mobileInput = scanner.next();
            System.out.println("Enter PIN: ");
            int pinInput = scanner.nextInt();

            try {
                User user = auth.login(mobileInput, pinInput);
                //User user = auth.register(nameInput, emailInput, mobileInput, pinInput);
                if (user != null) {
                    System.out.println("Welcome, " + user.getName() + "!");
                }
            } catch (Exception e) {
                System.out.println("Invalid mobile number or PIN.");
            } finally {
                scanner.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // userDAO.getAllUsers().forEach(user -> {
        //     System.out.println("User: " + user.getName() + ", Email: " + user.getEmail());
        // });
        // userDAO.createUser("Eric", "eric@example.com", "1111111111", 1234);
        //     userDAO.getAllUsers().forEach(user -> {
        //         System.out.println("User: " + user.getName() + ", Email: " + user.getEmail());
        //     });

        

        

    }
}
