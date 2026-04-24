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
            double cashOutAmount = 0.0;
            try{
               cashOutAmount = Double.parseDouble(txtCashOut.getText());
                try{
                    Connection conn = DBConnection.getConnection();
                    AccountDAO accountDAO = new AccountDAOImpl(conn);
                    TransactionDAO transactionDAO = new TransactionDAOImpl();
                    //lambda for checking balance
                    CheckBalance checkBalance = (userID -> {
                        return accountDAO.findAccount(userID);
                    });
                    //validation for current uer balance
                    Accounts account = checkBalance.check(user.getId());
                    if(account.getAmount() < cashOutAmount){
                        JOptionPane.showMessageDialog(panel, "Insufficient balance.");
                        return;
                    }
                    CashInOut cashOut = ((userID, amount) -> {
                        accountDAO.updateCash(-amount, userID);
                    });
                    //subtracting the balance on database table
                    cashOut.moveCash(user.getId(), cashOutAmount );
                    //logging on transactions table
                    transactionDAO.insertTransaction(cashOutAmount, "Cash In",user.getNumber(), user.getNumber(), user.getId());
                    JOptionPane.showMessageDialog(panel, "Successfully withdraw :" + cashOutAmount);
                    mainFrame.showDashboard(user);


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Input must be a valid number.");
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
