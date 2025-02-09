import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 *
 * @author Mervin Ooi
 */
public class ManagerAddResidentPage extends javax.swing.JFrame {

    private Manager manager;

    /**
     * Creates new form ManagerAddManagerPage
     */
    public ManagerAddResidentPage() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public ManagerAddResidentPage(Manager manager) {
        this.manager = manager;
        initComponents();
        setLocationRelativeTo(null);
    }

    public void getAvailableRooms() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Room_Info.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] roomInfo = line.split(",");
                int availability = Integer.valueOf(roomInfo[2]);
                if (GenderComboBox.getSelectedIndex() == 0 && Integer.valueOf(roomInfo[2]) != 0) {
                    if (roomInfo[0].toString().charAt(0) == 'M') {
                        RoomNumberComboBox.addItem(roomInfo[0]);
                        RoomNumberComboBox.setVisible(true);
                    }
                } else if (GenderComboBox.getSelectedIndex() == 1 && Integer.valueOf(roomInfo[2]) != 0) {
                    if (roomInfo[0].toString().charAt(0) == 'F') {
                        RoomNumberComboBox.addItem(roomInfo[0]);
                        RoomNumberComboBox.setVisible(true);
                    }
                }
            }

            br.close();
            return;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        GenderComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomNumberComboBox.removeAllItems();
                getAvailableRooms();
            }
        });

        RoomNumberComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {} ));
        RoomNumberComboBox.setVisible(false);

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

        while (br.readLine() != null) {
            count++;
        }

        br.close();

        String residentID = manager.generateId("Resident_Info.txt");
        String username = UsernameField.getText();
        String password = PasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();
        String name = NameField.getText();
        String contact = ContactNumberField.getText();
        String email = EmailField.getText();
        String gender = GenderComboBox.getSelectedItem().toString();
        String roomNumber = RoomNumberComboBox.getSelectedItem().toString();
        String payableAmount = "";
        String roomType = "";
        if (roomNumber.charAt(1) == 'S') {
            roomType = "Single Room";
        } else if (roomNumber.charAt(1) == 'D') {
            roomType = "Double Sharing Room";
        } else if (roomNumber.charAt(1) == 'T') {
            roomType = "Triple Sharing Room";
        }

        LocalDate date = LocalDate.now();
        String line = residentID + "," +  username + "," + password + "," + name + "," + contact + "," + email + "," + gender + "," + roomNumber + "," + roomType + "," + payableAmount + "," + date.toString();

        ManagerMenuPage managerMenuPage = new ManagerMenuPage(manager);

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
            JOptionPane.showMessageDialog(null, "Invalid contact number. Please enter a valid contact number between 9 and 11 digit without any special characters");
            return;
        } else if (!manager.isUsernameUnique(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists. Please enter a different username");
            return;
        } else if (!manager.isEmailUnique(email)) {
            JOptionPane.showMessageDialog(null, "Email already exists. Please enter a different email");
            return;
        } else if (!manager.isContactNumberUnique(contact)) {
            JOptionPane.showMessageDialog(null, "Contact number already exists. Please enter a different contact number");
            return;
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Password does not match");
            return;
        }


        try {
            BufferedReader roomInfoReader = new BufferedReader(new FileReader("Room_Info.txt"));
            String roomInfoLine = "";
            ArrayList<String> roomInfoList = new ArrayList<>();
            while ((roomInfoLine = roomInfoReader.readLine()) != null) {
                roomInfoList.add(roomInfoLine);
            }

            for (int i = 0; i < roomInfoList.size(); i++) {
                String[] roomInfo = roomInfoList.get(i).split(",");
                if (roomInfo[0].equals(roomNumber)) {
                    roomInfo[2] = String.valueOf(Integer.valueOf(roomInfo[2]) - 1);
                    roomInfoList.set(i, roomInfo[0] + "," + roomInfo[1] + "," + roomInfo[2] + "," + roomInfo[3]);
                    break;
                }
            }

            StringJoiner sj = new StringJoiner(System.lineSeparator());
            for (String s : roomInfoList) {
                sj.add(s);
            }

            BufferedWriter roomInfoWriter = new BufferedWriter(new FileWriter("Room_Info.txt"));
            roomInfoWriter.write(sj.toString());
            roomInfoWriter.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter("Resident_Info.txt", true));

            if (count != 0) {
                bw.newLine();
            }
            bw.write(line);
            bw.close();
            JOptionPane.showMessageDialog(null, "Resident added successfully");

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
