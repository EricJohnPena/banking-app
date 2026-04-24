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

public class Dashboard {
    private JPanel panel;
    private JLabel lblWelcome;
    private JButton btnCheckBalance;
    private JButton btnTransactions;
    private JButton btnCashOut;
    private JButton btnCashIn;
    private JButton btnTransfer;
    private JButton btnChangePIN;
    private JButton btnLogout;
    private JLabel lblName;
    private JLabel lblEmail;
    private JLabel lblNumber;
    private Accounts account;
    private User user;

    private MainFrame mainFrame;
    private Connection conn;

    Dashboard(MainFrame mainFrame, User user){
        this.mainFrame = mainFrame;
        this.user = user;
        this.account = account;

        lblName.setText(user.getName());
        lblEmail.setText(user.getEmail());
        lblNumber.setText(user.getNumber());
        btnCheckBalance.addActionListener(e -> {
            try {
                conn = DBConnection.getConnection();
                AccountDAO accountDAO = new AccountDAOImpl(conn);
                CheckBalance checkBalance = (userID -> {
                   return accountDAO.findAccount(userID);
                });
                 Accounts accounts = checkBalance.check(user.getId());
                JOptionPane.showMessageDialog(panel,"Your current balance is: " + accounts.getAmount());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnCashIn.addActionListener(e ->{
            mainFrame.showCashInForm(user);
        });
        btnTransactions.addActionListener(e -> {
            mainFrame.showViewTransactions(user);
        });
        btnCashOut.addActionListener(e ->{
            mainFrame.showCashOutForm(user);
        });
        btnTransfer.addActionListener(e ->{
            mainFrame.showTransferMoney(user);
        });

        btnChangePIN.addActionListener(e -> {
            mainFrame.showChangePIN(user);

        });
        btnLogout.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    panel,
                    "Do you really want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION)
                mainFrame.logout(user);

        });
    }
    public JPanel getPanel() {
        return panel;
    }

}
