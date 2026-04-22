import model.User;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Simple Swing App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showLogin();

        setVisible(true);
    }

    public void showLogin() {
        Login loginForm = new Login(this);
        setContentPane(loginForm.getPanel());
        revalidate();
        repaint();
    }

    public void showDashboard(User user) {
        Dashboard dashboardForm = new Dashboard(user);
        setContentPane(dashboardForm.getPanel());
        revalidate();
        repaint();
    }

    public void showRegister(){
        Register registerForm = new Register(this);
        setSize(400, 500);
        setContentPane(registerForm.getPanel());
        revalidate();
        repaint();
    }
}
