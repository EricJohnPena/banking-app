import dao.UserDAO;
import dao.impl.UserDAOImpl;
import service.impl.UserAuthenticationImpl;
import util.DBConnection;

import javax.swing.*;
import java.sql.*;

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
            try{
                conn = DBConnection.getConnection();
                UserDAO userDAO = new UserDAOImpl(conn);
                UserAuthenticationImpl auth = new UserAuthenticationImpl(userDAO);

                auth.register(nameInput,emailInput,numberInput,pinInput);
                mainFrame.showLogin();
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
