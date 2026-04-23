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

public class CashOutForm {
    private JPanel panel;
    private JTextField txtCashOut;
    private JButton btnCashOut;
    private JButton btnCancel;

    private MainFrame mainFrame;
    private User user;

    CashOutForm(MainFrame mainFrame, User user){
        this.mainFrame = mainFrame;
        this.user = user;

        btnCashOut.addActionListener(e -> {
            double cashOutAmount = Double.parseDouble(txtCashOut.getText());
            try{
                Connection conn = DBConnection.getConnection();
                AccountDAO accountDAO = new AccountDAOImpl(conn);
                CashInOut cashOut = ((userID, amount, balance) -> {
                    double total = balance - amount;
                    return accountDAO.updateCash(total, "Cash Out",userID, userID, userID);
                });
                CheckBalance checkBalance = (userID -> {
                    return accountDAO.findAccount(userID);
                });
                Accounts accounts = checkBalance.check(user.getId());
                accounts = cashOut.moveCash(user.getId(), cashOutAmount,accounts.getAmount() );
                JOptionPane.showMessageDialog(panel, "Successfully withdraw :" + cashOutAmount);
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
