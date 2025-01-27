import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Mervin Ooi
 */
public class ManagerMenuPage extends javax.swing.JFrame {

    /**
     * Creates new form ApproveUserPage
     */
    private Manager manager;

    public ManagerMenuPage() throws IOException {
        initComponents();
    }

    public ManagerMenuPage(Manager manager) throws IOException {
        this.manager = manager;
        initComponents();
    }

    public String[][] toUserInfoTable(int userType) throws IOException {
        String filename = manager.getInfoFilename(userType);
        BufferedReader br = new BufferedReader(new FileReader(filename));

        int lineCount = 0;
        while (br.readLine() != null) {
            lineCount++;
        }
        br.close();

        String[][] tableInfo = null;

        if (userType == 1 || userType == 2) {
            tableInfo = new String[lineCount][5];
        } else if (userType == 3) {
            tableInfo = new String[lineCount][8];
        }

        br = new BufferedReader(new FileReader(filename));
        String line;
        int i = 0;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            tableInfo[i][0] = parts[0]; // id
            tableInfo[i][1] = parts[1]; // username
            tableInfo[i][2] = parts[3]; // name
            tableInfo[i][3] = parts[4]; // phone
            tableInfo[i][4] = parts[5]; // email

            if (userType == 3) {
                tableInfo[i][5] = parts[6]; // gender
                tableInfo[i][6] = parts[7]; // room number
                tableInfo[i][7] = parts[8]; // overdue amount
            }

            i++;
        }
        return tableInfo;
    }

    public String[][] toRegistrationTable(int userType) throws IOException {
        String filename = manager.getRegisterFilename(userType);
        BufferedReader br = new BufferedReader(new FileReader(filename));

        int lineCount = 0;
        while (br.readLine() != null) {
            lineCount++;
        }
        br.close();

        String[][] tableInfo = null;

        if (userType == 1 || userType == 2) {
            tableInfo = new String[lineCount][5];
        } else if (userType == 3) {
            tableInfo = new String[lineCount][7];
        }

        br = new BufferedReader(new FileReader(filename));
        String line;
        int i = 0;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            tableInfo[i][0] = parts[1]; // username
            tableInfo[i][1] = parts[2]; // name
            tableInfo[i][2] = parts[3]; // phone
            tableInfo[i][3] = parts[4]; // email

            if (userType == 1 || userType == 2) {
                tableInfo[i][4] = parts[5]; // request date

            } else if (userType == 3) {
                tableInfo[i][4] = parts[5]; // Gender
                tableInfo[i][5] = parts[6]; // Room Type
                tableInfo[i][6] = parts[7]; // request date
            }
        }
        return tableInfo;
    }

    public String[][] toRoomInfoTable() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Room_Info.txt"));

        int lineCount = 0;
        while (br.readLine() != null) {
            lineCount++;
        }
        br.close();

        String[][] tableInfo = new String[lineCount][4];

        br = new BufferedReader(new FileReader("Room_Info.txt"));
        String line;
        int i = 0;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            tableInfo[i][0] = parts[0]; // room number
            tableInfo[i][1] = parts[1]; // room type
            tableInfo[i][2] = parts[2]; // room availability

            if (parts[1].equals("Single Room")) {
                tableInfo[i][3] = "500.00";
            } else if (parts[1].equals("Double Sharing Room")) {
                tableInfo[i][3] = "700.00";
            } else if (parts[1].equals("Triple Sharing Room")) {
                tableInfo[i][3] = "900.00";
            }
            i++;
        }

        return tableInfo;
    }

    public String[][] toRoomChangeTable() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Change_Room.txt"));

        int lineCount = 0;
        while (br.readLine() != null) {
            lineCount++;
        }
        br.close();

        String[][] tableInfo = new String[lineCount][9];

        br = new BufferedReader(new FileReader("Change_Room.txt"));
        String line;
        int i = 0;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            tableInfo[i][0] = parts[0]; // request id
            tableInfo[i][1] = parts[1]; // resident id
            tableInfo[i][2] = parts[2]; // resident name
            tableInfo[i][3] = parts[3]; // gender
            tableInfo[i][4] = parts[4]; // current room number
            tableInfo[i][5] = parts[5]; // current room type
            tableInfo[i][6] = parts[6]; // new room type
            tableInfo[i][7] = parts[7]; // description
            tableInfo[i][8] = parts[8]; // status
        }

        return tableInfo;
    }

    public String[][] toPaymentRecordTable() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Payment_Records.txt"));

        int lineCount = 0;
        while (br.readLine() != null) {
            lineCount++;
        }
        br.close();

        String[][] tableInfo = new String[lineCount][7];

        br = new BufferedReader(new FileReader("Payment_Records.txt"));
        String line;
        int i = 0;
        String roomType = "";

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            tableInfo[i][0] = parts[0]; // payment id
            tableInfo[i][1] = parts[1]; // resident id
            tableInfo[i][2] = parts[2]; // resident name
            tableInfo[i][3] = parts[3]; // room number
            tableInfo[i][4] = parts[4]; // room type
            tableInfo[i][5] = parts[5]; // amount
            tableInfo[i][6] = parts[6]; // datetime

        }


        return tableInfo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() throws IOException {

        ManagerMenuTab = new JTabbedPane();
        UserInfoPanel = new JPanel();
        UserInfoTab = new JTabbedPane();
        jScrollPane7 = new JScrollPane();
        ManagerInfoTable = new JTable();
        jScrollPane8 = new JScrollPane();
        StaffInfoTable = new JTable();
        jScrollPane9 = new JScrollPane();
        ResidentInfoTable = new JTable();
        AddUserButton = new JButton();
        DeleteUserButton = new JButton();
        EditUserButton = new JButton();
        FilterUserInfoButton = new JButton();
        SortUserInfoButton = new JButton();
        SearchUserInfoButton = new JButton();
        ViewUserDetailsButton = new JButton();
        RegistrationPanel = new JPanel();
        RegistrationRequestTab = new JTabbedPane();
        jScrollPane10 = new JScrollPane();
        ManagerRegistrationTable = new JTable();
        jScrollPane11 = new JScrollPane();
        StaffRegistrationTable = new JTable();
        jScrollPane12 = new JScrollPane();
        ResidentRegistrationTable = new JTable();
        FilterRegistrationButton = new JButton();
        SortRegistrationButton = new JButton();
        SearchRegistrationButton = new JButton();
        ApproveRegistrationButton = new JButton();
        RejectRegistrationButton = new JButton();
        RoomInformationPanel = new JPanel();
        jScrollPane2 = new JScrollPane();
        RoomInfoTable = new JTable();
        UpdateRoomInfoButton = new JButton();
        UpdateRateButton = new JButton();
        ViewRoomInfoDetailsButton = new JButton();
        SearchRoomInfoButton = new JButton();
        FilterRoomDetailsButton = new JButton();
        SortRoomDetailsButton = new JButton();
        RoomChangeRequestPanel = new JPanel();
        jScrollPane3 = new JScrollPane();
        RoomChangeRequestTable = new JTable();
        RejectRoomChangeButton = new JButton();
        ApproveRoomChangeButton = new JButton();
        ViewRoomChangeDetailsButton = new JButton();
        FilterRoomChangeButton = new JButton();
        SortRoomChangeButton = new JButton();
        SearchRoomChangeButton = new JButton();
        PaymentRecordPanel = new JPanel();
        jScrollPane5 = new JScrollPane();
        PaymentRecordTable = new JTable();
        ViewPaymentDetailsButton = new JButton();
        FilterPaymentButton = new JButton();
        SortPaymentButton = new JButton();
        SearchPaymentButton = new JButton();
        ProfilePanel = new JPanel();
        UsernameLabel = new JLabel();
        PasswordLabel = new JLabel();
        NameLabel = new JLabel();
        ContactLabel = new JLabel();
        EmailLabel = new JLabel();
        ManagerIDLabel = new JLabel();
        ManagerIDField = new JTextField();
        UsernameField = new JTextField();
        PasswordField = new JPasswordField();
        NameField = new JTextField();
        ContactField = new JTextField();
        EmailField = new JTextField();
        EditProfileButton = new JButton();
        LogOutPanel = new JPanel();
        LogOutButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ManagerInfoTable.setModel(new DefaultTableModel(
                toUserInfoTable(1),
                new String [] {
                        "ManagerID", "Username", "Name", "Contact Number", "Email Address"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(ManagerInfoTable);

        UserInfoTab.addTab("Manager", jScrollPane7);

        StaffInfoTable.setModel(new DefaultTableModel(
                toUserInfoTable(2),
                new String [] {
                        "Staff ID", "Username", "Name", "Contact Number", "Email Address"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane8.setViewportView(StaffInfoTable);

        UserInfoTab.addTab("Staff", jScrollPane8);

        ResidentInfoTable.setModel(new DefaultTableModel(
                toUserInfoTable(3),
                new String [] {
                        "Resident ID", "Username", "Name", "Contact Number", "Email Address", "Gender", "Room Number", "Overdue Amount"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane9.setViewportView(ResidentInfoTable);
        if (ResidentInfoTable.getColumnModel().getColumnCount() > 0) {
            ResidentInfoTable.getColumnModel().getColumn(0).setHeaderValue("Resident ID");
        }

        UserInfoTab.addTab("Resident", jScrollPane9);

        AddUserButton.setText("Add User");
        AddUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AddUserButtonActionPerformed(evt);
            }
        });

        DeleteUserButton.setText("Delete User");
        DeleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DeleteUserButtonActionPerformed(evt);
            }
        });

        EditUserButton.setText("Edit User");
        EditUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EditUserButtonActionPerformed(evt);
            }
        });

        ViewUserDetailsButton.setText("View Details");
        ViewUserDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewUserDetailsButtonActionPerformed(evt);
            }
        });

        FilterUserInfoButton.setText("Filter");
        FilterUserInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FilterUserInfoButtonActionPerformed(evt);
            }
        });

        SortUserInfoButton.setText("Sort");
        SortUserInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SortUserInfoButtonActionPerformed(evt);
            }
        });

        SearchUserInfoButton.setText("Search User");
        SearchUserInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchUserInfoButtonActionPerformed(evt);
            }
        });

        GroupLayout UserInfoPanelLayout = new GroupLayout(UserInfoPanel);
        UserInfoPanel.setLayout(UserInfoPanelLayout);
        UserInfoPanelLayout.setHorizontalGroup(
                UserInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, UserInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(UserInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(UserInfoTab)
                                        .addGroup(UserInfoPanelLayout.createSequentialGroup()
                                                .addComponent(AddUserButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(DeleteUserButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(EditUserButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                                                .addComponent(ViewUserDetailsButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(FilterUserInfoButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SortUserInfoButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SearchUserInfoButton)))
                                .addContainerGap())
        );
        UserInfoPanelLayout.setVerticalGroup(
                UserInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(UserInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(UserInfoTab, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(UserInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(AddUserButton)
                                        .addComponent(DeleteUserButton)
                                        .addComponent(EditUserButton)
                                        .addComponent(FilterUserInfoButton)
                                        .addComponent(SortUserInfoButton)
                                        .addComponent(SearchUserInfoButton)
                                        .addComponent(ViewUserDetailsButton))
                                .addContainerGap(42, Short.MAX_VALUE))
        );

        ManagerMenuTab.addTab("User Information", UserInfoPanel);

        RegistrationRequestTab.addTab("Manager", jScrollPane10);

        ManagerRegistrationTable.setModel(new DefaultTableModel(
                toRegistrationTable(1),
                new String [] {
                        "Username", "Name", "Contact Number", "Email Address", "Request Date"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane10.setViewportView(ManagerRegistrationTable);

        StaffRegistrationTable.setModel(new DefaultTableModel(
                toRegistrationTable(2),
                new String [] {
                        "Username", "Name", "Contact Number", "Email Address", "Request Date"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane11.setViewportView(StaffRegistrationTable);

        RegistrationRequestTab.addTab("Staff", jScrollPane11);

        ResidentRegistrationTable.setModel(new DefaultTableModel(
                toRegistrationTable(3),
                new String [] {
                        "Username", "Name", "Contact Number", "Email Address", "Gender", "Room Type", "Request Date"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane12.setViewportView(ResidentRegistrationTable);

        RegistrationRequestTab.addTab("Resident", jScrollPane12);

        ApproveRegistrationButton.setText("Approve");
        ApproveRegistrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ApproveRegistrationButtonActionPerformed(evt);
            }
        });

        RejectRegistrationButton.setText("Reject");
        RejectRegistrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RejectRegistrationButtonActionPerformed(evt);
            }
        });

        FilterRegistrationButton.setText("Filter");
        FilterRegistrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FilterRegistrationButtonActionPerformed(evt);
            }
        });

        SortRegistrationButton.setText("Sort");
        SortRegistrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SortRegistrationActionPerformed(evt);
            }
        });

        SearchRegistrationButton.setText("Search User");
        SearchRegistrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchRegistrationButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(RegistrationPanel);
        RegistrationPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(RegistrationRequestTab, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(ApproveRegistrationButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(RejectRegistrationButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(FilterRegistrationButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SortRegistrationButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SearchRegistrationButton)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(RegistrationRequestTab, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(SortRegistrationButton)
                                        .addComponent(SearchRegistrationButton)
                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(ApproveRegistrationButton)
                                                .addComponent(RejectRegistrationButton)
                                                .addComponent(FilterRegistrationButton)))
                                .addContainerGap(39, Short.MAX_VALUE))
        );

        ManagerMenuTab.addTab("Registration Request", RegistrationPanel);

        RoomInfoTable.setModel(new DefaultTableModel(
                toRoomInfoTable(),
                new String [] {
                        "Room Number", "Room Type", "Room Availability", "Rate"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, Integer.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });

        jScrollPane2.setViewportView(RoomInfoTable);

        UpdateRoomInfoButton.setText("Update Room Information");
        UpdateRoomInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UpdateRoomInfoButtonActionPerformed(evt);
            }
        });

        UpdateRateButton.setText("Update Rate");
        UpdateRateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UpdateRateButtonActionPerformed(evt);
            }
        });

        ViewRoomInfoDetailsButton.setText("View Details");
        ViewRoomInfoDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewRoomInfoDetailsButtonActionPerformed(evt);
            }
        });

        SearchRoomInfoButton.setText("Search Room");
        SearchRoomInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchRoomInfoButtonActionPerformed(evt);
            }
        });

        FilterRoomDetailsButton.setText("Filter");
        FilterRoomDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FilterRoomDetailsButtonActionPerformed(evt);
            }
        });

        SortRoomDetailsButton.setText("Sort");
        SortRoomDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SortRoomDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RoomInformationPanelLayout = new javax.swing.GroupLayout(RoomInformationPanel);
        RoomInformationPanel.setLayout(RoomInformationPanelLayout);
        RoomInformationPanelLayout.setHorizontalGroup(
                RoomInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RoomInformationPanelLayout.createSequentialGroup()
                                .addGroup(RoomInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(RoomInformationPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(UpdateRateButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(UpdateRoomInfoButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ViewRoomInfoDetailsButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(FilterRoomDetailsButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SortRoomDetailsButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SearchRoomInfoButton))
                                        .addGroup(RoomInformationPanelLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        RoomInformationPanelLayout.setVerticalGroup(
                RoomInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RoomInformationPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RoomInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(UpdateRoomInfoButton)
                                        .addComponent(UpdateRateButton)
                                        .addComponent(ViewRoomInfoDetailsButton)
                                        .addComponent(FilterRoomDetailsButton)
                                        .addComponent(SortRoomDetailsButton)
                                        .addComponent(SearchRoomInfoButton))
                                .addContainerGap(39, Short.MAX_VALUE))
        );


        ManagerMenuTab.addTab("Room Information", RoomInformationPanel);

        RoomChangeRequestTable.setModel(new DefaultTableModel(
                toRoomChangeTable(),
                new String [] {
                        "Request ID", "Resident ID", "Resident Name", "Gender", "Current Room Number", "Current Room Type", "New Room Type", "Description", "Status"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(RoomChangeRequestTable);

        RejectRoomChangeButton.setText("Reject");
        RejectRoomChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RejectRoomChangeButtonActionPerformed(evt);
            }
        });

        ApproveRoomChangeButton.setText("Approve");
        ApproveRoomChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ApproveRoomChangeButtonActionPerformed(evt);
            }
        });

        ViewRoomChangeDetailsButton.setText("View Details");
        ViewRoomChangeDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewRoomChangeDetailsButtonActionPerformed(evt);
            }
        });

        FilterRoomChangeButton.setText("Filter");
        FilterRoomChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FilterRoomChangeButtonActionPerformed(evt);
            }
        });

        SortRoomChangeButton.setText("Sort");
        SortRoomChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SortRoomChangeButtonActionPerformed(evt);
            }
        });

        SearchRoomChangeButton.setText("Search Request");
        SearchRoomChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchRoomChangeButtonActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout RoomChangeRequestPanelLayout = new javax.swing.GroupLayout(RoomChangeRequestPanel);
        RoomChangeRequestPanel.setLayout(RoomChangeRequestPanelLayout);
        RoomChangeRequestPanelLayout.setHorizontalGroup(
                RoomChangeRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RoomChangeRequestPanelLayout.createSequentialGroup()
                                .addGroup(RoomChangeRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(RoomChangeRequestPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(ApproveRoomChangeButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(RejectRoomChangeButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ViewRoomChangeDetailsButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(FilterRoomChangeButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SortRoomChangeButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SearchRoomChangeButton))
                                        .addGroup(RoomChangeRequestPanelLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        RoomChangeRequestPanelLayout.setVerticalGroup(
                RoomChangeRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RoomChangeRequestPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RoomChangeRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(RejectRoomChangeButton)
                                        .addComponent(ApproveRoomChangeButton)
                                        .addComponent(ViewRoomChangeDetailsButton)
                                        .addComponent(FilterRoomChangeButton)
                                        .addComponent(SortRoomChangeButton)
                                        .addComponent(SearchRoomChangeButton))
                                .addContainerGap(39, Short.MAX_VALUE))
        );


        ManagerMenuTab.addTab("Room Change Request", RoomChangeRequestPanel);

        PaymentRecordTable.setModel(new DefaultTableModel(
               toPaymentRecordTable(),
                new String [] {
                        "Payment ID", "Resident ID", "Resident Name", "Room Number", "Room Type", "Amount", "Datetime"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(PaymentRecordTable);

        ViewPaymentDetailsButton.setText("View Details");
        ViewPaymentDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewPaymentDetailsButtonActionPerformed(evt);
            }
        });

        FilterPaymentButton.setText("Filter");
        FilterPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FilterPaymentButtonActionPerformed(evt);
            }
        });

        SortPaymentButton.setText("Sort");
        SortPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SortPaymentButtonActionPerformed(evt);
            }
        });

        SearchPaymentButton.setText("Search Record");
        SearchPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchPaymentButtonActionPerformed(evt);
            }
        });

        GroupLayout PaymentRecordPanelLayout = new GroupLayout(PaymentRecordPanel);
        PaymentRecordPanel.setLayout(PaymentRecordPanelLayout);
        PaymentRecordPanelLayout.setHorizontalGroup(
                PaymentRecordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(PaymentRecordPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ViewPaymentDetailsButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FilterPaymentButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SortPaymentButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SearchPaymentButton)
                                .addContainerGap())
                        .addGroup(PaymentRecordPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane5, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
        );
        PaymentRecordPanelLayout.setVerticalGroup(
                PaymentRecordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(PaymentRecordPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane5, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PaymentRecordPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(ViewPaymentDetailsButton)
                                        .addComponent(FilterPaymentButton)
                                        .addComponent(SortPaymentButton)
                                        .addComponent(SearchPaymentButton))
                                .addContainerGap(39, Short.MAX_VALUE))
        );

        ManagerMenuTab.addTab("Payment Record", PaymentRecordPanel);

        ManagerIDLabel.setText("Manager ID");

        UsernameLabel.setText("Username");

        PasswordLabel.setText("Password");

        NameLabel.setText("Name");

        ContactLabel.setText("Contact Number");

        EmailLabel.setText("Email Address");


        ManagerIDField.setEditable(false);
        ManagerIDField.setText("M0001");
        ManagerIDField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ManagerIDFieldActionPerformed(evt);
            }
        });

        UsernameField.setEditable(false);
        UsernameField.setText("mervin");
        UsernameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                UsernameFieldActionPerformed(evt);
            }
        });

        PasswordField.setEditable(false);
        PasswordField.setText("123123");
        PasswordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });

        NameField.setEditable(false);
        NameField.setText("Mervin");
        NameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NameFieldActionPerformed(evt);
            }
        });

        ContactField.setEditable(false);
        ContactField.setText("0123456789");
        ContactField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ContactFieldActionPerformed(evt);
            }
        });

        EmailField.setEditable(false);
        EmailField.setText("mervinooi221@gmail.com");
        EmailField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EmailFieldActionPerformed(evt);
            }
        });

        EditProfileButton.setText("Edit Profile");
        EditProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EditProfileButtonActionPerformed(evt);
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
                                        .addComponent(ManagerIDLabel)
                                        .addComponent(UsernameLabel)
                                        .addComponent(NameLabel)
                                        .addComponent(ContactLabel)
                                        .addComponent(PasswordLabel))
                                .addGap(45, 45, 45)
                                .addGroup(ProfilePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(ManagerIDField)
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
                                        .addComponent(ManagerIDLabel)
                                        .addComponent(ManagerIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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

        ManagerMenuTab.addTab("Profile", ProfilePanel);

        LogOutButton.setText("Log Out");
        LogOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        GroupLayout LogOutPanelLayout = new GroupLayout(LogOutPanel);
        LogOutPanel.setLayout(LogOutPanelLayout);
        LogOutPanelLayout.setHorizontalGroup(
                LogOutPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(LogOutPanelLayout.createSequentialGroup()
                                .addGap(404, 404, 404)
                                .addComponent(LogOutButton)
                                .addContainerGap(410, Short.MAX_VALUE))
        );
        LogOutPanelLayout.setVerticalGroup(
                LogOutPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(LogOutPanelLayout.createSequentialGroup()
                                .addGap(194, 194, 194)
                                .addComponent(LogOutButton)
                                .addContainerGap(235, Short.MAX_VALUE))
        );

        ManagerMenuTab.addTab("Log Out", LogOutPanel);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ManagerMenuTab)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ManagerMenuTab))
        );

        pack();
    }// </editor-fold>

    private void AddUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void DeleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void EditUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ViewUserDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void FilterUserInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SortUserInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SearchUserInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ApproveRegistrationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void RejectRegistrationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void FilterRegistrationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SortRegistrationActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SearchRegistrationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ViewRoomInfoDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void UpdateRateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void UpdateRoomInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ViewRoomChangeDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ApproveRoomChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void RejectRoomChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ViewPaymentDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ManagerIDFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void UsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void PasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void NameFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void ContactFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void EmailFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void EditProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SearchRoomInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void FilterRoomDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SortRoomDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void FilterRoomChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SortRoomChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SearchRoomChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void FilterPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SortPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void SearchPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(ManagerMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerMenuPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ManagerMenuPage().setVisible(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton AddUserButton;
    private javax.swing.JButton ApproveRegistrationButton;
    private javax.swing.JButton ApproveRoomChangeButton;
    private javax.swing.JTextField ContactField;
    private javax.swing.JLabel ContactLabel;
    private javax.swing.JButton DeleteUserButton;
    private javax.swing.JButton EditProfileButton;
    private javax.swing.JButton EditUserButton;
    private javax.swing.JTextField EmailField;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JButton FilterPaymentButton;
    private javax.swing.JButton FilterRegistrationButton;
    private javax.swing.JButton FilterRoomChangeButton;
    private javax.swing.JButton FilterRoomDetailsButton;
    private javax.swing.JButton FilterUserInfoButton;
    private javax.swing.JButton LogOutButton;
    private javax.swing.JPanel LogOutPanel;
    private javax.swing.JTextField ManagerIDField;
    private javax.swing.JLabel ManagerIDLabel;
    private javax.swing.JTable ManagerInfoTable;
    private javax.swing.JTabbedPane ManagerMenuTab;
    private javax.swing.JTable ManagerRegistrationTable;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JPanel PaymentRecordPanel;
    private javax.swing.JTable PaymentRecordTable;
    private javax.swing.JPanel ProfilePanel;
    private javax.swing.JTabbedPane RegistrationRequestTab;
    private javax.swing.JButton RejectRegistrationButton;
    private javax.swing.JButton RejectRoomChangeButton;
    private javax.swing.JTable ResidentRegistrationTable;
    private javax.swing.JTable ResidentInfoTable;
    private javax.swing.JPanel RoomChangeRequestPanel;
    private javax.swing.JTable RoomChangeRequestTable;
    private javax.swing.JTable RoomInfoTable;
    private javax.swing.JPanel RoomInformationPanel;
    private javax.swing.JButton SearchPaymentButton;
    private javax.swing.JButton SearchRegistrationButton;
    private javax.swing.JButton SearchRoomChangeButton;
    private javax.swing.JButton SearchRoomInfoButton;
    private javax.swing.JButton SearchUserInfoButton;
    private javax.swing.JButton SortPaymentButton;
    private javax.swing.JButton SortRegistrationButton;
    private javax.swing.JButton SortRoomChangeButton;
    private javax.swing.JButton SortRoomDetailsButton;
    private javax.swing.JButton SortUserInfoButton;
    private javax.swing.JTable StaffInfoTable;
    private javax.swing.JTable StaffRegistrationTable;
    private javax.swing.JButton UpdateRateButton;
    private javax.swing.JButton UpdateRoomInfoButton;
    private javax.swing.JTabbedPane UserInfoTab;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.JButton ViewPaymentDetailsButton;
    private javax.swing.JButton ViewRoomChangeDetailsButton;
    private javax.swing.JButton ViewRoomInfoDetailsButton;
    private javax.swing.JButton ViewUserDetailsButton;
    private javax.swing.JPanel RegistrationPanel;
    private javax.swing.JPanel UserInfoPanel;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration
}
