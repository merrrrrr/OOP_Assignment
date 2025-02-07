/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


/**
 *
 * @author Yong Jun
 */

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;




public class StaffMenuPage extends javax.swing.JFrame {

    /**
     * Creates new form StaffNewPage
     */
    private Staff staff;

    public StaffMenuPage() {
        initComponents();
        populateResidentPaymentTable();
        populateGenerateReceiptTable();
        populateRoomChangeData();
        setupGenerateReceiptTable();
        setLocationRelativeTo(null);
    }

    public StaffMenuPage(Staff staff) {
        this.staff = staff;
        initComponents();
        populateResidentPaymentTable();
        populateGenerateReceiptTable();
        populateRoomChangeData();
        setupGenerateReceiptTable();
        setLocationRelativeTo(null);
    }

    public void editProfile() throws IOException {
        String[] myInfo = new String[6];
        myInfo[0] = staff.getId();
        myInfo[1] = staff.getUsername();
        myInfo[2] = staff.getPassword();
        myInfo[3] = staff.getName();
        myInfo[4] = staff.getContactNumber();
        myInfo[5] = staff.getEmail();

        int attribute = JOptionPane.showOptionDialog(null, "Please select attribute you want to edit.", "Edit Profile", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Username", "Password", "Name", "Contact Number", "Email Address"}, "Username");
        if (attribute == JOptionPane.CLOSED_OPTION) {
            return;
        } else if (attribute == 0) {
            String newUsername = JOptionPane.showInputDialog(null, "Please enter new username: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newUsername == null) {
                return;
            } else if (newUsername.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!staff.isUsernameUnique(newUsername)) {
                JOptionPane.showMessageDialog(null, "This username already exists. Please enter other username.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[1] = newUsername;
            staff.setUsername(newUsername);
            UsernameField.setText(newUsername);

        } else if (attribute == 1) {
            String newPassword = JOptionPane.showInputDialog(null, "Please enter new password: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newPassword == null) {
                return;
            } else if (newPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!staff.validatePassword(newPassword)) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit and one special character.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newConfirmPassword = JOptionPane.showInputDialog(null, "Please re-enter new password: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newPassword.equals(newConfirmPassword)) {
                myInfo[2] = newPassword;
                staff.setPassword(newPassword);
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
            } else if (!staff.validateName(newName)) {
                JOptionPane.showMessageDialog(null, "Name must only contain alphabets.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[3] = newName;
            staff.setName(newName);
            NameField.setText(newName);

        } else if (attribute == 3) {
            String newContact = JOptionPane.showInputDialog(null, "Please enter new contact number: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newContact == null) {
                return;
            } else if (newContact.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Contact number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!staff.validateContactNumber(newContact)) {
                JOptionPane.showMessageDialog(null, "Contact number must between 9 and 11 digits number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!staff.isContactNumberUnique(newContact)) {
                JOptionPane.showMessageDialog(null, "This contact number already exists. Please enter other contact number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            myInfo[4] = newContact;
            staff.setContactNumber(newContact);
            ContactField.setText(newContact);

        } else if (attribute == 4) {
            String newEmail = JOptionPane.showInputDialog(null, "Please enter new email address: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newEmail == null) {
                return;
            } else if (newEmail.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email address cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!staff.validateEmail(newEmail)) {
                JOptionPane.showMessageDialog(null, "Invalid email address format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!staff.isEmailUnique(newEmail)) {
                JOptionPane.showMessageDialog(null, "This email address already exists. Please enter other email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[5] = newEmail;
            staff.setEmail(newEmail);
            EmailField.setText(newEmail);
        }

        StringJoiner sj = new StringJoiner(",");

        for (int i = 0; i < myInfo.length; i++) {
            sj.add(myInfo[i]);
        }

        BufferedReader br = new BufferedReader(new FileReader("Staff_Info.txt"));
        String line;
        ArrayList staffInfo = new ArrayList();
        while ((line = br.readLine()) != null) {
            staffInfo.add(line);
        }

        for (int i = 0; i < staffInfo.size(); i++) {
            String[] parts = staffInfo.get(i).toString().split(",");
            if (parts[0].equals(staff.getId())) {
                staffInfo.set(i, sj.toString());
            }
        }

        sj = new StringJoiner(System.lineSeparator());

        for (int i = 0; i < staffInfo.size(); i++) {
            sj.add(staffInfo.get(i).toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("Staff_Info.txt"));
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

        StaffTab = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ResidentPaymentTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        SearchMakePaymentBox = new javax.swing.JTextField();
        confirmButtonMP = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        generateReceiptTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        GenerateReceiptSearchBox = new javax.swing.JTextField();
        confirmButtonGR = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        roomChangeRequestTextBox = new javax.swing.JTextField();
        confirmButtonRC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ManageRoomChangeTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        editUsernameBox = new javax.swing.JTextField();
        editPasswordBox = new javax.swing.JTextField();
        editNameBox = new javax.swing.JTextField();
        editContactNumberBox = new javax.swing.JTextField();
        editEmailAddressBox = new javax.swing.JTextField();
        editProfileButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        logOutButton = new javax.swing.JButton();
        ProfilePanel = new JPanel();
        UsernameLabel = new JLabel();
        PasswordLabel = new JLabel();
        NameLabel = new JLabel();
        ContactLabel = new JLabel();
        EmailLabel = new JLabel();
        StaffIDLabel = new JLabel();
        StaffIDField = new JTextField();
        UsernameField = new JTextField();
        PasswordField = new JPasswordField();
        NameField = new JTextField();
        ContactField = new JTextField();
        EmailField = new JTextField();
        EditProfileButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ResidentPaymentTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Resident ID","Username", "Room Number", "Room Type", "Overdue Amount", "Action"
                }
        ));
        jScrollPane2.setViewportView(ResidentPaymentTable);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Enter Name to Make Payment : ");

        SearchMakePaymentBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchMakePaymentBoxActionPerformed(evt);
            }
        });

        confirmButtonMP.setText("Confirm");
        confirmButtonMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonMPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jScrollPane2)
                                                .addContainerGap())
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(confirmButtonMP)
                                                        .addComponent(SearchMakePaymentBox, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 106, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(SearchMakePaymentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confirmButtonMP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142))
        );

        StaffTab.addTab("Make Payment For Resident", jPanel3);

        generateReceiptTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Receipt ID", "Date of Issue","Resident ID","Resident Name","Room Number","Room Type","Amount Paid","Staff In Charge", "Action"
                }
        ));
        jScrollPane3.setViewportView(generateReceiptTable);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Enter Name to Generate Receipt : ");

        GenerateReceiptSearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateReceiptSearchBoxActionPerformed(evt);
            }
        });

        confirmButtonGR.setText("Confirm");
        confirmButtonGR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonGRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jScrollPane3)
                                                .addContainerGap())
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(confirmButtonGR)
                                                        .addComponent(GenerateReceiptSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 106, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(GenerateReceiptSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confirmButtonGR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))
        );

        StaffTab.addTab("Generate Receipt", jPanel4);

        confirmButtonRC.setText("Confirm");
        confirmButtonRC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonRCActionPerformed(evt);
            }
        });

        ManageRoomChangeTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Request ID", "Resident ID", "Resident Name", "gender", "Current Room Number", "Current Room Type", "Requested Room Type", "Description","Action","Status","New Room Number"
                }
        ));
        jScrollPane1.setViewportView(ManageRoomChangeTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Enter the number of the request to approve/disapprove: ");

        roomChangeRequestTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterManageRoomChangeTable();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(roomChangeRequestTextBox)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(confirmButtonRC)
                                                                .addGap(0, 190, Short.MAX_VALUE)))))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(roomChangeRequestTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confirmButtonRC)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(139, Short.MAX_VALUE))
        );

        StaffTab.addTab("Manage Resident Room Change", jPanel1);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Modify Personal Information");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Username : ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Password : ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Name : ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Contact Number :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Email Address : ");

        editEmailAddressBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEmailAddressBoxActionPerformed(evt);
            }
        });

        editProfileButton.setText("Edit Profile");
        editProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProfileButtonActionPerformed(evt);
            }
        });


        StaffIDLabel.setText("Staff ID");

        UsernameLabel.setText("Username");

        PasswordLabel.setText("Password");

        NameLabel.setText("Name");

        ContactLabel.setText("Contact Number");

        EmailLabel.setText("Email Address");

        StaffIDField.setEditable(false);
        StaffIDField.setText(staff.getId());

        UsernameField.setEditable(false);
        UsernameField.setText(staff.getUsername());


        PasswordField.setEditable(false);
        PasswordField.setText(staff.getPassword());

        NameField.setEditable(false);
        NameField.setText(staff.getName());

        ContactField.setEditable(false);
        ContactField.setText(staff.getContactNumber());

        EmailField.setEditable(false);
        EmailField.setText(staff.getEmail());

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
                                        .addComponent(StaffIDLabel)
                                        .addComponent(UsernameLabel)
                                        .addComponent(NameLabel)
                                        .addComponent(ContactLabel)
                                        .addComponent(PasswordLabel))
                                .addGap(45, 45, 45)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(StaffIDField)
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
                                        .addComponent(StaffIDLabel)
                                        .addComponent(StaffIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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

        StaffTab.addTab("Edit Profile", ProfilePanel);

        logOutButton.setText("Log Out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);

            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(340, Short.MAX_VALUE)
                                .addComponent(logOutButton)
                                .addContainerGap(340, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(342, Short.MAX_VALUE))
        );

        StaffTab.addTab("Log Out", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(StaffTab)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(StaffTab, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void EditProfileButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        editProfile();
    }

    private void GenerateReceiptSearchBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        filterGenerateReceiptTable();
    }


    private void SearchMakePaymentBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // Get user input from the search box
        filterResidentPaymentTable();
    }


    private void populateRoomChangeData() {
        DefaultTableModel model = (DefaultTableModel) ManageRoomChangeTable.getModel();
        model.setRowCount(0); // Clear existing rows

        try (BufferedReader reader = new BufferedReader(new FileReader("Change_Room.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] details = line.split(",");
                if (details.length >= 10) { // Ensure there are at least 10 elements
                    model.addRow(new Object[]{details[0], details[1], details[2], details[3], details[4], details[5], details[6], details[7], "", details[8], details[9]});
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        ManageRoomChangeTable.setRowHeight(60); // Set the row height to 60 pixels
        ManageRoomChangeTable.getColumn("Action").setCellRenderer(new ActionButtonRenderer());
        ManageRoomChangeTable.getColumn("Action").setCellEditor(new ActionButtonEditor(new JCheckBox()));
    }

    private void editEmailAddressBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            MainPage mp = new MainPage();
            mp.setVisible(true);
            this.dispose();
        }
    }
    private void confirmButtonMPActionPerformed(java.awt.event.ActionEvent evt) {
        // Get user input from the search box
        filterResidentPaymentTable();
    }

    private void confirmButtonGRActionPerformed(java.awt.event.ActionEvent evt) {
        // Get user input from the search box
        filterGenerateReceiptTable();
    }

    private void confirmButtonRCActionPerformed(java.awt.event.ActionEvent evt) {
        filterManageRoomChangeTable();
    }

    // Method to handle the edit profile button action
    private void editProfileButtonActionPerformed(ActionEvent evt) {
        String newUsername = editUsernameBox.getText().trim();
        String newPassword = editPasswordBox.getText().trim();
        String newName = editNameBox.getText().trim();
        String newContactNumber = editContactNumberBox.getText().trim();
        String newEmailAddress = editEmailAddressBox.getText().trim();

        updateStaffInfo(newUsername, newPassword, newName, newContactNumber, newEmailAddress);
    }

    // Method to update the staff information in the Staff_Info.txt file
    private void updateStaffInfo(String newUsername, String newPassword, String newName, String newContactNumber, String newEmailAddress) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Staff_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Info.txt"))) {
            for (String line : lines) {
                String[] details = line.split(",");
                if (details[1].equalsIgnoreCase(staff.getUsername())) {
                    if (!newUsername.isEmpty()) {
                        details[1] = newUsername;
                        staff.setUsername(newUsername);
                    }
                    if (!newPassword.isEmpty()) {
                        details[2] = newPassword;
                        staff.setPassword(newPassword);
                    }
                    if (!newName.isEmpty()) {
                        details[3] = newName;
                        staff.setName(newName);
                    }
                    if (!newContactNumber.isEmpty()) {
                        details[4] = newContactNumber;
                        staff.setContactNumber(newContactNumber);
                    }
                    if (!newEmailAddress.isEmpty()) {
                        details[5] = newEmailAddress;
                        staff.setEmail(newEmailAddress);
                    }
                    line = String.join(",", details);
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

        JOptionPane.showMessageDialog(this, "Profile updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void filterGenerateReceiptTable() {
        String searchTerm = GenerateReceiptSearchBox.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) generateReceiptTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        generateReceiptTable.setRowSorter(sorter);

        if (searchTerm.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm));
        }
    }

    private void filterResidentPaymentTable() {
        String searchTerm = SearchMakePaymentBox.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) ResidentPaymentTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        ResidentPaymentTable.setRowSorter(sorter);

        if (searchTerm.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm));
        }
    }

    private void filterManageRoomChangeTable() {
        String searchTerm = roomChangeRequestTextBox.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) ManageRoomChangeTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        ManageRoomChangeTable.setRowSorter(sorter);

        if (searchTerm.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm));
        }
    }



    private void searchResidentInfo(String searchName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        DefaultTableModel model = (DefaultTableModel) ResidentPaymentTable.getModel();
        model.setRowCount(0); // Clear existing rows

        boolean found = false;
        for (String line : lines) {
            String[] details = line.split(",");
            if (details[1].equalsIgnoreCase(searchName)) {
                found = true;
                // Display the resident's information in the ResidentPaymentTable
                model.addRow(new Object[]{details[1], details[7], details[8], "Make Payment"});
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Resident not found.");
        }
    }

    //adjust resident payment table's row size and refresh gui
    private void populateResidentPaymentTable() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        DefaultTableModel model = (DefaultTableModel) ResidentPaymentTable.getModel();
        model.setRowCount(0); // Clear existing rows

        for (String line : lines) {
            String[] details = line.split(",");
            model.addRow(new Object[]{details[0], details[1], details[7], details[8], details[9], "Make Payment"});
        }

        ResidentPaymentTable.setRowHeight(30); // Set the row height to 30 pixels
        ResidentPaymentTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        ResidentPaymentTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    private String generateUniquePaymentID() {
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String lastID = "P" + currentDate + "0000";
        try (BufferedReader reader = new BufferedReader(new FileReader("Payment_Records.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length > 0 && !details[0].isEmpty()) {
                    lastID = details[0];
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Payment_Records.txt: " + e.getMessage());
        }

        String datePart = lastID.substring(1, 9);
        int idNumber = Integer.parseInt(lastID.substring(9)) + 1;
        if (!datePart.equals(currentDate)) {
            datePart = currentDate;
            idNumber = 1;
        }
        return String.format("P%s%04d", datePart, idNumber);
    }


    private void addPaymentRecord(String residentID, String residentName, String roomNumber, String roomType, double amountPaid) {
        String paymentID = generateUniquePaymentID();
        String paymentDatetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String record = String.join(",", paymentID, residentID, residentName, roomNumber, roomType, String.valueOf(amountPaid), paymentDatetime);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Payment_Records.txt", true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to Payment_Records.txt: " + e.getMessage());
        }
    }


    //adjust generate receipt table's row size and refresh gui
    private void populateGenerateReceiptTable() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Receipt.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        DefaultTableModel model = (DefaultTableModel) generateReceiptTable.getModel();
        model.setRowCount(0); // Clear existing rows

        for (String line : lines) {
            String[] details = line.split(",");
            model.addRow(new Object[]{details[0], details[1], details[2], details[3], details[4],details[5],details[6],details[7], "Display"});
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Make Payment" : value.toString());
            return this;
        }
    }

    // Custom cell renderer for the "Action" column
    class ActionButtonRenderer extends JPanel implements TableCellRenderer {
        private JButton approveButton;
        private JButton rejectButton;
        private JLabel completedLabel;

        public ActionButtonRenderer() {
            setOpaque(true);
            setLayout(new java.awt.GridLayout(2, 1)); // Change to vertical layout
            approveButton = new JButton("Approve");
            rejectButton = new JButton("Reject");
            completedLabel = new JLabel("Completed", SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            String status = (String) table.getValueAt(row, 9); // Assuming status is in the 10th column (index 9)
            removeAll();
            if ("approved".equalsIgnoreCase(status) || "rejected".equalsIgnoreCase(status)) {
                add(completedLabel, new GridBagConstraints()); // Center the label
            } else {
                add(approveButton);
                add(rejectButton);
            }
            return this;
        }
    }

    // Custom cell editor for the "Action" column
    class ActionButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton approveButton;
        private JButton rejectButton;
        private JLabel completedLabel;
        private int currentRow;

        public ActionButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel(new java.awt.GridLayout(2, 1)); // Change to vertical layout
            approveButton = new JButton("Approve");
            rejectButton = new JButton("Reject");
            completedLabel = new JLabel("Completed", SwingConstants.CENTER);
            panel.add(approveButton);
            panel.add(rejectButton);

            approveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleApproveButtonClick(currentRow);
                    fireEditingStopped();
                }
            });

            rejectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleRejectButtonClick(currentRow);
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentRow = row;
            String status = (String) table.getValueAt(row, 9); // Assuming status is in the 10th column (index 9)
            panel.removeAll();
            if ("approved".equalsIgnoreCase(status) || "rejected".equalsIgnoreCase(status)) {
                panel.add(completedLabel);
            } else {
                panel.add(approveButton);
                panel.add(rejectButton);
            }
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }

        @Override
        public boolean stopCellEditing() {
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    //Make Payment Button in Make Payment Table
    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Make Payment" : value.toString();
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    String overdueAmount = (String) table.getValueAt(row, 4); // Assuming overdue amount is in the 5th column (index 4)
                    String residentID = (String) table.getValueAt(row, 0);
                    String residentName = (String) table.getValueAt(row, 1);
                    String roomNumber = (String) table.getValueAt(row, 2);
                    String roomType = (String) table.getValueAt(row, 3);
                    if ("RM00.00".equals(overdueAmount)) {
                        JOptionPane.showMessageDialog(null, residentName + " has no overdue amount.");
                    } else {
                        int response = JOptionPane.showConfirmDialog(null,
                                "Are you sure to make payment for " + residentName + "?",
                                "Confirm Payment",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            updateOverdueAmount(row);
                            addPaymentRecord(residentID, residentName, roomNumber, roomType, Double.parseDouble(overdueAmount.substring(2)));
                            JOptionPane.showMessageDialog(null, "Payment made successfully for " + residentName + ".");
                        }
                    }
                }
            });
            return button;
        }

        public Object getCellEditorValue() {
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }



    class GenerateButtonRenderer extends JButton implements TableCellRenderer {
        public GenerateButtonRenderer() {
            setOpaque(true);
            setText("Display");
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class GenerateButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isPushed;

        public GenerateButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label = "Display";
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    String receiptID = (String) table.getValueAt(row, 0);
                    String dateOfIssue = (String) table.getValueAt(row, 1);
                    String residentName = (String) table.getValueAt(row, 3);
                    String roomNumber = (String) table.getValueAt(row, 4);
                    String roomType = (String) table.getValueAt(row, 5);
                    String amountPaid = (String) table.getValueAt(row, 6);
                    String staffInCharge = (String) table.getValueAt(row, 7);
                    JOptionPane.showMessageDialog(null, "Receipt ID: " + receiptID + "\nDate of Issue: " + dateOfIssue +
                            "\nResident Name: " + residentName + "\nRoom Number: " + roomNumber +
                            "\nRoom Type: " + roomType + "\nAmount Paid: " + amountPaid +
                            "\nStaff in Charge: " + staffInCharge);
                }
            });
            return button;
        }

        public Object getCellEditorValue() {
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    //adjust generate receipt table's row size
    private void setupGenerateReceiptTable() {


        TableColumnModel columnModel = generateReceiptTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100); // Receipt ID
        columnModel.getColumn(1).setPreferredWidth(150); // Date of Issue
        columnModel.getColumn(2).setPreferredWidth(150); // Resident ID
        columnModel.getColumn(3).setPreferredWidth(200); // Resident Name
        columnModel.getColumn(4).setPreferredWidth(150); // Room Number
        columnModel.getColumn(5).setPreferredWidth(150); // Room Type
        columnModel.getColumn(6).setPreferredWidth(120); // Amount Paid
        columnModel.getColumn(7).setPreferredWidth(170); // Staff In Charge
        columnModel.getColumn(8).setPreferredWidth(150); // Action

        generateReceiptTable.setRowHeight(30); // Set the row height to 30 pixels
        generateReceiptTable.getColumn("Action").setCellRenderer(new GenerateButtonRenderer());
        generateReceiptTable.getColumn("Action").setCellEditor(new GenerateButtonEditor(new JCheckBox()));
    }


    private void updateOverdueAmount(int row) {
        if (staff == null) {
            System.err.println("Staff is not initialized.");
            return;
        }

        String residentID = (String) ResidentPaymentTable.getValueAt(row, 0);
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resident_Info.txt"))) {
            for (String line : lines) {
                String[] details = line.split(",");
                if (details[0].equalsIgnoreCase(residentID)) {
                    details[9] = "RM00.00"; // Assuming the overdue amount is in the 10th column (index 9)
                    line = String.join(",", details);
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

        generateReceipt(row, staff.getUsername());
        populateResidentPaymentTable();
    }

    // auto generate receipt after clicked make payment button
    private void generateReceipt(int row, String staffInCharge) {
        String receiptID = generateUniqueReceiptID();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String residentID = (String) ResidentPaymentTable.getValueAt(row, 0);
        String residentName = (String) ResidentPaymentTable.getValueAt(row, 1);
        String roomNumber = (String) ResidentPaymentTable.getValueAt(row, 2);
        String roomType = (String) ResidentPaymentTable.getValueAt(row, 3);
        String amountPaid = (String) ResidentPaymentTable.getValueAt(row, 4);

        // Create receipt details
        String receipt = String.join(",", receiptID, dateTime, residentID, residentName, roomNumber, roomType, amountPaid, staffInCharge);

        // Write receipt details to Receipt.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Receipt.txt", true))) {
            writer.write(receipt);
            writer.newLine(); // Add a new line after writing the receipt
        } catch (IOException e) {
            System.err.println("Error writing to Receipt.txt: " + e.getMessage());
        }

        // Refresh the generateReceiptTable
        populateGenerateReceiptTable();
    }

    private String generateUniqueReceiptID() {
        String lastID = "P" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "0000";
        try (BufferedReader reader = new BufferedReader(new FileReader("Receipt.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length > 0 && !details[0].isEmpty()) {
                    lastID = details[0];
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Receipt.txt: " + e.getMessage());
        }

        if (lastID.length() >= 9) {
            String datePart = lastID.substring(1, 9); // Extract the date part
            int idNumber = Integer.parseInt(lastID.substring(9)) + 1; // Increment the ID number
            return String.format("P%s%04d", datePart, idNumber);
        } else {
            // Handle the case where lastID is not in the expected format
            return "P" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "0001";
        }
    }

    // Method to handle the "Approve" button click
    private void handleApproveButtonClick(int row) {
        String residentID = (String) ManageRoomChangeTable.getValueAt(row, 1);
        String residentName = (String) ManageRoomChangeTable.getValueAt(row, 2);
        String gender = (String) ManageRoomChangeTable.getValueAt(row, 3);
        String currentRoomNumber = (String) ManageRoomChangeTable.getValueAt(row, 4);
        String newRoomType = (String) ManageRoomChangeTable.getValueAt(row, 6);

        List<String> availableRooms = getAvailableRooms(newRoomType, gender);
        if (availableRooms.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No available rooms for the selected type and gender.");
            return;
        }

        String selectedRoom = (String) JOptionPane.showInputDialog(this, "Select a room:", "Available Rooms",
                JOptionPane.QUESTION_MESSAGE, null, availableRooms.toArray(), availableRooms.get(0));

        if (selectedRoom != null) {
            updateRoomInfo(currentRoomNumber, selectedRoom);
            updateChangeRoomStatus(row, "approved");
            updateResidentInfo(residentID, selectedRoom, newRoomType);
            ManageRoomChangeTable.setValueAt(selectedRoom, row, 10); // Update the "New Room" column

            // Update the Change_Room.txt file
            try {
                List<String> lines = new ArrayList<>();
                try (BufferedReader reader = new BufferedReader(new FileReader("Change_Room.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                }

                String[] details = lines.get(row).split(",");
                details[9] = selectedRoom; // Update the new room number
                lines.set(row, String.join(",", details));

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Change_Room.txt"))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error updating the file: " + e.getMessage());
            }

            populateRoomChangeData();
        }
    }

    // Method to update the resident info in Resident_Info.txt
    private void updateResidentInfo(String residentID, String newRoomNumber, String newRoomType) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resident_Info.txt"))) {
            for (String line : lines) {
                String[] details = line.split(",");
                if (details[0].equalsIgnoreCase(residentID)) {
                    details[7] = newRoomNumber; // Update room number
                    details[8] = newRoomType;   // Update room type
                    line = String.join(",", details);
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    // Method to handle the "Reject" button click
    private void handleRejectButtonClick(int row) {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to reject this request?", "Confirm Rejection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            updateChangeRoomStatus(row, "rejected");
            ManageRoomChangeTable.setValueAt("-", row, 10); // Update the "New Room" column
            populateRoomChangeData(); // Repopulate the table
        }
    }

    // Method to get available rooms based on room type and gender
    private List<String> getAvailableRooms(String roomType, String gender) {
        List<String> availableRooms = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Room_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String roomNumber = details[0];
                String type = details[1];
                int availability = Integer.parseInt(details[2]);
                String roomGender = details[3];

                if (type.equalsIgnoreCase(roomType) && roomGender.equalsIgnoreCase(gender)) {
                    if ((roomNumber.startsWith("FS") && availability == 1) ||
                            (roomNumber.startsWith("MS") && availability == 1) ||
                            (roomNumber.startsWith("FD") && (availability == 1 || availability == 2)) ||
                            (roomNumber.startsWith("MD") && (availability == 1 || availability == 2)) ||
                            (roomNumber.startsWith("FT") && (availability == 1 || availability == 2 || availability == 3)) ||
                            (roomNumber.startsWith("MT") && (availability == 1 || availability == 2 || availability == 3))) {
                        availableRooms.add(roomNumber);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return availableRooms;
    }

    // Method to update the status in Change_Room.txt
    private void updateChangeRoomStatus(int row, String status) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Change_Room.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Change_Room.txt"))) {
            for (int i = 0; i < lines.size(); i++) {
                String[] details = lines.get(i).split(",");
                if (i == row) {
                    if (details.length >= 9) {
                        details[8] = status;
                    } else {
                        // Ensure the array has at least 9 elements
                        String[] newDetails = new String[9];
                        System.arraycopy(details, 0, newDetails, 0, details.length);
                        newDetails[8] = status;
                        details = new String[9];
                        System.arraycopy(newDetails, 0, details, 0, 9);
                    }
                    lines.set(i, String.join(",", details).replaceAll(",null", ""));
                }
                writer.write(lines.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

        // Update the table model to reflect the new status
        DefaultTableModel model = (DefaultTableModel) ManageRoomChangeTable.getModel();
        model.setValueAt(status, row, 8); // Assuming status is in the 9th column (index 8)
        populateRoomChangeData(); // Repopulate the table
    }

    // Method to update the room info in Room_Info.txt
    private void updateRoomInfo(String oldRoomNumber, String newRoomNumber) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("room_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("room_info.txt"))) {
            for (String line : lines) {
                String[] details = line.split(",");

                if (details[0].equalsIgnoreCase(oldRoomNumber)) {
                    int availability = Integer.parseInt(details[2]);
                    details[2] = String.valueOf(availability + 1); // Increase availability
                    line = String.join(",", details);
                } else if (details[0].equalsIgnoreCase(newRoomNumber)) {
                    int availability = Integer.parseInt(details[2]);
                    details[2] = String.valueOf(availability - 1); // Decrease availability
                    line = String.join(",", details);
                }

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
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
            java.util.logging.Logger.getLogger(StaffMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffMenuPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JTextField GenerateReceiptSearchBox;
    private javax.swing.JTable ManageRoomChangeTable;
    private javax.swing.JRadioButton RejectRadioButton;
    private javax.swing.JTable ResidentPaymentTable;
    private javax.swing.JTextField SearchMakePaymentBox;
    private javax.swing.JTabbedPane StaffTab;
    private javax.swing.JRadioButton approveRadioButton;
    private javax.swing.JButton confirmButtonGR;
    private javax.swing.JButton confirmButtonMP;
    private javax.swing.JButton confirmButtonRC;
    private javax.swing.JTextField editContactNumberBox;
    private javax.swing.JTextField editEmailAddressBox;
    private javax.swing.JTextField editNameBox;
    private javax.swing.JTextField editPasswordBox;
    private javax.swing.JButton editProfileButton;
    private javax.swing.JTextField editUsernameBox;
    private javax.swing.JTable generateReceiptTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton logOutButton;
    private javax.swing.JTextField roomChangeRequestTextBox;
    private JTextField ContactField;
    private JLabel ContactLabel;
    private JTextField EmailField;
    private JLabel EmailLabel;
    private JButton EditProfileButton;
    private JTextField UsernameField;
    private JLabel UsernameLabel;
    private JTextField NameField;
    private JLabel NameLabel;
    private JPasswordField PasswordField;
    private JLabel PasswordLabel;
    private JPanel ProfilePanel;
    private JTextField StaffIDField;
    private JLabel StaffIDLabel;

    // End of variables declaration
}
