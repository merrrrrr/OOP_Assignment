import javax.swing.*;
import java.io.*;

/**
 *
 * @author Mervin Ooi
 */
public class ManagerAddStaffPage extends javax.swing.JFrame {

    /**
     * Creates new form ManagerAddStaffPage
     */
    private Manager manager;

    public ManagerAddStaffPage() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public ManagerAddStaffPage(Manager manager) {
        this.manager = manager;
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        PasswordLabel = new javax.swing.JLabel();
        UsernameLabel = new javax.swing.JLabel();
        AddStaffLabel = new javax.swing.JLabel();
        UsernameField = new javax.swing.JTextField();
        ConfirmPasswordLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        ContactNumberLabel = new javax.swing.JLabel();
        EmailLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        ConfirmPasswordField = new javax.swing.JPasswordField();
        NameField = new javax.swing.JTextField();
        ContactNumberField = new javax.swing.JTextField();
        EmailField = new javax.swing.JTextField();
        ConfirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        PasswordLabel.setText("Password");

        UsernameLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        UsernameLabel.setText("Username");

        AddStaffLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        AddStaffLabel.setText("Add Staff");

        ConfirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ConfirmPasswordLabel.setText("Confirm Password");

        NameLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        NameLabel.setText("Name");

        ContactNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ContactNumberLabel.setText("Contact Number");

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        EmailLabel.setText("Email Address");


        ConfirmButton.setText("Confirm");
        ConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    ConfirmButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(227, Short.MAX_VALUE)
                                .addComponent(ConfirmButton)
                                .addGap(227, 227, 227))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PasswordLabel)
                                        .addComponent(UsernameLabel)
                                        .addComponent(ConfirmPasswordLabel)
                                        .addComponent(NameLabel)
                                        .addComponent(ContactNumberLabel)
                                        .addComponent(EmailLabel))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(ContactNumberField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(NameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                        .addComponent(ConfirmPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(EmailField)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(AddStaffLabel))
                                        .addComponent(UsernameField, javax.swing.GroupLayout.Alignment.LEADING))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(AddStaffLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(UsernameLabel)
                                        .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(PasswordLabel)
                                        .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ConfirmPasswordLabel)
                                        .addComponent(ConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(NameLabel)
                                        .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ContactNumberLabel)
                                        .addComponent(ContactNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(EmailLabel)
                                        .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(ConfirmButton)
                                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void ConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        String staffID = manager.generateId("Staff_Info.txt");

        ManagerMenuPage managerMenuPage = new ManagerMenuPage(manager);

        String username = UsernameField.getText();
        String password = PasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();
        String name = NameField.getText();
        String contact = ContactNumberField.getText();
        String email = EmailField.getText();

        if (username == null || password == null || confirmPassword == null || name == null || contact == null || email == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        } else if (username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty() || name.trim().isEmpty() || contact.trim().isEmpty() || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        }else if (!manager.validateName(name)) {
            JOptionPane.showMessageDialog(null, "Invalid name. No special characters or numbers allowed");
            return;
        } else if (!manager.validatePassword(password)) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number and one special character");
            return;
        } else if (!manager.validateEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email address. Please enter a valid email address");
            return;
        } else if (!manager.validateContactNumber(contact)) {
            JOptionPane.showMessageDialog(null, "Invalid contact number. Please enter a valid contact number between 9 and 11 digit without any special characters");
            return;
        } else if (!manager.isEmailUnique(email)) {
            JOptionPane.showMessageDialog(null, "Email address already exists. Please enter a different email address.");
            return;
        } else if (!manager.isUsernameUnique(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists. Please enter a different username.");
            return;
        } else if (!manager.isContactNumberUnique(contact)) {
            JOptionPane.showMessageDialog(null, "Contact number already exists. Please enter a different contact number.");
            return;
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Password does not match");
            return;
        }

        String line = staffID + "," +  username + "," + password + "," + name + "," + contact + "," + email;


        try {
            BufferedReader br = new BufferedReader(new FileReader("Staff_Info.txt"));
            int count = 0;
            while (br.readLine() != null) {
                count++;
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("Staff_Info.txt", true));

            if (count != 0) {
                bw.newLine();
            }
            bw.write(line);
            bw.close();
            JOptionPane.showMessageDialog(null, "Staff added successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }


        this.dispose();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagerAddStaffPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerAddStaffPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerAddStaffPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerAddStaffPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerAddStaffPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton ConfirmButton;
    private javax.swing.JPasswordField ConfirmPasswordField;
    private javax.swing.JLabel ConfirmPasswordLabel;
    private javax.swing.JTextField ContactNumberField;
    private javax.swing.JLabel ContactNumberLabel;
    private javax.swing.JTextField EmailField;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.JLabel AddStaffLabel;
    // End of variables declaration
}
