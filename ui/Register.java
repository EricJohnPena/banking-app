import dao.UserDAO;
import dao.impl.UserDAOImpl;
import service.UserValidator;
import service.impl.UserAuthenticationImpl;
import util.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Register {
    private JPanel panel;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtNumber;
    private JPasswordField pwdPIN;
    private JPasswordField pwdRePIN;
    private JButton btnLogin;
    private JButton btnRegister;

    private MainFrame mainFrame;
    private Connection conn;

    public Register(MainFrame mainFrame){
        this.mainFrame = mainFrame;

        btnRegister.addActionListener(e ->{
            String nameInput = txtName.getText();
            String emailInput = txtEmail.getText();
            String numberInput = txtNumber.getText();
            String pinInput = new String(pwdPIN.getPassword());
            String pinReInput = new String(pwdRePIN.getPassword());
            //validation for inputs
            if(nameInput.isEmpty() || emailInput.isEmpty() || numberInput.isEmpty() || pinInput.isEmpty() || pinReInput.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "All fields are required.");
                return;
            }
            if(!UserValidator.isValidName(nameInput)){
                JOptionPane.showMessageDialog(panel, "Invalid name." +
                        "\nName must be at least 3 characters long.");
                return;
            }
            if(!UserValidator.isValidEmail(emailInput)){
                JOptionPane.showMessageDialog(panel, "Invalid email." +
                        "\nEx.: 'test@email.com'");
                return;
            }
            if(!UserValidator.isValidMobileNumber(numberInput)){
                JOptionPane.showMessageDialog(panel, "Invalid mobile number." +
                        "\nMobile number must only be composed of 10 digits." +
                        "\nEx.: 9876543210");
                return;
            }
            if(!UserValidator.isValidPIN(pinInput)){
                JOptionPane.showMessageDialog(panel, "Invalid PIN." +
                        "\nPIN must be 4 digits long.");
                return;
            }
            if(!pinInput.equals(pinReInput)){
                JOptionPane.showMessageDialog(panel, "PIN did not match." +
                        "\nPlease try again.");
                return;
            }
            try{
                conn = DBConnection.getConnection();
                UserDAO userDAO = new UserDAOImpl(conn);
                UserAuthenticationImpl auth = new UserAuthenticationImpl(userDAO);
                //validation if user mobile number and email already exists
                if(userDAO.isExist("users", "number", numberInput)){
                    JOptionPane.showMessageDialog(panel, "Mobile number already exists!");
                    return;
                }
                if(userDAO.isExist("users", "email", emailInput)){
                    JOptionPane.showMessageDialog(panel, "Email address already exists!");
                    return;
                }
                try {
                    auth.register(nameInput,emailInput,numberInput,pinInput);
                    JOptionPane.showMessageDialog(panel, "Registered successfully!");
                    mainFrame.showLogin();
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage());
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });

        btnLogin.addActionListener(e ->{
            mainFrame.showLogin();
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
