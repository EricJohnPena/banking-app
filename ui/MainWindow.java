import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

  private CustomPanel1 panel1;
  private CustomButton1 button2;
  private CustomButton2 button3;
  private CustomLabel1 label4;

  public MainWindow() {
    setTitle("MainWindow");
    setSize(1024, 768);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

    panel1 = new CustomPanel1();
    panel1.setBounds(25, 22, 200, 150);
    panel1.setLayout(null);
    this.add(panel1);

    button2 = new CustomButton1();
    button2.setBounds(70, 87, 120, 36);
    this.add(button2);

    button3 = new CustomButton2();
    button3.setBounds(43, 125, 120, 36);
    panel1.add(button3);

    label4 = new CustomLabel1();
    label4.setBounds(40, 28, 120, 28);
    panel1.add(label4);

    setLocationRelativeTo(null);
  }
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainWindow frame = new MainWindow();
      frame.setVisible(true);
    });
  }
}
