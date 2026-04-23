import dao.AccountDAO;
import dao.TransactionDAO;
import dao.impl.AccountDAOImpl;
import dao.impl.TransactionDAOImpl;
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
                TransactionDAO transactionDAO = new TransactionDAOImpl();
                CashInOut cashOut = ((userID, amount) -> {
                     accountDAO.updateCash(-amount, userID);
                });
                cashOut.moveCash(user.getId(), cashOutAmount );
                transactionDAO.insertTransaction(cashOutAmount, "Cash In",user.getNumber(), user.getNumber(), user.getId());
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
