/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author Yong Jun
 */
public class ResidentMenuPage extends javax.swing.JFrame {

    private Resident resident;

    /**
     * Creates new form ResidentMenuPage
     */
    public ResidentMenuPage() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public ResidentMenuPage(Resident resident) {
        this.resident = resident;
        initComponents();
        loadPaymentRecords();
        setLocationRelativeTo(null);
    }



    public void editProfile() throws IOException {
        String[] myInfo = new String[11];
        myInfo[0] = resident.getId();
        myInfo[1] = resident.getUsername();
        myInfo[2] = resident.getPassword();
        myInfo[3] = resident.getName();
        myInfo[4] = resident.getContactNumber();
        myInfo[5] = resident.getEmail();
        myInfo[6] = resident.getGender();
        myInfo[7] = resident.getRoomNo();
        myInfo[8] = resident.getRoomType();
        myInfo[9] = resident.getPayableAmount();

        int attribute = JOptionPane.showOptionDialog(null, "Please select attribute you want to edit.", "Edit Profile", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Username", "Password", "Name", "Contact Number", "Email Address"}, "Username");
        if (attribute == JOptionPane.CLOSED_OPTION) {
            return;
        } else if (attribute == 0) {
            String newUsername = JOptionPane.showInputDialog(null, "Please enter new username: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newUsername == null) {
                return;
            } if (newUsername.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!resident.isUsernameUnique(newUsername)) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[1] = newUsername;
            resident.setUsername(newUsername);
            UsernameField.setText(newUsername);

        } else if (attribute == 1) {
            String newPassword = JOptionPane.showInputDialog(null, "Please enter new password: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newPassword == null) {
                return;
            } else if (newPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!resident.validatePassword(newPassword)) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit and one special character.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String newConfirmPassword = JOptionPane.showInputDialog(null, "Please re-enter new password: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newPassword.equals(newConfirmPassword)) {
                myInfo[2] = newPassword;
                resident.setPassword(newPassword);
                PasswordField.setText(newPassword);
            } else {
                JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (attribute == 2) {
            String newName = JOptionPane.showInputDialog(null, "Please enter new name: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newName == null) {
                return;
            } else if (newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!resident.validateName(newName)) {
                JOptionPane.showMessageDialog(null, "Name must only contain alphabets.", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            myInfo[3] = newName;
            resident.setName(newName);
            NameField.setText(newName);

        } else if (attribute == 3) {
            String newContact = JOptionPane.showInputDialog(null, "Please enter new contact number: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newContact == null) {
                return;
            } else if (newContact.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Contact number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!resident.validateContactNumber(newContact)) {
                JOptionPane.showMessageDialog(null, "Contact number must between 9 and 11 digits number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!resident.isContactNumberUnique(newContact)) {
                JOptionPane.showMessageDialog(null, "Contact number already exists. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[4] = newContact;
            resident.setContactNumber(newContact);
            ContactField.setText(newContact);

        } else if (attribute == 4) {
            String newEmail = JOptionPane.showInputDialog(null, "Please enter new email address: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newEmail == null) {
                return;
            } else if (newEmail.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email address cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!resident.validateEmail(newEmail)) {
                JOptionPane.showMessageDialog(null, "Invalid email address format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!resident.isEmailUnique(newEmail)) {
                JOptionPane.showMessageDialog(null, "Email address already exists. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[5] = newEmail;
            resident.setEmail(newEmail);
            EmailField.setText(newEmail);
        }

        StringJoiner sj = new StringJoiner(",");

        for (int i = 0; i < myInfo.length; i++) {
            sj.add(myInfo[i]);
        }

        BufferedReader br = new BufferedReader(new FileReader("Resident_Info.txt"));
        String line;
        ArrayList residentInfo = new ArrayList();
        while ((line = br.readLine()) != null) {
            residentInfo.add(line);
        }

        for (int i = 0; i < residentInfo.size(); i++) {
            String[] parts = residentInfo.get(i).toString().split(",");
            if (parts[0].equals(resident.getId())) {
                residentInfo.set(i, sj.toString());
            }
        }

        sj = new StringJoiner(System.lineSeparator());

        for (int i = 0; i < residentInfo.size(); i++) {
            sj.add(residentInfo.get(i).toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("Resident_Info.txt"));
        bw.write(sj.toString());
        bw.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        ResidentTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        roomTypeDropdownMenu = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        changeReasonTextBox = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        paymentRecordsTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        SearchBoxPaymentPage = new javax.swing.JTextField();
        confirmSearchButton = new javax.swing.JButton();
        viewReceiptButton = new javax.swing.JButton();
        viewPayableAmountButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        editResidentUsernameBox = new javax.swing.JTextField();
        editResidentPasswordBox = new javax.swing.JTextField();
        editResidentNameBox = new javax.swing.JTextField();
        editResidentContactNumberBox = new javax.swing.JTextField();
        editResidentEmailAddressBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        editResidentProfileButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        ResidentLogOutButton = new javax.swing.JButton();
        ProfilePanel = new JPanel();
        UsernameLabel = new JLabel();
        PasswordLabel = new JLabel();
        NameLabel = new JLabel();
        ContactLabel = new JLabel();
        EmailLabel = new JLabel();
        ResidentIDLabel = new JLabel();
        ResidentIDField = new JTextField();
        UsernameField = new JTextField();
        PasswordField = new JPasswordField();
        NameField = new JTextField();
        ContactField = new JTextField();
        EmailField = new JTextField();
        EditProfileButton = new JButton();
        viewRequest = new javax.swing.JButton();


        viewRequest.setText("View Request");
        viewRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        roomTypeDropdownMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Room Type...", "Single Room", "Double Sharing Room", "Triple Sharing Room" }));
        roomTypeDropdownMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomTypeDropdownMenuActionPerformed(evt);
            }
        });

        jLabel3.setText("Enter Change Reason : ");

        submitButton.setText("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                handleSubmitButtonClick();
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Change Room Type Request");

        jLabel2.setText("Select Room Type :");

        changeReasonTextBox.setColumns(20);
        changeReasonTextBox.setRows(5);
        jScrollPane2.setViewportView(changeReasonTextBox);

        Dimension buttonSize = new Dimension(150, 40); // Adjust the height and width as needed
        viewRequest.setPreferredSize(buttonSize);
        submitButton.setPreferredSize(buttonSize);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(243, 243, 243)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(166, 166, 166)
                                                .addComponent(viewRequest)
                                                .addGap(290, 290, 290)
                                                .addComponent(submitButton))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(48, 48, 48)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                                                        .addComponent(roomTypeDropdownMenu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1)
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(roomTypeDropdownMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(viewRequest)
                                        .addComponent(submitButton))
                                .addGap(67, 67, 67))
        );

        ResidentTab.addTab("Change Room Type Request", jPanel1);

        paymentRecordsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Payment ID", "Resident Name", "Room Number", "Room Type", "Amount Paid", "Payment Datetime"
                }
        ));
        jScrollPane1.setViewportView(paymentRecordsTable);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Search Payment Records : ");

        confirmSearchButton.setText("Confirm");

        viewReceiptButton.setText("View Receipt");
        viewReceiptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewReceiptButtonActionPerformed(evt);
            }
        });

        viewPayableAmountButton.setText("View Payable Amount");
        viewPayableAmountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewPayableAmountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap(150, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(confirmSearchButton)
                                                        .addComponent(SearchBoxPaymentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(150, 150, 150))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(viewReceiptButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(viewPayableAmountButton)
                                                .addGap(150, 150, 150))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(150, 150, 150))))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(SearchBoxPaymentPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(confirmSearchButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(viewReceiptButton)
                                        .addComponent(viewPayableAmountButton))
                                .addContainerGap())
        );

        ResidentTab.addTab("Payment", jPanel3);

        SearchBoxPaymentPage.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchPaymentRecords();
                }
            }
        });

        confirmSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchPaymentRecords();
            }
        });

        ResidentIDLabel.setText("Resident ID");

        UsernameLabel.setText("Username");

        PasswordLabel.setText("Password");

        NameLabel.setText("Name");

        ContactLabel.setText("Contact Number");

        EmailLabel.setText("Email Address");


        ResidentIDField.setEditable(false);
        ResidentIDField.setText(resident.getId());

        UsernameField.setEditable(false);
        UsernameField.setText(resident.getUsername());

        PasswordField.setEditable(false);
        PasswordField.setText(resident.getPassword());

        NameField.setEditable(false);
        NameField.setText(resident.getName());

        ContactField.setEditable(false);
        ContactField.setText(resident.getContactNumber());

        EmailField.setEditable(false);
        EmailField.setText(resident.getEmail());

        EditProfileButton.setText("Edit Profile");
        EditProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    EditProfileButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        GroupLayout ProfilePanelLayout = new GroupLayout(ProfilePanel);
        ProfilePanel.setLayout(ProfilePanelLayout);
        ProfilePanelLayout.setHorizontalGroup(
                ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, ProfilePanelLayout.createSequentialGroup()
                                .addGap(290, 290, 290)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(EmailLabel)
                                        .addComponent(ResidentIDLabel)
                                        .addComponent(UsernameLabel)
                                        .addComponent(NameLabel)
                                        .addComponent(ContactLabel)
                                        .addComponent(PasswordLabel))
                                .addGap(45, 45, 45)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(ResidentIDField)
                                        .addComponent(UsernameField)
                                        .addComponent(PasswordField)
                                        .addComponent(NameField)
                                        .addComponent(ContactField)
                                        .addComponent(EmailField, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                                .addGap(285, 285, 285))
                        .addGroup(ProfilePanelLayout.createSequentialGroup()
                                .addGap(400, 400, 400)
                                .addComponent(EditProfileButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProfilePanelLayout.setVerticalGroup(
                ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(ProfilePanelLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(ResidentIDLabel)
                                        .addComponent(ResidentIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(UsernameLabel)
                                        .addComponent(UsernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(PasswordLabel)
                                        .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(NameLabel)
                                        .addComponent(NameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(ContactLabel)
                                        .addComponent(ContactField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(EmailLabel)
                                        .addComponent(EmailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addComponent(EditProfileButton)
                                .addContainerGap(103, Short.MAX_VALUE))
        );

        ResidentTab.addTab("Profile", ProfilePanel);


        ResidentLogOutButton.setText("Log Out");
        ResidentLogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResidentLogOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 340, Short.MAX_VALUE)
                                .addComponent(ResidentLogOutButton)
                                .addGap(0, 340, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(ResidentLogOutButton)
                                .addContainerGap(218, Short.MAX_VALUE))
        );

        ResidentTab.addTab("Log Out", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ResidentTab)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ResidentTab)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void EditProfileButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        editProfile();

    }
    private void roomTypeDropdownMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void editResidentUsernameBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void editResidentEmailAddressBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void viewRequestActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String residentID = resident.getId();
        java.util.List<String> requests = new java.util.ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Change_Room.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 9 && details[1].equals(residentID)) {
                    String request = "Old Room: " + details[4] + " \nNew Room: " + details[9] + " \nStatus: " + details[8];
                    requests.add(request);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Change_Room.txt: " + e.getMessage());
            return;
        }

        if (requests.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "No requests found.", "Info", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Create a dropdown menu with the requests
        String[] requestArray = requests.toArray(new String[0]);
        String selectedRequest = (String) javax.swing.JOptionPane.showInputDialog(this, "Select a request:", "View Request",
                javax.swing.JOptionPane.PLAIN_MESSAGE, null, requestArray, requestArray[0]);

        if (selectedRequest != null) {
            javax.swing.JOptionPane.showMessageDialog(this, selectedRequest, "Request Details", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void ResidentLogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            MainPage mp = new MainPage();
            mp.setVisible(true);
            this.dispose();
        }
    }

    private void viewReceiptButtonActionPerformed(ActionEvent evt) {
        List<String> receipts = new ArrayList<>();
        List<String> displayReceipts = new ArrayList<>();
        String residentID = resident.getId();

        try (BufferedReader reader = new BufferedReader(new FileReader("Receipt.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 8 && details[2].equals(residentID)) {
                    receipts.add(line);
                    displayReceipts.add(String.format("ReceiptID: %s, Date of Issue: %s", details[0], details[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Receipt.txt: " + e.getMessage());
            return;
        }

        if (receipts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No receipts found for the resident.", "No Receipts", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] displayArray = displayReceipts.toArray(new String[0]);
        String selectedDisplay = (String) JOptionPane.showInputDialog(this, "Select a receipt to view:", "View Receipt",
                JOptionPane.QUESTION_MESSAGE, null, displayArray, displayArray[0]);

        if (selectedDisplay != null) {
            int selectedIndex = displayReceipts.indexOf(selectedDisplay);
            String selectedReceipt = receipts.get(selectedIndex);
            String[] details = selectedReceipt.split(",");
            String receiptDetails = String.format(
                    "ReceiptID: %s\nDate of Issue: %s\nResident Name: %s\nRoom Number: %s\nRoom Type: %s\nStaff in Charge: %s",
                    details[0], details[1], details[3], details[4], details[5], details[7]);
            JOptionPane.showMessageDialog(this, receiptDetails, "Receipt Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewPayableAmountButtonActionPerformed(ActionEvent evt) {
        String residentID = resident.getId();
        String payableAmount = "";

        try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 10 && details[0].equals(residentID)) {
                    payableAmount = details[9];
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Resident_Info.txt: " + e.getMessage());
            return;
        }

        if ("RM00.00".equals(payableAmount)) {
            JOptionPane.showMessageDialog(this, "You have no payable amount for now.", "Payable Amount", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Your payable amount is: " + payableAmount, "Payable Amount", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void handleSubmitButtonClick() {
        String newRoomType = (String) roomTypeDropdownMenu.getSelectedItem();
        if ("Select Room Type...".equals(newRoomType)) {
            javax.swing.JOptionPane.showMessageDialog(this, "No room type selected. Please select a valid room type.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        String changeReason = changeReasonTextBox.getText().trim();
        if (changeReason.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter a change reason.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        String residentID = resident.getId();
        String residentUsername = resident.getUsername();
        String residentGender = resident.getGender();
        String currentRoomNumber = resident.getRoomNo();
        String currentRoomType = resident.getRoomType();

        // Generate a unique request ID
        String requestID = generateUniqueRequestID();

        // Add the new request to Change_Room.txt
        File file = new File("Change_Room.txt");
        boolean isEmpty = file.length() == 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (!isEmpty) {
                writer.newLine();
            }
            writer.write(String.join(",", requestID, residentID, residentUsername, residentGender, currentRoomNumber, currentRoomType, newRoomType, changeReason, "pending", "-"));
        } catch (IOException e) {
            System.err.println("Error writing to Change_Room.txt: " + e.getMessage());
        }

        // Show a popup message indicating the request has been submitted
        javax.swing.JOptionPane.showMessageDialog(this, "Room change request submitted successfully.", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        // Refresh the dropdown menu and text box
        roomTypeDropdownMenu.setSelectedIndex(0);
        changeReasonTextBox.setText("");
    }

    private String generateUniqueRequestID() {
        String lastID = "C000";
        try (BufferedReader reader = new BufferedReader(new FileReader("Change_Room.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length > 0) {
                    lastID = details[0];
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Change_Room.txt: " + e.getMessage());
        }

        if (lastID.length() > 1) {
            int idNumber = Integer.parseInt(lastID.substring(1)) + 1;
            return String.format("C%03d", idNumber);
        } else {
            return "C001";
        }
    }

    private void loadPaymentRecords() {
        DefaultTableModel model = (DefaultTableModel) paymentRecordsTable.getModel();
        model.setRowCount(0); // Clear existing rows

        try (BufferedReader reader = new BufferedReader(new FileReader("Payment_Records.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 7 && details[1].equals(resident.getId())) {
                    model.addRow(new Object[] {
                            details[0], // Payment ID
                            details[2], // Resident Name
                            details[3], // Room Number
                            details[4], // Room Type
                            details[5], // Amount Paid
                            details[6], // Payment Datetime
                    });
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Payment_Records.txt: " + e.getMessage());
        }
        paymentRecordsTable.setRowHeight(60);
    }

    private void searchPaymentRecords() {
        String searchText = SearchBoxPaymentPage.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) paymentRecordsTable.getModel();
        model.setRowCount(0); // Clear existing rows

        try (BufferedReader reader = new BufferedReader(new FileReader("Payment_Records.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 7 && details[1].equals(resident.getId())) {
                    boolean matches = false;
                    for (String detail : details) {
                        if (detail.toLowerCase().contains(searchText)) {
                            matches = true;
                            break;
                        }
                    }
                    if (matches) {
                        model.addRow(details);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Payment_Records.txt: " + e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(ResidentMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResidentMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResidentMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResidentMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResidentMenuPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton ResidentLogOutButton;
    private javax.swing.JTabbedPane ResidentTab;
    private javax.swing.JTextField SearchBoxPaymentPage;
    private javax.swing.JTextArea changeReasonTextBox;
    private javax.swing.JButton confirmSearchButton;
    private javax.swing.JTextField editResidentContactNumberBox;
    private javax.swing.JTextField editResidentEmailAddressBox;
    private javax.swing.JTextField editResidentNameBox;
    private javax.swing.JTextField editResidentPasswordBox;
    private javax.swing.JButton editResidentProfileButton;
    private javax.swing.JTextField editResidentUsernameBox;
    private javax.swing.JButton viewReceiptButton;
    private javax.swing.JButton viewPayableAmountButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable paymentRecordsTable;
    private javax.swing.JComboBox<String> roomTypeDropdownMenu;
    private javax.swing.JButton submitButton;
    private JTextField ContactField;
    private JLabel ContactLabel;
    private JButton EditProfileButton;
    private JTextField EmailField;
    private JLabel EmailLabel;
    private JTextField ResidentIDField;
    private JLabel ResidentIDLabel;
    private JTextField NameField;
    private JLabel NameLabel;
    private JPasswordField PasswordField;
    private JLabel PasswordLabel;
    private JTextField UsernameField;
    private JLabel UsernameLabel;
    private JPanel ProfilePanel;
    private JButton viewRequest;
    // End of variables declaration
}
