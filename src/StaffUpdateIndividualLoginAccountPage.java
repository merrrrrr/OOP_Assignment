import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffUpdateIndividualLoginAccountPage extends javax.swing.JFrame {
    private Staff staff;
    private boolean isLoggedIn = true; // Assuming the user is logged in for this example

    public StaffUpdateIndividualLoginAccountPage() {
        initComponents();
    }

    public StaffUpdateIndividualLoginAccountPage(Staff staff) {
        this.staff = staff;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        ModifyText = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        editPassword = new javax.swing.JPasswordField();
        editUsername = new javax.swing.JTextField();
        editName = new javax.swing.JTextField();
        editContactNum = new javax.swing.JTextField();
        editEmail = new javax.swing.JTextField();
        confirmEditButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ModifyText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ModifyText.setText("Modify Personal Information");

        jLabel1.setText("New Username: ");

        jLabel2.setText("New Password:");

        jLabel3.setText("New Name:");

        jLabel5.setText("New Contact Number:");

        jLabel6.setText("New Email:");

        editPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPasswordActionPerformed(evt);
            }
        });

        editUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUsernameActionPerformed(evt);
            }
        });

        editName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNameActionPerformed(evt);
            }
        });

        editContactNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editContactNumActionPerformed(evt);
            }
        });

        editEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEmailActionPerformed(evt);
            }
        });

        confirmEditButton.setText("Confirm Edit");
        confirmEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmEditButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(58, 58, 58)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(ModifyText)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(editPassword)
                                                                        .addComponent(editUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(editContactNum)
                                                                        .addComponent(editName)
                                                                        .addComponent(editEmail))
                                                                .addGap(29, 29, 29))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton)
                                .addGap(101, 101, 101)
                                .addComponent(confirmEditButton)
                                .addGap(141, 141, 141))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(ModifyText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(editUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(editPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(editName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(editEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel5)
                                                .addComponent(editContactNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(confirmEditButton)
                                        .addComponent(backButton))
                                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>

    private void confirmEditButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Get new inputs from text fields
        String newUsername = editUsername.getText().trim();
        String newPassword = new String(editPassword.getPassword()).trim();
        String newName = editName.getText().trim();
        String newContactNumber = editContactNum.getText().trim();
        String newEmail = editEmail.getText().trim();

        // Read the file and load the data into a list
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

        // Modify the details
        for (int i = 0; i < lines.size(); i++) {
            String[] details = lines.get(i).split(",");
            if (details[0].equals(staff.getId())) { // Assuming you want to modify the details for the logged-in staff
                details[1] = newUsername;
                details[2] = newPassword;
                details[3] = newName;
                details[4] = newContactNumber;
                details[5] = newEmail;
                lines.set(i, String.join(",", details));
                break;
            }
        }

        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Info.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }

        // Navigate back to the StaffMenuPage
        StaffMenuPage sp = new StaffMenuPage(staff);
        sp.setVisible(true);
        this.dispose();
    }



    private void editUsernameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String newUsername = editUsername.getText().trim();
        System.out.println("Updated Username: " + newUsername);
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Staff_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Modify the detail[1] (username)
        for (int i = 0; i < lines.size(); i++) {
            String[] details = lines.get(i).split(",");
            if (details[0].equals("S01")) { // Assuming you want to modify the username for S01
                details[1] = newUsername;
                lines.set(i, String.join(",", details));
                break;
            }
        }

        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Info.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        String newPassword = new String(editPassword.getPassword()).trim();
        System.out.println("Updated Password: " + newPassword);

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

        // Modify the detail[2] (password)
        for (int i = 0; i < lines.size(); i++) {
            String[] details = lines.get(i).split(",");
            if (details[0].equals("S01")) { // Assuming you want to modify the password for S01
                details[2] = newPassword;
                lines.set(i, String.join(",", details));
                break;
            }
        }

        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Info.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    private void editNameActionPerformed(java.awt.event.ActionEvent evt) {
        String newName = editName.getText().trim();
        System.out.println("Updated Name: " + newName);

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

        // Modify the detail[3] (name)
        for (int i = 0; i < lines.size(); i++) {
            String[] details = lines.get(i).split(",");
            if (details[0].equals("S01")) { // Assuming you want to modify the name for S01
                details[3] = newName;
                lines.set(i, String.join(",", details));
                break;
            }
        }

        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Info.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    private void editContactNumActionPerformed(java.awt.event.ActionEvent evt) {
        String newContactNumber = editContactNum.getText().trim();
        System.out.println("Updated Contact Number: " + newContactNumber);

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

        // Modify the detail[4] (contact number)
        for (int i = 0; i < lines.size(); i++) {
            String[] details = lines.get(i).split(",");
            if (details[0].equals("S01")) { // Assuming you want to modify the contact number for S01
                details[4] = newContactNumber;
                lines.set(i, String.join(",", details));
                break;
            }
        }

        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Info.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    private void editEmailActionPerformed(java.awt.event.ActionEvent evt) {
        String newEmail = editEmail.getText().trim();
        System.out.println("Updated Email: " + newEmail);

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

        // Modify the detail[5] (email)
        for (int i = 0; i < lines.size(); i++) {
            String[] details = lines.get(i).split(",");
            if (details[0].equals("S01")) { // Assuming you want to modify the email for S01
                details[5] = newEmail;
                lines.set(i, String.join(",", details));
                break;
            }
        }

        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Info.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        StaffMenuPage sp = new StaffMenuPage(staff);
        sp.setVisible(true);
        this.dispose();
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffUpdateIndividualLoginAccountPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel ModifyText;
    private javax.swing.JButton backButton;
    private javax.swing.JButton confirmEditButton;
    private javax.swing.JTextField editContactNum;
    private javax.swing.JTextField editEmail;
    private javax.swing.JTextField editName;
    private javax.swing.JPasswordField editPassword;
    private javax.swing.JTextField editUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration
}
