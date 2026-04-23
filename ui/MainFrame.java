import model.User;

import javax.swing.*;

public class MainFrame extends JFrame {
    private User currentUser;
    public MainFrame() {

        setTitle("Simple Swing App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showLogin();

        setVisible(true);
    }
    public void setUser(User currentUser){
        this.currentUser = currentUser;
    }
    public void logout(User user){
        setUser(user);
        showLogin();
    }

    public void showLogin() {
        Login loginForm = new Login(this);
        setContentPane(loginForm.getPanel());
        revalidate();
        repaint();
    }

    public void showDashboard(User user) {
        Dashboard dashboardForm = new Dashboard(this, user);
        //setSize(500, 600);
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
    public void showChangePIN(User user){
        ChangePIN changePINForm = new ChangePIN(this, user);
        setContentPane(changePINForm.getPanel());
        revalidate();
        repaint();
    }



}
