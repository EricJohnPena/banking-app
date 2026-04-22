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
        int choice;
        int userChoice;
        
        try {
            Connection conn = DBConnection.getConnection();
            UserDAO userDAO = new UserDAOImpl(conn);
            UserAuthenticationImpl auth = new UserAuthenticationImpl(userDAO);

            do {
            showMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- LOGIN ---");
                    System.out.print("Enter mobile number: ");
                    String mobileInput = scanner.next();
                    System.out.print("Enter PIN: ");
                    String pinInput = scanner.next();
                    try {
                        User user = auth.login(mobileInput, pinInput);
                        if (user != null) {
                             boolean isContinue = true;
                             while(isContinue){
                                 showUserMenu(user.getName());
                                 userChoice = scanner.nextInt();
                                 // Handle user choices
                                 switch (userChoice) {
                                    case 1:
                                        System.out.println("Check balnace");
                                        break;
                                    case 2:
                                        System.out.println("widraw");
                                        break;
                                    case 3:
                                        System.out.println("deposit");
                                        break;
                                    case 4:
                                        System.out.println("treansfer");
                                        break;
                                    case 5:
                                        System.out.println("transactions");
                                        break;
                                    case 6:
                                        System.out.println("change pin");
                                        break;
                                    case 7:
                                        System.out.println("logout");
                                        auth.logout(user);
                                        isContinue = false;
                                        break;
                                 
                                    default:
                                        break;
                                 }
                             };

                        }
                    } catch (Exception e) {
                        System.out.println("Invalid mobile number or PIN.");
                    } 
                    break;
                case 2:

                   //User user = auth.register()
                    break;
                case 3:
                    System.out.println("Thank you for using the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);

        scanner.close();
    

            
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
    public static void showMenu() {
        System.out.println("\nWelcome to Bank System");
        System.out.println("----------------------");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
    public static void showUserMenu(String name) {
        System.out.println("\nWelcome "+ name);
        System.out.println("----------------------");
        System.out.println("1. Check Balance");
        System.out.println("2. Widraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer Money");
        System.out.println("5. View Transactions");
        System.out.println("6. Change Pin");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");
    }
}
