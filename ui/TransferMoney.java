import dao.*;
import dao.impl.*;
import model.User;
import service.CashTransfer;
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
            try{
                String recipient = txtNumber.getText();
                System.out.println(recipient);
                double amountToSend = Double.parseDouble(txtAmount.getText());
                Connection conn = DBConnection.getConnection();
                UserDAO userDAO = new UserDAOImpl(conn);
                TransactionDAO transactionDAO = new TransactionDAOImpl();
                AccountDAO accountDAO = new AccountDAOImpl(conn);
                CashTransfer transfer = ((userID, recipientNumber, senderNumber, amount) -> {
                    User receiver = userDAO.findUserByNumber(recipientNumber);
                    accountDAO.updateCash(-amount, user.getId());
                    accountDAO.updateCash(amount, receiver.getId());
                    transactionDAO.insertTransaction(-amount,"Send", recipientNumber, senderNumber,userID);
                    transactionDAO.insertTransaction(amount,"Receive", recipientNumber, senderNumber,receiver.getId());
                });
                transfer.cashTransfer(user.getId(),recipient,user.getNumber(), amountToSend);
                JOptionPane.showMessageDialog(panel,"Transferred " + amountToSend + " to " + recipient);
            } catch (RuntimeException | SQLException ex) {
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
