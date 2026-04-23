import dao.TransactionDAO;
import dao.impl.TransactionDAOImpl;
import model.Transaction;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TransactionView {
    private JPanel panel;
    private JTable tblTransactions;
    private JButton btnBack;
    private MainFrame mainFrame;
    User user;
    public TransactionView(MainFrame mainFrame, User user){
        this.mainFrame = mainFrame;
        this.user = user;

        loadUsers();
        btnBack.addActionListener(e -> mainFrame.showDashboard(user));


    }

    public JPanel getPanel() {
        return panel;
    }
     void loadUsers() {
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        List<Transaction> transactions = transactionDAO.viewUserTransactions(user.getId());

        String[] columns = { "Type", "Date", "Amount", "From", "To"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Transaction transaction : transactions) {
            Object[] row = {
                    transaction.getType(),
                    transaction.getDate(),
                    transaction.getAmount(),
                    transaction.getTransferFromNumber(),
                    transaction.getTransferToNumber()
            };
            model.addRow(row);
        }

        tblTransactions.setModel(model);
    }


}

