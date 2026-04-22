import model.User;

import javax.swing.*;

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
    private User user;
    private MainFrame mainFrame;

    Dashboard(MainFrame mainFrame, User user){
        this.mainFrame = mainFrame;
        this.user = user;
        lblWelcome.setText("Welcome to Dashboard! " + user.getName());
        btnLogout.addActionListener(e -> {
            mainFrame.logout(user);

        });
    }
    public JPanel getPanel() {
        return panel;
    }

}
