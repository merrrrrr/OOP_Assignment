import javax.swing.*;
import java.io.*;

/**
 *
 * @author Mervin Ooi
 */
public class ManagerAddManagerPage extends javax.swing.JFrame {

    private Manager manager;

    /**
     * Creates new form ManagerAddResidentPAge
     */
    public ManagerAddManagerPage() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public ManagerAddManagerPage(Manager manager) {
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

        ConfirmButton = new javax.swing.JButton();
        EmailLabel = new javax.swing.JLabel();
        EmailField = new javax.swing.JTextField();
        ContactNumberField = new javax.swing.JTextField();
        ContacNumbertLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        ConfirmPasswordField = new javax.swing.JPasswordField();
        ConfirmPasswordLabel = new javax.swing.JLabel();
        PasswordLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        UsernameField = new javax.swing.JTextField();
        UsernameLabel = new javax.swing.JLabel();
        AddManagerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ConfirmButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
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

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        EmailLabel.setText("Email Address");

        ContacNumbertLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ContacNumbertLabel.setText("Contact Number");

        NameLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        NameLabel.setText("Name");


        ConfirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ConfirmPasswordLabel.setText("Confirm Password");

        PasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        PasswordLabel.setText("Password");

        UsernameLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        UsernameLabel.setText("Username");

        AddManagerLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        AddManagerLabel.setText("Add Manager");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(AddManagerLabel)
                                .addGap(228, 228, 228))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(UsernameLabel)
                                        .addComponent(PasswordLabel)
                                        .addComponent(ConfirmPasswordLabel)
                                        .addComponent(NameLabel)
                                        .addComponent(ContacNumbertLabel)
                                        .addComponent(EmailLabel))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ContactNumberField)
                                        .addComponent(NameField)
                                        .addComponent(EmailField, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(PasswordField)
                                        .addComponent(ConfirmPasswordField)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(84, 84, 84))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(227, 227, 227)
                                .addComponent(ConfirmButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(AddManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(ContacNumbertLabel)
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
        BufferedReader br = new BufferedReader(new FileReader("Manager_Info.txt"));
        int count = 0;
        String managerID = "";

        while (br.readLine() != null) {
            count++;
        }

        br.close();

        boolean isIDExist = true;
        while (isIDExist) {
            if (count + 1 < 10) {
                managerID = "M" + "000" + String.valueOf(count + 1);
            } else if (count + 1 < 100) {
                managerID = "M" + "00" + String.valueOf(count + 1);
            } else if (count + 1 < 1000) {
                managerID = "M" + "0" + String.valueOf(count + 1);
            } else {
                managerID = "M" + String.valueOf(count + 1);
            }
            isIDExist = false;
            br = new BufferedReader(new FileReader("Manager_Info.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] residentInfo = line.split(",");
                if (managerID.equals(residentInfo[0])) {
                    count++;
                    isIDExist = true;
                    break;
                }
            }
            br.close();
        }

        ManagerMenuPage managerMenuPage = new ManagerMenuPage(manager);

        String username = UsernameField.getText();
        String password = PasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();
        String name = NameField.getText();
        String contact = ContactNumberField.getText();
        String email = EmailField.getText();
        String line = managerID + "," +  username + "," + password + "," + name + "," + contact + "," + email;

        if (username == null || password == null || confirmPassword == null || name == null || contact == null || email == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        } else if (username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty() || name.trim().isEmpty() || contact.trim().isEmpty() || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        } else if (!manager.validateName(name)) {
            JOptionPane.showMessageDialog(null, "Invalid name. No special characters or numbers allowed");
            return;
        } else if (!manager.validatePassword(password)) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number and one special character");
            return;
        } else if (!manager.validateEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email address. Please enter a valid email address");
            return;
        } else if (!manager.validateContactNumber(contact)) {
            JOptionPane.showMessageDialog(null, "Invalid contact number. Please enter a valid contact number without any special characters");
            return;
        } else if (!manager.isUsernameUnique(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists. Please enter a different username");
            return;
        } else if (!manager.isContactNumberUnique(contact)) {
            JOptionPane.showMessageDialog(null, "Contact number already exists. Please enter a different contact number");
            return;
        } else if (!manager.isEmailUnique(email)) {
            JOptionPane.showMessageDialog(null, "Email address already exists. Please enter a different email address");
            return;
        }

        if (password.equals(confirmPassword)) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("Manager_Info.txt", true));
                bw.newLine();
                bw.write(line);
                bw.close();
                JOptionPane.showMessageDialog(null, "Manager added successfully");

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Password does not match");
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
            java.util.logging.Logger.getLogger(ManagerAddManagerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerAddManagerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerAddManagerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerAddManagerPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerAddManagerPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton ConfirmButton;
    private javax.swing.JPasswordField ConfirmPasswordField;
    private javax.swing.JLabel ConfirmPasswordLabel;
    private javax.swing.JLabel ContacNumbertLabel;
    private javax.swing.JTextField ContactNumberField;
    private javax.swing.JTextField EmailField;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.JLabel AddManagerLabel;
    // End of variables declaration
}