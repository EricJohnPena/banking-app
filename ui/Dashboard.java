import model.User;

import javax.swing.*;

public class Dashboard {
    private JPanel panel;
    private JLabel lblWelcome;
    private User user;

    Dashboard(User user){
        this.user = user;
        lblWelcome.setText("Welcome to Dashboard! " + user.getName());

    }
    public JPanel getPanel() {
        return panel;
    }
}
