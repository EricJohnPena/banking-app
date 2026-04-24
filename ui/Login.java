
import dao.impl.UserDAOImpl;
import model.User;
import service.UserValidator;
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
            if(numberInput.isEmpty() || passwordPIN.getPassword().length==0){
                JOptionPane.showMessageDialog(panel, "All fields are required.");
            }
            else if(!UserValidator.isValidMobileNumber(numberInput)){
                JOptionPane.showMessageDialog(panel, "Invalid mobile number");
            }else if(!UserValidator.isValidPIN(pinInput)){
                JOptionPane.showMessageDialog(panel, "Invalid PIN.");
            }
            else
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
                        JOptionPane.showMessageDialog(panel, "Incorrect mobile number or PIN.");
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
