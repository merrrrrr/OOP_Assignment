import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField username;
    private JPasswordField password;
    private JButton registerButton;
    private JButton loginButton;
    private JPanel Page;

    User user = new User();
    Manager manager = new Manager();
    Staff staff = new Staff();
    Resident resident = new Resident();

    public Login () {
        add(Page);
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(Page);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                register.setVisible(true);
                dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = password.getText();
                if (user.equals("admin") && pass.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    dispose();
                    Main main = new Main();
                    main.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            }
        });


    }

    public static void main(String[] args) {
        Login login = new Login();
    }
}

