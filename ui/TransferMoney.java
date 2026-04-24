import dao.*;
import dao.impl.*;
import model.Accounts;
import model.User;
import service.CashTransfer;
import service.CheckBalance;
import service.UserValidator;
import util.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TransferMoney {
    private JPanel panel;
    private JTextField txtAmount;
    private JTextField txtNumber;
    private JButton btnSend;
    private JButton cancelButton;
    private MainFrame mainFrame;
    private User user;
    TransferMoney(MainFrame mainFrame, User user){
        this.mainFrame = mainFrame;
        this.user = user;
        btnSend.addActionListener(e -> {
            double amountToSend = 0.0;
            if (!UserValidator.isValidMobileNumber(txtNumber.getText())) {
                JOptionPane.showMessageDialog(panel, "Invalid mobile number." +
                        "\nMobile number must only be composed of 10 digits." +
                        "\nEx.: 9876543210");
                return;
            }
            String recipient = txtNumber.getText();
            try {
                //checking if amountToSend is a valid number
                amountToSend = Double.parseDouble(txtAmount.getText());
                try {
                    Connection conn = DBConnection.getConnection();
                    UserDAO userDAO = new UserDAOImpl(conn);
                    TransactionDAO transactionDAO = new TransactionDAOImpl();
                    AccountDAO accountDAO = new AccountDAOImpl(conn);
                    //lambda for transferring cash
                    CashTransfer transfer = ((userID, recipientNumber, senderNumber, amount) -> {
                        User receiver = userDAO.findUserByNumber(recipientNumber);
                        accountDAO.updateCash(-amount, user.getId());
                        accountDAO.updateCash(amount, receiver.getId());
                        transactionDAO.insertTransaction(-amount, "Send", recipientNumber, senderNumber, userID);
                        transactionDAO.insertTransaction(amount, "Receive", recipientNumber, senderNumber, receiver.getId());
                    });
                    // lambda for checking available balance
                    CheckBalance checkBalance = (userID -> {
                        return accountDAO.findAccount(userID);
                    });
                    //validation for checking if amount to send does not exceed remaining balance
                    Accounts account = checkBalance.check(user.getId());//used CheckBalance lambda
                    if(account.getAmount() < amountToSend){
                        JOptionPane.showMessageDialog(panel, "Insufficient balance.");
                        return;
                    }
                    //validation for receiver number if existing
                    if(!userDAO.isExist("users", "number",recipient)){
                        JOptionPane.showMessageDialog(panel, "User with mobile number "+ recipient+" does not exist.");
                        return;
                    }
                    //execute cash transfer using CashTransfer lambda
                    transfer.cashTransfer(user.getId(), recipient, user.getNumber(), amountToSend);
                    JOptionPane.showMessageDialog(panel, "Transferred " + amountToSend + " to " + recipient);
                    mainFrame.showDashboard(user);
                } catch (RuntimeException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Input must be a valid amount.");
                throw new RuntimeException(ex);
            }
                 
                
        });

        cancelButton.addActionListener(e -> {
            mainFrame.showDashboard(user);
        });

    }
    public JPanel getPanel() {
        return panel;
    }
}
