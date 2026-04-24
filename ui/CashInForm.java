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
            double cashAmount = 0.0;
            try{
                  cashAmount = Double.parseDouble(txtCashIn.getText());
                if(cashAmount <= 0){
                    JOptionPane.showMessageDialog(panel,"Only positive integers are allowed.");
                }
                else {
                    try {
                        Connection conn = DBConnection.getConnection();
                        AccountDAO accountDAO = new AccountDAOImpl(conn);
                        TransactionDAO transactionDAO = new TransactionDAOImpl();
                        CashInOut cashIn = ((userID, amount) -> {
                            accountDAO.updateCash(amount, userID);
                        });

                        cashIn.moveCash(user.getId(), cashAmount);
                        transactionDAO.insertTransaction(cashAmount, "Cash In", user.getNumber(), user.getNumber(), user.getId());
                        JOptionPane.showMessageDialog(panel, "Successfully deposited: " + cashAmount);
                        mainFrame.showDashboard(user);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
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
