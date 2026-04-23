import dao.AccountDAO;
import dao.impl.AccountDAOImpl;
import model.Accounts;
import model.User;
import service.CashInOut;
import service.CheckBalance;
import util.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class CashInForm {
    private JPanel panel;
    private JTextField txtCashIn;
    private JButton btnCashIn;
    private JButton btnCancel;
    private MainFrame mainFrame;
    private User user;
    private Accounts account;

    CashInForm(MainFrame mainFrame, User user){
        this.mainFrame = mainFrame;
        this.user = user;

        btnCashIn.addActionListener(e -> {
            double cashAmount = Double.parseDouble(txtCashIn.getText());
            try{
                Connection conn = DBConnection.getConnection();
                AccountDAO accountDAO = new AccountDAOImpl(conn);
                CashInOut cashIn = ((userID, amount, balance) -> {
                   double total = amount + balance;
                    return accountDAO.updateCash(userID, total);
                });
                CheckBalance checkBalance = (userID -> {
                    return accountDAO.findAccount(userID);
                });
                Accounts accounts = checkBalance.check(user.getId());
                System.out.println(accounts.getAmount());
                 accounts = cashIn.moveCash(user.getId(),cashAmount, accounts.getAmount() );
                JOptionPane.showMessageDialog(panel,"Successfully deposited: " + cashAmount);
                mainFrame.showDashboard(user);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        btnCancel.addActionListener(e ->{
            mainFrame.showDashboard(user);
        });

    }
    public JPanel getPanel() {
        return panel;
    }



}
