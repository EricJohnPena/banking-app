import dao.TransactionDAO;
import dao.impl.TransactionDAOImpl;
import model.Transaction;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
            //ternary operator. this is just for not displaying the current user's number to the table
            String from = transaction.getTransferFromNumber().equals(user.getNumber())
                    ? "-"
                    : transaction.getTransferFromNumber();

            String to = transaction.getTransferToNumber().equals(user.getNumber())
                    ? "-"
                    : transaction.getTransferToNumber();
            Object[] row = {
                    transaction.getType(),
                    transaction.getDate(),
                    transaction.getAmount(),
                    from ,//from ternary operator
                    to//from ternary operator
            };
            model.addRow(row);
        }

        tblTransactions.setModel(model);
         // Center renderer
         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
         centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Apply to all columns
         TableColumnModel columnModel = tblTransactions.getColumnModel();
         for (int i = 0; i < columnModel.getColumnCount(); i++) {
             columnModel.getColumn(i).setCellRenderer(centerRenderer);
         }
    }


}

