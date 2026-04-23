import model.User;

import javax.swing.*;

public class ChangePIN {
    private JPanel panel;
    private JTextField txtOldPIN;
    private JPasswordField pwdNewPIN;
    private JPasswordField pwdReNewPIN;
    private JButton btnCancel;
    private JButton btnSave;

    private MainFrame mainFrame;
    private User user;

    ChangePIN(MainFrame mainFrame, User user){
        this.mainFrame = mainFrame;
        this.user = user;

        btnCancel.addActionListener(e -> {
            mainFrame.showDashboard(user);
        });



    }
    public JPanel getPanel() {
        return panel;
    }
}
