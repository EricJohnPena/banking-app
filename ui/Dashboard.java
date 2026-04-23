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
    private Accounts account;
    private User user;

    private MainFrame mainFrame;
    private Connection conn;

    Dashboard(MainFrame mainFrame, User user){
        this.mainFrame = mainFrame;
        this.user = user;
        this.account = account;

        lblWelcome.setText("Welcome to Dashboard! " + user.getName());
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
                mainFrame.logout(user);
        });
    }
    public JPanel getPanel() {
        return panel;
    }

}
