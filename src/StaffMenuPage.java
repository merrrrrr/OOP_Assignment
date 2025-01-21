public class StaffMenuPage extends javax.swing.JFrame {
    private Staff staff;

    public StaffMenuPage() {
        initComponents();
    }

    public StaffMenuPage(Staff staff) {
        this.staff = staff;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        StaffPageText = new javax.swing.JLabel();
        UpdateIndividualLoginAccount = new javax.swing.JButton();
        MakePaymentforResident = new javax.swing.JButton();
        generateReceipt = new javax.swing.JButton();
        AcceptRoomChange = new javax.swing.JButton();
        StaffLogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        StaffPageText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        StaffPageText.setText("Staff Page");

        UpdateIndividualLoginAccount.setText("Update Individual Login Account");
        UpdateIndividualLoginAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateIndividualLoginAccountActionPerformed(evt);
            }
        });

        MakePaymentforResident.setText("Make Payment for Resident");
        MakePaymentforResident.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MakePaymentforResidentActionPerformed(evt);
            }
        });

        generateReceipt.setText("Generate Receipt");
        generateReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReceiptActionPerformed(evt);
            }
        });

        AcceptRoomChange.setText("Accept Resident Room Type Change Request");
        AcceptRoomChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcceptRoomChangeActionPerformed(evt);
            }
        });

        StaffLogOut.setText("Log Out");
        StaffLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaffLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(generateReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(UpdateIndividualLoginAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(MakePaymentforResident, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(AcceptRoomChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(24, 24, 24))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(274, 274, 274)
                                                .addComponent(StaffLogOut))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(276, 276, 276)
                                                .addComponent(StaffPageText)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(StaffPageText)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(UpdateIndividualLoginAccount)
                                        .addComponent(MakePaymentforResident))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(AcceptRoomChange)
                                        .addComponent(generateReceipt))
                                .addGap(31, 31, 31)
                                .addComponent(StaffLogOut)
                                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void AcceptRoomChangeActionPerformed(java.awt.event.ActionEvent evt) {
        StaffAcceptResidentRoomTypeChangeRequestPage sa = new StaffAcceptResidentRoomTypeChangeRequestPage(staff);
        sa.setVisible(true);
        this.dispose();
    }

    private void generateReceiptActionPerformed(java.awt.event.ActionEvent evt) {
        StaffGenerateReceiptPage sg = new StaffGenerateReceiptPage(staff);
        sg.setVisible(true);
        this.dispose();
    }

    private void UpdateIndividualLoginAccountActionPerformed(java.awt.event.ActionEvent evt) {
        StaffUpdateIndividualLoginAccountPage su = new StaffUpdateIndividualLoginAccountPage(staff);
        su.setVisible(true);
        this.dispose();
    }

    private void StaffLogOutActionPerformed(java.awt.event.ActionEvent evt) {
        MainPage mp = new MainPage();
        mp.setVisible(true);
        this.dispose();
    }

    private void MakePaymentforResidentActionPerformed(java.awt.event.ActionEvent evt) {
        StaffMakePaymentForResidentPage sm = new StaffMakePaymentForResidentPage(this.staff);
        sm.setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffMenuPage().setVisible(true);
            }
        });
    }

    private javax.swing.JButton AcceptRoomChange;
    private javax.swing.JButton MakePaymentforResident;
    private javax.swing.JButton StaffLogOut;
    private javax.swing.JLabel StaffPageText;
    private javax.swing.JButton UpdateIndividualLoginAccount;
    private javax.swing.JButton generateReceipt;
}