import dao.UserDAO;
import dao.impl.UserDAOImpl;
import model.User;
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
            String newPIN = new String(pwdNewPIN.getPassword());
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
