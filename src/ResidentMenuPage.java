/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

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
    }

    public ResidentMenuPage(Resident resident) {
        this.resident = resident;
        initComponents();
        loadPaymentRecords();
    }

    public boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return name.matches("[a-zA-Z]+");
    }

    public boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (!Character.isLetterOrDigit(c) && c != ',') {
                hasSpecialChar = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasSpecialChar;
    }

    public boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return email != null && p.matcher(email).matches();
    }

    public boolean validateContactNumber(String contactNumber) {
        if (contactNumber.length() >= 9 && contactNumber.length() <= 11) {
            for (char c : contactNumber.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public boolean isUsernameUnique(String username, String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[1];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();
        return true;
    }

    public boolean isEmailUnique(String email, String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[5]; // Assuming the email is the 5th element in the CSV
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();
        return true;
    }

    public boolean isContactNumberUnique(String contactNumber, String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[4]; // Assuming the contact number is the 4th element in the CSV
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();
        return true;
    }

    public void editProfile() throws IOException {
        String[] myInfo = new String[6];
        myInfo[0] = resident.getId();
        myInfo[1] = resident.getUsername();
        myInfo[2] = resident.getPassword();
        myInfo[3] = resident.getName();
        myInfo[4] = resident.getContactNumber();
        myInfo[5] = resident.getEmail();
        myInfo[6] = resident.getGender();
        myInfo[7] = resident.getRoomNo();
        myInfo[8] = resident.getRoomType();
        myInfo[9] = resident.getOverdueAmount();

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
            } else if (isUsernameUnique(newUsername, "Resident_Info.txt") == false) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[1] = newUsername;

        } else if (attribute == 1) {
            String newPassword = JOptionPane.showInputDialog(null, "Please enter new password: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newPassword == null) {
                return;
            } else if (newPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (validatePassword(newPassword) == false) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit and one special character.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String newConfirmPassword = JOptionPane.showInputDialog(null, "Please re-enter new password: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newPassword.equals(newConfirmPassword)) {
                myInfo[2] = newPassword;
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
            } else if (validateName(newName) == false) {
                JOptionPane.showMessageDialog(null, "Name must only contain alphabets.", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            myInfo[3] = newName;

        } else if (attribute == 3) {
            String newContact = JOptionPane.showInputDialog(null, "Please enter new contact number: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newContact == null) {
                return;
            } else if (newContact.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Contact number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (validateContactNumber(newContact) == false) {
                JOptionPane.showMessageDialog(null, "Contact number must between 9 and 11 digits number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (isContactNumberUnique(newContact, "Resident_Info.txt") == false) {
                JOptionPane.showMessageDialog(null, "Contact number already exists. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[4] = newContact;

        } else if (attribute == 4) {
            String newEmail = JOptionPane.showInputDialog(null, "Please enter new email address: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newEmail == null) {
                return;
            } else if (newEmail.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email address cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (validateEmail(newEmail) == false) {
                JOptionPane.showMessageDialog(null, "Invalid email address format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (isEmailUnique(newEmail, "Resident_Info.txt") == false) {
                JOptionPane.showMessageDialog(null, "Email address already exists. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[5] = newEmail;
        }

        StringJoiner sj = new StringJoiner(",");

        for (int i = 0; i < myInfo.length; i++) {
            sj.add(myInfo[i]);
        }

        BufferedReader br = new BufferedReader(new FileReader("Resident_Info.txt"));
        String line;
        ArrayList managerInfo = new ArrayList();
        while ((line = br.readLine()) != null) {
            managerInfo.add(line);
        }

        for (int i = 0; i < managerInfo.size(); i++) {
            String[] parts = managerInfo.get(i).toString().split(",");
            if (parts[0].equals(resident.getId())) {
                managerInfo.set(i, sj.toString());
            }
        }

        sj = new StringJoiner(System.lineSeparator());

        for (int i = 0; i < managerInfo.size(); i++) {
            sj.add(managerInfo.get(i).toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("Manager_Info.txt"));
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
        viewOverdueAmountButton = new javax.swing.JButton();
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
                                .addComponent(submitButton)
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

        viewOverdueAmountButton.setText("View Overdue Amount");
        viewOverdueAmountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewOverdueAmountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(68, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(viewReceiptButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(viewOverdueAmountButton))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(confirmSearchButton)
                                                        .addComponent(SearchBoxPaymentPage, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(92, 92, 92))
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
                                        .addComponent(viewOverdueAmountButton))
                                .addContainerGap())
        );

        ResidentTab.addTab("Payment", jPanel3);

        editResidentUsernameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editResidentUsernameBoxActionPerformed(evt);
            }
        });

        editResidentEmailAddressBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editResidentEmailAddressBoxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Modify Personal Information");

        editResidentProfileButton.setText("Edit Profile");
        editResidentProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editResidentProfileButtonActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(123, 123, 123)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(40, 40, 40)
                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(editResidentUsernameBox)
                                                                        .addComponent(editResidentPasswordBox)
                                                                        .addComponent(editResidentNameBox)
                                                                        .addComponent(editResidentContactNumberBox)
                                                                        .addComponent(editResidentEmailAddressBox, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addGap(152, 152, 152)
                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(317, 317, 317)
                                                .addComponent(editResidentProfileButton)))
                                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap(32, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(45, 45, 45)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(editResidentUsernameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(editResidentPasswordBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(editResidentNameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(editResidentContactNumberBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(editResidentEmailAddressBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addComponent(editResidentProfileButton)
                                .addGap(44, 44, 44))
        );

        ResidentTab.addTab("Edit Profile", jPanel6);

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
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap(340, Short.MAX_VALUE)
                                .addComponent(ResidentLogOutButton)
                                .addGap(328, 328, 328))
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

    private void roomTypeDropdownMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void editResidentUsernameBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void editResidentEmailAddressBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void editResidentProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ResidentLogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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

    private void viewOverdueAmountButtonActionPerformed(ActionEvent evt) {
        String residentID = resident.getId();
        String overdueAmount = "";

        try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 10 && details[0].equals(residentID)) {
                    overdueAmount = details[9];
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Resident_Info.txt: " + e.getMessage());
            return;
        }

        if ("RM00.00".equals(overdueAmount)) {
            JOptionPane.showMessageDialog(this, "You have no overdue amount for now.", "Overdue Amount", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Your overdue amount is: " + overdueAmount, "Overdue Amount", JOptionPane.INFORMATION_MESSAGE);
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Change_Room.txt", true))) {
            writer.write(String.join(",", requestID, residentID, residentUsername, residentGender, currentRoomNumber, currentRoomType, newRoomType, changeReason, "pending"));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to Change_Room.txt: " + e.getMessage());
            return;
        }

        // Show a popup message indicating the request has been submitted
        javax.swing.JOptionPane.showMessageDialog(this, "Room change request submitted successfully.", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
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
    private javax.swing.JButton viewOverdueAmountButton;
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
    // End of variables declaration
}
