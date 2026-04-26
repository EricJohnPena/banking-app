import dao.UserDAO;
import dao.impl.UserDAOImpl;
import model.User;
import service.UserValidator;
import service.impl.UserAuthenticationImpl;
import util.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ChangePIN {
    private JPanel panel;
    private JTextField txtOldPIN;
    private JPasswordField pwdNewPIN;
    private JPasswordField pwdReNewPIN;
    private JButton btnCancel;
    private JButton btnSave;

    private MainFrame mainFrame;
    private User user;

    ChangePIN(MainFrame mainFrame, User user){
        this.mainFrame = mainFrame;
        this.user = user;

        btnSave.addActionListener(e -> {
            String oldPin = txtOldPIN.getText();
            String newPIN = new String(pwdNewPIN.getPassword());
            String reNewPIN = new String(pwdReNewPIN.getPassword());
            //validation for PIN number
            if(oldPin.isEmpty()|| newPIN.isEmpty() || reNewPIN.isEmpty()){
                JOptionPane.showMessageDialog(panel, "All fields are required!");
                return;
            }
            if(!UserValidator.isValidPIN(newPIN)){
                JOptionPane.showMessageDialog(panel, "Invalid PIN number");
                return;
            }
            if(!oldPin.equals(user.getPin())){
                JOptionPane.showMessageDialog(panel, "Incorrect PIN!");
                return;
            }
            if(!newPIN.equals(reNewPIN)){
                JOptionPane.showMessageDialog(panel, "New PIN did not match.\nPlease try again.");
                return;
            }

            try {
                Connection conn = DBConnection.getConnection();
                UserDAO userDAO = new UserDAOImpl(conn);
                UserAuthenticationImpl auth = new UserAuthenticationImpl(userDAO);
                auth.changePin(user, newPIN);
                JOptionPane.showMessageDialog(panel,"PIN changed successfully.");
                mainFrame.showDashboard(user);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });


        btnCancel.addActionListener(e -> {
            mainFrame.showDashboard(user);
        });



    }
    public JPanel getPanel() {
        return panel;
    }
}
