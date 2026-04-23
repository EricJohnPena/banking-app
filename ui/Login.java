import dao.AccountDAO;
import dao.UserDAO;
import dao.impl.AccountDAOImpl;
import dao.impl.UserDAOImpl;
import model.Accounts;
import model.User;
import service.CheckBalance;
import service.impl.UserAuthenticationImpl;
import util.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Login {
    private JPanel panel;
    private JTextField textNumber;
    private JPasswordField passwordPIN;
    private JButton btnRegister;
    private JButton btnLogin;

    private MainFrame mainFrame;
    private Connection conn;



    public Login(MainFrame mainFrame){
        this.mainFrame = mainFrame;

        btnLogin.addActionListener(e -> {
            String numberInput = textNumber.getText();
            String pinInput = new String(passwordPIN.getPassword());
            System.out.println(pinInput);
            {
                try {
                    conn = DBConnection.getConnection();
                    UserDAOImpl userDAO = new UserDAOImpl(conn);
                    UserAuthenticationImpl auth = new UserAuthenticationImpl(userDAO);
                    User user = auth.login(numberInput,pinInput);
                    if (user != null) {
                        JOptionPane.showMessageDialog(panel, "Login Successful!");
                        mainFrame.setUser(user);
                        mainFrame.showDashboard(user);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid Credentials!");
                    }
                } catch (SQLException s) {
                    throw new RuntimeException(s);
                }
            }

        });

        btnRegister.addActionListener(e -> {
            mainFrame.showRegister();
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
