import javax.swing.*;
import java.io.*;

/**
 *
 * @author Mervin Ooi
 */
public class ManagerAddResidentPage extends javax.swing.JFrame {

    /**
     * Creates new form ManagerAddManagerPage
     */
    public ManagerAddResidentPage() {
        initComponents();
    }

    public void getAvailableRooms() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Room_Info.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] roomInfo = line.split(",");
                int availability = Integer.getInteger(roomInfo[2]);
                if (Integer.getInteger(roomInfo[2]) != 0) {
                    RoomNumberComboBox.addItem(roomInfo[0]);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        AddResidentLabel = new javax.swing.JLabel();
        PasswordLabel = new javax.swing.JLabel();
        ConfirmPasswordLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        ContactNumberLabel = new javax.swing.JLabel();
        EmailLabel = new javax.swing.JLabel();
        GenderLabel = new javax.swing.JLabel();
        RoomNumberLabel = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        ContactNumberField = new javax.swing.JTextField();
        EmailField = new javax.swing.JTextField();
        ConfirmButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        PasswordField = new javax.swing.JPasswordField();
        ConfirmPasswordField = new javax.swing.JPasswordField();
        UsernameLabel = new javax.swing.JLabel();
        UsernameField = new javax.swing.JTextField();
        GenderComboBox = new javax.swing.JComboBox<>();
        RoomNumberComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AddResidentLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        AddResidentLabel.setText("Add Resident");

        PasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        PasswordLabel.setText("Password");

        ConfirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ConfirmPasswordLabel.setText("Confirm Password");

        NameLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        NameLabel.setText("Name");

        ContactNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ContactNumberLabel.setText("Contact Number");

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        EmailLabel.setText("Email Address");

        GenderLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        GenderLabel.setText("Gender");

        RoomNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        RoomNumberLabel.setText("Room Number");

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


        UsernameLabel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        UsernameLabel.setText("Username");

        GenderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));


        RoomNumberComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {} ));
        getAvailableRooms();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(226, 226, 226)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(ConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(84, 84, 84)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(RoomNumberLabel)
                                                        .addComponent(GenderLabel)
                                                        .addComponent(EmailLabel)
                                                        .addComponent(ContactNumberLabel)
                                                        .addComponent(NameLabel)
                                                        .addComponent(ConfirmPasswordLabel)
                                                        .addComponent(PasswordLabel)
                                                        .addComponent(UsernameLabel))
                                                .addGap(48, 48, 48)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(AddResidentLabel)
                                                        .addComponent(EmailField, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                                        .addComponent(ContactNumberField, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                                        .addComponent(NameField, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                                        .addComponent(PasswordField)
                                                        .addComponent(ConfirmPasswordField)
                                                        .addComponent(UsernameField)
                                                        .addComponent(GenderComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(RoomNumberComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(AddResidentLabel)
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(GenderLabel)
                                        .addComponent(GenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(RoomNumberLabel)
                                        .addComponent(RoomNumberComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(ConfirmButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void ConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        BufferedReader br = new BufferedReader(new FileReader("Resident_Info.txt"));
        int count = 0;
        String residentID = "";

        while (br.readLine() != null) {
            count++;
        }

        br.close();

        if (count + 1 < 10) {
            residentID = "R" + "000" + String.valueOf(count + 1);
        } else if (count + 1 < 100) {
            residentID = "R" + "00" + String.valueOf(count + 1);
        } else if (count + 1 < 1000) {
            residentID = "R" + "0" + String.valueOf(count + 1);
        } else {
            residentID = "R" + String.valueOf(count + 1);
        }

        String username = UsernameField.getText();
        String password = PasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();
        String name = NameField.getText();
        String contact = ContactNumberField.getText();
        String email = EmailField.getText();
        String gender = GenderComboBox.getSelectedItem().toString();
        String roomNumber = RoomNumberComboBox.getSelectedItem().toString();
        String overdueAmount = "0.00";
        String line = residentID + "," +  username + "," + password + "," + name + "," + contact + "," + email + "," + gender + "," + roomNumber + "," + overdueAmount;

        if (password.equals(confirmPassword)) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("Resident_Info.txt", true));
                bw.write(line);
                bw.close();
                JOptionPane.showMessageDialog(null, "Resident added successfully");

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
            java.util.logging.Logger.getLogger(ManagerAddResidentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerAddResidentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerAddResidentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerAddResidentPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerAddResidentPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel AddResidentLabel;
    private javax.swing.JButton ConfirmButton;
    private javax.swing.JPasswordField ConfirmPasswordField;
    private javax.swing.JLabel ConfirmPasswordLabel;
    private javax.swing.JTextField ContactNumberField;
    private javax.swing.JLabel ContactNumberLabel;
    private javax.swing.JTextField EmailField;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JComboBox<String> GenderComboBox;
    private javax.swing.JLabel GenderLabel;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JComboBox<String> RoomNumberComboBox;
    private javax.swing.JLabel RoomNumberLabel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.Box.Filler filler1;
    // End of variables declaration
}
