import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mervin Ooi
 */
public class ManagerMenuPage extends JFrame {

    /**
     * Creates new form ApproveUserPage
     */
    private Manager manager;

    public ManagerMenuPage() throws IOException {
        initComponents();
        setLocationRelativeTo(null);
    }

    public ManagerMenuPage(Manager manager) throws IOException {
        this.manager = manager;
        initComponents();
        setLocationRelativeTo(null);
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
            tableInfo = new String[lineCount][9];
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
                tableInfo[i][7] = parts[8]; // room type
                tableInfo[i][8] = parts[9]; // overdue amount

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
            tableInfo[i][0] = parts[0]; // username
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

            i++;
        }
        return tableInfo;
    }

    public String[][] toRoomInfoTable() throws IOException {
        BufferedReader roomInfoReader = new BufferedReader(new FileReader("Room_Info.txt"));

        int lineCount = 0;
        while (roomInfoReader.readLine() != null) {
            lineCount++;
        }
        roomInfoReader.close();

        String[][] tableInfo = new String[lineCount][4];

        roomInfoReader = new BufferedReader(new FileReader("Room_Info.txt"));
        String line;
        int i = 0;

        BufferedReader roomTypeReader = new BufferedReader(new FileReader("Room_Type.txt"));
        ArrayList roomTypeList = new ArrayList();

        while ((line = roomTypeReader.readLine()) != null) {
            roomTypeList.add(line);
        }

        String[] rate = new String[3];
        for (int j = 0; j < roomTypeList.size(); j++) {
            String[] parts = roomTypeList.get(j).toString().split(",");
            rate[j] = parts[1];
        }

        while ((line = roomInfoReader.readLine()) != null) {
            String[] parts = line.split(",");
            tableInfo[i][0] = parts[0]; // room number
            tableInfo[i][1] = parts[1]; // room type
            tableInfo[i][2] = parts[2]; // room availability

            if (parts[1].equals("Single Room")) {
                tableInfo[i][3] = rate[0];
            } else if (parts[1].equals("Double Sharing Room")) {
                tableInfo[i][3] = rate[1];
            } else if (parts[1].equals("Triple Sharing Room")) {
                tableInfo[i][3] = rate[2];
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
            i++;
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
            i++;
        }

        return tableInfo;
    }

    public ArrayList<Object> getUserInfoTable() {
        ArrayList<Object> userInfo = new ArrayList<>();
        int tab = UserInfoTab.getSelectedIndex();
        DefaultTableModel model = null;
        JTable table = null;
        String filename = "";

        if (tab == 1) {
            model = (DefaultTableModel) StaffInfoTable.getModel();
            table = StaffInfoTable;
            filename = "Staff_Info.txt";

        } else if (tab == 2) {
            model = (DefaultTableModel) ResidentInfoTable.getModel();
            table = ResidentInfoTable;
            filename = "Resident_Info.txt";

        } else if (tab == 0) {
            model = (DefaultTableModel) ManagerInfoTable.getModel();
            table = ManagerInfoTable;
            filename = "Manager_Info.txt";
        }


        userInfo.add(tab);
        userInfo.add(model);
        userInfo.add(table);
        userInfo.add(filename);
        return userInfo;
    }

    public void setupTableSorter(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
    }

    public void updateUserInfoFile(int userType) throws IOException {
        String filename = manager.getInfoFilename(userType);
        BufferedReader br = new BufferedReader(new FileReader(filename));
        ArrayList<String> passwords = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            passwords.add(parts[2]);
        }
        br.close();

        DefaultTableModel model = null;

        if (userType == 1) {
            model = (DefaultTableModel) ManagerInfoTable.getModel();
        } else if (userType == 2) {
            model = (DefaultTableModel) StaffInfoTable.getModel();
        } else if (userType == 3) {
            model = (DefaultTableModel) ResidentInfoTable.getModel();
        }

        StringJoiner sj = new StringJoiner(System.lineSeparator());

        for (int i = 0; i < model.getRowCount(); i++) {
            StringJoiner row = new StringJoiner(",");
            for (int j = 0; j < model.getColumnCount(); j++) {
                if (j == 2) {
                    row.add(passwords.get(i));
                }
                row.add(model.getValueAt(i, j).toString());
            }
            sj.add(row.toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write(sj.toString());
        bw.close();
    }

    public void refreshUserInfoTable() throws IOException {
        int tab = UserInfoTab.getSelectedIndex();
        DefaultTableModel model = null;
        JTable table = null;
        String filename = "";

        if (tab == 1) {
            model = (DefaultTableModel) StaffInfoTable.getModel();
            table = StaffInfoTable;
            filename = "Staff_Info.txt";
        } else if (tab == 2) {
            model = (DefaultTableModel) ResidentInfoTable.getModel();
            table = ResidentInfoTable;
            filename = "Resident_Info.txt";
        } else if (tab == 0) {
            model = (DefaultTableModel) ManagerInfoTable.getModel();
            table = ManagerInfoTable;
            filename = "Manager_Info.txt";
        }

        table.setRowSorter(null);
        model.setRowCount(0);

        BufferedReader br = new BufferedReader(new FileReader(filename));
        ArrayList<String> userInfoList = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            userInfoList.add(line);
        }
        br.close();

        for (String userInfo : userInfoList) {
            String[] parts = userInfo.split(",");
            if (tab == 2) {
                model.addRow(new Object[]{parts[0], parts[1], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9]});
            } else {
                model.addRow(new Object[]{parts[0], parts[1], parts[3], parts[4], parts[5]});
            }
        }

        setupTableSorter(table);
    }

    public void deleteUserInfo() throws IOException {
        DefaultTableModel model = (DefaultTableModel) getUserInfoTable().get(1);
        JTable table = (JTable) getUserInfoTable().get(2);
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String id = table.getValueAt(row, 0).toString();
        String filename = (String) getUserInfoTable().get(3);

        int deleteUser = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Delete User", JOptionPane.YES_NO_OPTION);

        if (deleteUser == JOptionPane.YES_OPTION) {
            if (id.contains("R")) {
                BufferedReader roomInfoReader = new BufferedReader(new FileReader("Room_Info.txt"));
                ArrayList roomInfoList = new ArrayList();
                String line;
                while ((line = roomInfoReader.readLine()) != null) {
                    roomInfoList.add(line);
                }

                String roomNumber = table.getValueAt(row, 6).toString();
                for (int i = 0; i < roomInfoList.size(); i++) {
                    String[] parts = roomInfoList.get(i).toString().split(",");
                    if (parts[0].equals(roomNumber)) {
                        roomInfoList.set(i, parts[0] + "," + parts[1] + "," + (Integer.valueOf(parts[2]) + 1) + "," + parts[3]);
                    }
                }

                StringJoiner sj = new StringJoiner(System.lineSeparator());
                for (int i = 0; i < roomInfoList.size(); i++) {
                    sj.add(roomInfoList.get(i).toString());
                }

                BufferedWriter bw = new BufferedWriter(new FileWriter("Room_Info.txt"));
                bw.write(sj.toString());
                bw.close();
            }

            ArrayList<Object> userInfo = getUserInfoTable();
            if (userInfo.isEmpty()) {
                return;
            }

            model.removeRow(row);


            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringJoiner sj = new StringJoiner(System.lineSeparator());
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(id)) {
                    sj.add(line);
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(sj.toString());
            bw.close();

            JOptionPane.showMessageDialog(null, "User has been deleted successfully.", "Delete User", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void editUserInfo() throws IOException {
        ArrayList<Object> userInfoTable = getUserInfoTable();
        if (userInfoTable.isEmpty()) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) userInfoTable.get(1);
        JTable table = (JTable) userInfoTable.get(2);
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String id = table.getValueAt(row, 0).toString();
        String filename = (String) userInfoTable.get(3);


        String[] changeType = null;
        int column = 0;
        String value = null;
        ArrayList<String> availableRoomsList = new ArrayList<>();
        String newRoomType = "";

        if (filename.equals("Resident_Info.txt")) {
            BufferedReader roomInfoReader = new BufferedReader(new FileReader("Room_Info.txt"));
            String gender = table.getValueAt(row, 5).toString();
            String line = "";
            while ((line = roomInfoReader.readLine()) != null) {
                String[] roomInfo = line.split(",");
                int availability = Integer.valueOf(roomInfo[2]);
                if (gender.equals("Male") && Integer.valueOf(roomInfo[2]) != 0) {
                    if (roomInfo[0].toString().charAt(0) == 'M') {
                        availableRoomsList.add(roomInfo[0]);
                    }
                } else if (gender.equals("Female") && Integer.valueOf(roomInfo[2]) != 0) {
                    if (roomInfo[0].toString().charAt(0) == 'F') {
                        availableRoomsList.add(roomInfo[0]);
                    }
                }
            }

            roomInfoReader.close();

            changeType = new String[]{"Username", "Name", "Contact Number", "Email Address", "Room Number", "Overdue Amount"};
            int count = 0;
            line = "";
            roomInfoReader = new BufferedReader(new FileReader("Room_Info.txt"));

        } else if (filename.equals("Manager_Info.txt") || filename.equals("Staff_Info.txt")) {
            changeType = new String[]{"Username", "Name", "Contact Number", "Email Address"};
        }

        int choice = JOptionPane.showOptionDialog(null, "Please select attribute you want to edit.", "Edit User", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, changeType, changeType[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            return;
        }

        if (choice == 0) {
            value = JOptionPane.showInputDialog("Enter new username: ");
            if (value == null) {
                return;
            } else if (!manager.isUsernameUnique(value)) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please enter other username.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (choice == 1) {
            value = JOptionPane.showInputDialog("Enter new name: ");
            if (value == null) {
                return;
            } else if (!manager.validateName(value)) {
                JOptionPane.showMessageDialog(null, "Invalid name. Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (choice == 2) {
            value = JOptionPane.showInputDialog("Enter new contact number: ");
            if (value == null) {
                return;
            } else if (!manager.validateContactNumber(value)) {
                JOptionPane.showMessageDialog(null, "Invalid contact number. Please enter a valid contact number between 9 and 11 digit without any special characters");
                return;
            } else if (!manager.isContactNumberUnique(value)) {
                JOptionPane.showMessageDialog(null, "Contact number already exists. Please enter other contact number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (choice == 3) {
            value = JOptionPane.showInputDialog("Enter new email address: ");
            if (value == null) {
                return;
            } else if (!manager.validateEmail(value)) {
                JOptionPane.showMessageDialog(null, "Invalid email address. Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!manager.isEmailUnique(value)) {
                JOptionPane.showMessageDialog(null, "Email address already exists. Please enter other email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (choice == 4) {
            String[] availableRooms = availableRoomsList.toArray(new String[0]);
            value = JOptionPane.showInputDialog(null, "Select new room number:", "Edit Room Number", JOptionPane.PLAIN_MESSAGE, null, availableRooms, availableRooms[0]).toString();
            if (value == null) {
                return;
            }

            if (value.contains("S")) {
                newRoomType = "Single Room";
            } else if (value.contains("D")) {
                newRoomType = "Double Sharing Room";
            } else if (value.contains("T")) {
                newRoomType = "Triple Sharing Room";
            }

            String currentRoomNumber = table.getValueAt(row, 6).toString();
            String newRoomNumber = value;

            BufferedReader roomInfoReader = new BufferedReader(new FileReader("Room_Info.txt"));
            ArrayList roomInfoList = new ArrayList();
            String line;
            while ((line = roomInfoReader.readLine()) != null) {
                roomInfoList.add(line);
            }

            for (int i = 0; i < roomInfoList.size(); i++) {
                String[] parts = roomInfoList.get(i).toString().split(",");
                if (parts[0].equals(currentRoomNumber)) {
                    roomInfoList.set(i, parts[0] + "," + parts[1] + "," + (Integer.valueOf(parts[2]) + 1) + "," + parts[3]);
                }
            }

            for (int i = 0; i < roomInfoList.size(); i++) {
                String[] parts = roomInfoList.get(i).toString().split(",");
                if (parts[0].equals(newRoomNumber)) {
                    roomInfoList.set(i, parts[0] + "," + parts[1] + "," + (Integer.valueOf(parts[2]) - 1) + "," + parts[3]);
                }
            }

            StringJoiner sj = new StringJoiner(System.lineSeparator());
            for (int i = 0; i < roomInfoList.size(); i++) {
                sj.add(roomInfoList.get(i).toString());
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("Room_Info.txt"));
            bw.write(sj.toString());
            bw.close();

            BufferedReader residentInfoReader = new BufferedReader(new FileReader("Resident_Info.txt"));
            ArrayList residentInfoList = new ArrayList();
            while ((line = residentInfoReader.readLine()) != null) {
                residentInfoList.add(line);
            }

            residentInfoReader.close();

            for (int i = 0; i < residentInfoList.size(); i++) {
                String[] parts = residentInfoList.get(i).toString().split(",");
                if (parts[0].equals(id)) {
                    residentInfoList.set(i, parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + newRoomNumber + "," + newRoomType + "," + parts[9]);
                }
            }

        } else if (choice == 5) {
            String temp = JOptionPane.showInputDialog("Enter new overdue amount: ");
            value = "RM"  + temp;
            if (temp == null) {
                return;
            } else if (Double.valueOf(temp) < 0) {
                JOptionPane.showMessageDialog(null, "Overdue amount cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (value == null) {
            return;
        }

        if (choice >= 0 && choice < 4) {
            model.setValueAt(value, row, (choice + 1));
        } else if (choice == 4) {
            model.setValueAt(value, row,  (choice + 2));
            model.setValueAt(newRoomType, row, (choice + 3));
        } else if (choice == 5) {
            model.setValueAt(value, row,  (choice + 3));
        }

        if (filename.equals("Manager_Info.txt")) {
            updateUserInfoFile(1);
        } else if (filename.equals("Staff_Info.txt")) {
            updateUserInfoFile(2);
        } else if (filename.equals("Resident_Info.txt")) {
            updateUserInfoFile(3);
        }
    }

    public void searchUserInfo() {
        String input = JOptionPane.showInputDialog(null, "Please enter any details to search user:", "Search User", JOptionPane.PLAIN_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) getUserInfoTable().get(1);
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + input));

            JTable table = (JTable) getUserInfoTable().get(2);

            if (table != null) {
                table.setRowSorter(sorter);
            }

        } else if (input == null) {
            return;
        }
        else if (input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search input cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Object> getUserRegistrationTable() {
        ArrayList<Object> userInfo = new ArrayList<>();
        int tab = RegistrationRequestTab.getSelectedIndex();
        DefaultTableModel model = null;
        JTable table = null;
        String filename = "";

        if (tab == 1) {
            model = (DefaultTableModel) StaffRegistrationTable.getModel();
            table = StaffRegistrationTable;
            filename = "Staff_Registration.txt";

        } else if (tab == 2) {
            model = (DefaultTableModel) ResidentRegistrationTable.getModel();
            table = ResidentRegistrationTable;
            filename = "Resident_Registration.txt";

        } else if (tab == 0) {
            model = (DefaultTableModel) ManagerRegistrationTable.getModel();
            table = ManagerRegistrationTable;
            filename = "Manager_Registration.txt";
        }

        userInfo.add(tab);
        userInfo.add(model);
        userInfo.add(table);
        userInfo.add(filename);
        return userInfo;
    }

    public void approveRegistration() throws IOException {
        DefaultTableModel model = (DefaultTableModel) getUserRegistrationTable().get(1);
        JTable table = (JTable) getUserRegistrationTable().get(2);
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selectedUsername = table.getValueAt(row, 0).toString();

        StringJoiner sj = new StringJoiner((","));

        for (int i = 0; i < model.getColumnCount(); i++) {
            sj.add(model.getValueAt(row, i).toString());
        }

        String registrationFilename = (String) getUserRegistrationTable().get(3);
        BufferedReader RegistrationFileReader = new BufferedReader(new FileReader(registrationFilename));

        String line;
        String[] registeredUser = null;
        ArrayList registrationList = new ArrayList();
        int count = 0;

        while ((line = RegistrationFileReader.readLine()) != null) {
            registrationList.add(line);
            count++;

            if (line.split(",")[0].equals(selectedUsername)) {
                registeredUser = line.split(",");
            }

        }

        for (int i = 0; i < registrationList.size(); i++) {
            if (registeredUser[0].equals(selectedUsername)) {
                registrationList.remove(i);
            }
         }

        RegistrationFileReader.close();

        // get info filename
        String infoFilename = "";
        if (registrationFilename.equals("Manager_Registration.txt")) {
            infoFilename = "Manager_Info.txt";
        } else if (registrationFilename.equals("Staff_Registration.txt")) {
            infoFilename = "Staff_Info.txt";
        } else if (registrationFilename.equals("Resident_Registration.txt")) {
            infoFilename = "Resident_Info.txt";
        }

        // create user info
        BufferedWriter infoFileWriter = new BufferedWriter(new FileWriter(infoFilename, true));
        String id = manager.generateId(infoFilename);
        String username = "";
        String password = "";
        String name = "";
        String contact = "";
        String email = "";
        String gender = "";
        String roomNumber = "";
        String userInfo = "";

        if (infoFilename.equals("Manager_Info.txt")) {
            username = registeredUser[0];
            password = registeredUser[1];
            name = registeredUser[2];
            contact = registeredUser[3];
            email = registeredUser[4];
            userInfo = id + "," + username + "," + password + "," + name + "," + contact + "," + email;

        } else if (infoFilename.equals("Staff_Info.txt")) {
            username = registeredUser[0];
            password = registeredUser[1];
            name = registeredUser[2];
            contact = registeredUser[3];
            email = registeredUser[4];
            userInfo = id + "," + username + "," + password + "," + name + "," + contact + "," + email;

        } else if (infoFilename.equals("Resident_Info.txt")) {
            // assign room number
            ArrayList availableRooms = new ArrayList();
            BufferedReader roomInfoReader = new BufferedReader(new FileReader("Room_Info.txt"));

            gender = registeredUser[5];
            String roomType = registeredUser[6];
            String prefix = "";
            if (gender.equals("Male")) {
                if (roomType.equals("Single Room")) {
                    prefix = "MS";
                } else if (roomType.equals("Double Sharing Room")) {
                    prefix = "MD";
                } else if (roomType.equals("Triple Sharing Room")) {
                    prefix = "MT";
                }

            } else if (gender.equals("Female")) {
                if (roomType.equals("Single Room")) {
                    prefix = "FS";
                } else if (roomType.equals("Double Sharing Room")) {
                    prefix = "FD";
                } else if (roomType.equals("Triple Sharing Room")) {
                    prefix = "FT";
                }
            }

            while ((line = roomInfoReader.readLine()) != null) {
                    availableRooms.add(line);
            }

            roomInfoReader.close();

            room: for (int i = 0; i < availableRooms.size(); i++) {
                String[] parts = availableRooms.get(i).toString().split(",");
                String availableRoom = parts[0];
                int availbility = Integer.valueOf(parts[2]);

                if (availbility > 0 && availableRoom.contains(prefix)) {
                    roomNumber = availableRoom;
                    availbility--;
                    availableRooms.set(i, parts[0] + "," + parts[1] + "," + availbility + "," + parts[3]);
                    break room;
                }
            }

            sj = new StringJoiner(System.lineSeparator());
            for (int i = 0; i < availableRooms.size(); i++) {
                sj.add(availableRooms.get(i).toString());
            }
            BufferedWriter roomInfoWriter = new BufferedWriter(new FileWriter("Room_Info.txt"));
            roomInfoWriter.write(sj.toString());
            roomInfoWriter.close();

            username = registeredUser[0];
            password = registeredUser[1];
            name = registeredUser[2];
            contact = registeredUser[3];
            email = registeredUser[4];
            userInfo = id + "," + username + "," + password + "," + name + "," + contact + "," + email + "," + gender + "," + roomNumber + "," + roomType + "," + "RM00.00";
        }

        BufferedReader infoFileReader = new BufferedReader(new FileReader(infoFilename));
        if (infoFileReader.readLine() != null) {
            infoFileWriter.newLine();
        }
        infoFileWriter.write(userInfo);
        infoFileWriter.close();

        model.removeRow(row);

        // rewrite registration file
        BufferedWriter registrationFileWriter = new BufferedWriter(new FileWriter(registrationFilename));
        for (int i = 0; i < registrationList.size(); i++) {
            registrationFileWriter.write(registrationList.get(i).toString());
        }
        registrationFileWriter.close();

        JOptionPane.showMessageDialog(null, "Registration request has been approved successfully.", "Approve Registration", JOptionPane.INFORMATION_MESSAGE);
    }

    public void rejectRegistration() throws IOException {
        DefaultTableModel model = (DefaultTableModel) getUserRegistrationTable().get(1);
        JTable table = (JTable) getUserRegistrationTable().get(2);
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selectedUsername = table.getValueAt(row, 0).toString();
        String registrationFilename = (String) getUserRegistrationTable().get(3);

        model.removeRow(row);

        BufferedReader RegistrationFileReader = new BufferedReader(new FileReader(registrationFilename));

        String line;
        ArrayList registrationList = new ArrayList();

        while ((line = RegistrationFileReader.readLine()) != null) {
            registrationList.add(line);
        }

        RegistrationFileReader.close();

        for (int i = 0; i < registrationList.size(); i++) {
            if (registrationList.get(i).toString().split(",")[0].equals(selectedUsername)) {
                registrationList.remove(i);
            }
        }

        BufferedWriter registrationFileWriter = new BufferedWriter(new FileWriter(registrationFilename));
        for (int i = 0; i < registrationList.size(); i++) {
            registrationFileWriter.write(registrationList.get(i).toString());
        }
        registrationFileWriter.close();


        JOptionPane.showMessageDialog(null, "Registration request has been rejected successfully.", "Reject Registration", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchRegistration() {
        String input = JOptionPane.showInputDialog(null, "Please enter any details to search registration:", "Search Registration", JOptionPane.PLAIN_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) getUserRegistrationTable().get(1);
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + input));

            JTable table = (JTable) getUserRegistrationTable().get(2);

            if (table != null) {
                table.setRowSorter(sorter);
            }
        } else if (input == null) {
            return;

        } else if (input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search input cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshRegistration() throws IOException {
        int tab = RegistrationRequestTab.getSelectedIndex();
        DefaultTableModel model = null;
        JTable table = null;
        String filename = "";

        if (tab == 1) {
            model = (DefaultTableModel) StaffRegistrationTable.getModel();
            table = StaffRegistrationTable;
            filename = "Staff_Registration.txt";
        } else if (tab == 2) {
            model = (DefaultTableModel) ResidentRegistrationTable.getModel();
            table = ResidentRegistrationTable;
            filename = "Resident_Registration.txt";
        } else if (tab == 0) {
            model = (DefaultTableModel) ManagerRegistrationTable.getModel();
            table = ManagerRegistrationTable;
            filename = "Manager_Registration.txt";
        }

        table.setRowSorter(null);
        model.setRowCount(0);

        BufferedReader br = new BufferedReader(new FileReader(filename));
        ArrayList<String> userRegistrationList = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            userRegistrationList.add(line);
        }
        br.close();

        for (String userRegistration : userRegistrationList) {
            String[] parts = userRegistration.split(",");
            if (tab == 2) {
                model.addRow(new Object[]{parts[0], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8]});
            } else {
                model.addRow(new Object[]{parts[0], parts[2], parts[3], parts[4], parts[5]});
            }
        }

        setupTableSorter(table);
    }

    public void updateRate() throws IOException {
        int updateType = JOptionPane.showOptionDialog(null, "Please select room type to update rate.", "Update Rate", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Single Room", "Double Sharing Room", "Triple Sharing Room"}, "Single Room");
        if (updateType == JOptionPane.CLOSED_OPTION) {
            return;
        }
        int newRate = Integer.valueOf(JOptionPane.showInputDialog(null, "Please enter new rate for the room type:", "Update Rate", JOptionPane.PLAIN_MESSAGE));
        if (newRate < 0) {
            JOptionPane.showMessageDialog(null, "Rate cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader("Room_Type.txt"));

        String line;
        ArrayList roomType = new ArrayList();
        while ((line = br.readLine()) != null) {
            roomType.add(line);
        }

        br.close();

        StringJoiner sj = new StringJoiner(",");

        if (updateType == 0) {
            sj.add("Single Room");
            sj.add(String.valueOf(newRate));
            roomType.set(0, sj);
        } else if (updateType == 1) {
            sj.add("Double Sharing Room");
            sj.add(String.valueOf(newRate));
            roomType.set(1, sj);
        } else if (updateType == 2) {
            sj.add("Triple Sharing Room");
            sj.add(String.valueOf(newRate));
            roomType.set(2, sj);
        }

        sj = new StringJoiner(System.lineSeparator());

        for (int i = 0; i < roomType.size(); i++) {
            sj.add(roomType.get(i).toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("Room_Type.txt"));
        bw.write(sj.toString());
        bw.close();

        JOptionPane.showMessageDialog(null, "Room rate has been updated successfully.", "Update Rate", JOptionPane.INFORMATION_MESSAGE);
    }

    public void updateRoomAvailability() throws IOException {
        DefaultTableModel model = (DefaultTableModel) RoomInfoTable.getModel();
        int row = RoomInfoTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String roomNumber = RoomInfoTable.getValueAt(row, 0).toString();
        int availablity = Integer.valueOf(RoomInfoTable.getValueAt(row, 2).toString());

        String newAvailibility = JOptionPane.showInputDialog(null, "Please enter new availability for the room:", "Update Room Availability", JOptionPane.PLAIN_MESSAGE);

        if (newAvailibility == null) {
            return;
        } else if (newAvailibility.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Availability cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (Integer.valueOf(newAvailibility) < 0) {
            JOptionPane.showMessageDialog(null, "Availability cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.setValueAt(newAvailibility, row, 2);

        BufferedReader br = new BufferedReader(new FileReader("Room_Info.txt"));

        String line;
        ArrayList<String> roomInfoList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            roomInfoList.add(line);
        }

        for (int i = 0; i < roomInfoList.size(); i++) {
            String[] parts = roomInfoList.get(i).toString().split(",");
            if (parts[0].equals(roomNumber)) {
                roomInfoList.set(i, parts[0] + "," + parts[1] + "," + newAvailibility + "," + parts[3]);
            }
        }

        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < roomInfoList.size(); i++) {
            sj.add(roomInfoList.get(i).toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("Room_Info.txt"));
        bw.write(sj.toString());
        bw.close();

        JOptionPane.showMessageDialog(null, "Room availability has been updated successfully.", "Update Room Availability", JOptionPane.INFORMATION_MESSAGE);
    }

    public void viewRoomInfoDetails() throws IOException {
        DefaultTableModel model = (DefaultTableModel) RoomInfoTable.getModel();
        int row = RoomInfoTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String roomNumber = RoomInfoTable.getValueAt(row, 0).toString();
        String roomType = RoomInfoTable.getValueAt(row, 1).toString();
        String availability = RoomInfoTable.getValueAt(row, 2).toString();
        String rate = RoomInfoTable.getValueAt(row, 3).toString();
        ArrayList roomMemberList = new ArrayList();

        BufferedReader br = new BufferedReader(new FileReader("Resident_Info.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[7].equals(roomNumber)) {
                roomMemberList.add(parts[1]);
            }
        }
        br.close();

        StringJoiner roomMember = new StringJoiner(", ");
        for (int i = 0; i < roomMemberList.size(); i++) {
            roomMember.add(roomMemberList.get(i).toString());
        }

        String roomInfo = "Room Number: " + roomNumber + "\nRoom Type: " + roomType + "\nAvailability: " + availability + "\nRate: " + rate + "\nRoom Members: " + roomMember;

        JOptionPane.showMessageDialog(null, roomInfo, "Room Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchRoomInfo() {
        String input = JOptionPane.showInputDialog(null, "Please enter any details to search room:", "Search Room", JOptionPane.PLAIN_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) RoomInfoTable.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + input));
            RoomInfoTable.setRowSorter(sorter);

        } else if (input == null) {
            return;

        }  else if (input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search input cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshRoomInfo() throws IOException {
        DefaultTableModel model = (DefaultTableModel) RoomInfoTable.getModel();
        JTable table = RoomInfoTable;

        table.setRowSorter(null);
        model.setRowCount(0);

        String[] rateList = new String[3];

        BufferedReader br = new BufferedReader(new FileReader("Room_Type.txt"));
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            rateList[i] = parts[1];
            i++;
        }

        br = new BufferedReader(new FileReader("Room_Info.txt"));
        ArrayList<String> roomInfoList = new ArrayList<>();
        String rate = "";
        line = "";
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[1].equals("Single Room")) {
                rate = rateList[0];
            } else if (parts[1].equals("Double Sharing Room")) {
                rate = rateList[1];
            } else if (parts[1].equals("Triple Sharing Room")) {
                rate = rateList[2];
            }
            model.addRow(new Object[]{parts[0], parts[1], parts[2], rate});
        }
        br.close();

        setupTableSorter(table);
    }

    public void viewRoomChangeDetails() {
        DefaultTableModel model = (DefaultTableModel) RoomChangeRequestTable.getModel();
        int row = RoomChangeRequestTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String requestID = RoomChangeRequestTable.getValueAt(row, 0).toString();
        String residentID = RoomChangeRequestTable.getValueAt(row, 1).toString();
        String residentName = RoomChangeRequestTable.getValueAt(row, 2).toString();
        String gender = RoomChangeRequestTable.getValueAt(row, 3).toString();
        String currentRoomNumber = RoomChangeRequestTable.getValueAt(row, 4).toString();
        String currentRoomType = RoomChangeRequestTable.getValueAt(row, 5).toString();
        String newRoomType = RoomChangeRequestTable.getValueAt(row, 6).toString();
        String description = RoomChangeRequestTable.getValueAt(row, 7).toString();

        String roomChangeInfo = "Request ID: " + requestID + "\nResident ID: " + residentID + "\nResident Name: " + residentName + "\nGender: " + gender
                + "\nCurrent Room Number: " + currentRoomNumber + "\nCurrent Room Type: " + currentRoomType + "\nNew Room Type: " + newRoomType
                + "\nDescription: " + description;

        JOptionPane.showMessageDialog(null, roomChangeInfo, "Room Change Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void approveRoomChange() throws IOException {
        DefaultTableModel model = (DefaultTableModel) RoomChangeRequestTable.getModel();
        int row = RoomChangeRequestTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String requestID = RoomChangeRequestTable.getValueAt(row, 0).toString();
        String residentID = RoomChangeRequestTable.getValueAt(row, 1).toString();
        String residentName = RoomChangeRequestTable.getValueAt(row, 2).toString();
        String currentRoomNumber = RoomChangeRequestTable.getValueAt(row, 4).toString();
        String newRoomType = RoomChangeRequestTable.getValueAt(row, 6).toString();
        String status = RoomChangeRequestTable.getValueAt(row, 8).toString();

        if (status.equalsIgnoreCase("Approved")) {
            JOptionPane.showMessageDialog(null, "Room change request has already been approved.", "Approve Room Change", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (status.equalsIgnoreCase("Rejected")) {
            JOptionPane.showMessageDialog(null, "Room change request has already been rejected.", "Approve Room Change", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            model.setValueAt("Approved", row, 8);

            BufferedReader br = new BufferedReader(new FileReader("Change_Room.txt"));
            String line;
            ArrayList roomChangeList = new ArrayList();
            while ((line = br.readLine()) != null) {
                roomChangeList.add(line);
            }

            for (int i = 0; i < roomChangeList.size(); i++) {
                String[] parts = roomChangeList.get(i).toString().split(",");
                if (parts[0].equals(requestID)) {
                    roomChangeList.set(i, parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + parts[7] + "," + "Approved");
                }
            }

            StringJoiner sj = new StringJoiner(System.lineSeparator());

            for (int i = 0; i < roomChangeList.size(); i++) {
                sj.add(roomChangeList.get(i).toString());
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("Change_Room.txt"));
            bw.write(sj.toString());
            bw.close();

            // update room info
            BufferedReader roomInfoReader = new BufferedReader(new FileReader("Room_Info.txt"));
            ArrayList roomInfoList = new ArrayList();

            while ((line = roomInfoReader.readLine()) != null) {
                roomInfoList.add(line);
            }

            for (int i = 0; i < roomInfoList.size(); i++) {
                String[] parts = roomInfoList.get(i).toString().split(",");
                if (parts[0].equals(currentRoomNumber)) {
                    roomInfoList.set(i, parts[0] + "," + parts[1] + "," + (Integer.valueOf(parts[2]) + 1) + "," + parts[3]);
                    break;
                }
            }

            String newRoomNumber = "";
            for (int i = 0; i < roomInfoList.size(); i++) {
                String[] parts = roomInfoList.get(i).toString().split(",");
                newRoomNumber = parts[1];
                if (newRoomNumber.contains(newRoomType)) {
                    roomInfoList.set(i, parts[0] + "," + parts[1] + "," + (Integer.valueOf(parts[2]) - 1) + "," + parts[3]);
                    break;
                }
            }

            sj = new StringJoiner(System.lineSeparator());
            for (int i = 0; i < roomInfoList.size(); i++) {
                sj.add(roomInfoList.get(i).toString());
            }

            bw = new BufferedWriter(new FileWriter("Room_Info.txt"));
            bw.write(sj.toString());
            bw.close();

            JOptionPane.showMessageDialog(null, "Room change request has been approved successfully.", "Approve Room Change", JOptionPane.INFORMATION_MESSAGE);

            // update resident info
            BufferedReader residentInfoReader = new BufferedReader(new FileReader("Resident_Info.txt"));
            ArrayList residentInfoList = new ArrayList();

            while ((line = residentInfoReader.readLine()) != null) {
                residentInfoList.add(line);
            }

            for (int i = 0; i < residentInfoList.size(); i++) {
                String[] parts = residentInfoList.get(i).toString().split(",");
                if (parts[7].equals(newRoomNumber)) {
                    residentInfoList.set(i, parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + newRoomNumber + "," + newRoomType + "," + parts[9]);
                    break;
                }
            }

            sj = new StringJoiner(System.lineSeparator());
            for (int i = 0; i < residentInfoList.size(); i++) {
                sj.add(residentInfoList.get(i).toString());
            }

            bw = new BufferedWriter(new FileWriter("Resident_Info.txt"));
            bw.write(sj.toString());
            bw.close();
        }
    }

    public void rejectRoomChange() throws IOException {
        DefaultTableModel model = (DefaultTableModel) RoomChangeRequestTable.getModel();
        int row = RoomChangeRequestTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String requestID = RoomChangeRequestTable.getValueAt(row, 0).toString();
        String residentID = RoomChangeRequestTable.getValueAt(row, 1).toString();
        String residentName = RoomChangeRequestTable.getValueAt(row, 2).toString();

        model.setValueAt("Rejected", row, 8);

        BufferedReader br = new BufferedReader(new FileReader("Change_Room.txt"));
        String line;
        ArrayList roomInfoList = new ArrayList();
        while ((line = br.readLine()) != null) {
            roomInfoList.add(line);
        }

        for (int i = 0; i < roomInfoList.size(); i++) {
            String[] parts = roomInfoList.get(i).toString().split(",");
            if (parts[0].equals(requestID)) {
                roomInfoList.set(i, parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + parts[7] + "," + "Rejected");
            }
        }

        StringJoiner sj = new StringJoiner(System.lineSeparator());

        for (int i = 0; i < roomInfoList.size(); i++) {
            sj.add(roomInfoList.get(i).toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("Change_Room.txt"));
        bw.write(sj.toString());
        bw.close();

        JOptionPane.showMessageDialog(null, "Room change request has been rejected successfully.", "Reject Room Change", JOptionPane.INFORMATION_MESSAGE);
    }

    public void searchRoomChange() {
        String input = JOptionPane.showInputDialog(null, "Please enter any details to search room change request:", "Search Room Change Request", JOptionPane.PLAIN_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) RoomChangeRequestTable.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + input));
            RoomChangeRequestTable.setRowSorter(sorter);
        } else if (input == null) {
            return;
        } else if (input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search input cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void viewReceipt() {
        java.util.List<String> receipts = new ArrayList<>();
        List<String> displayReceipts = new ArrayList<>();
        JTable table = PaymentRecordTable;
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "No row selected. Please select a row.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String residentID = table.getValueAt(row, 2).toString();

        try (BufferedReader reader = new BufferedReader(new FileReader("Receipt.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(table.getValueAt(row, 0).toString())) {
                    JOptionPane.showMessageDialog(null, String.format("ReceiptID: %s\nDate of Issue: %s\nResident Name: %s\nRoom Number: %s\nRoom Type: %s\nStaff in Charge: %s",
                            parts[0], parts[1], parts[3], parts[4], parts[5], parts[7]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Receipt.txt: " + e.getMessage());
            return;
        }

    }

    public void refreshRoomChangeRequest() throws IOException {
        DefaultTableModel model = (DefaultTableModel) RoomChangeRequestTable.getModel();
        JTable table = RoomChangeRequestTable;

        table.setRowSorter(null);
        model.setRowCount(0);

        BufferedReader br = new BufferedReader(new FileReader("Change_Room.txt"));
        ArrayList<String> requestList = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            model.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8]});
        }

        br.close();

        setupTableSorter(table);
    }

    public void searchPayment() {
        String input = JOptionPane.showInputDialog(null, "Please enter any details to search payment record:", "Search Payment Record", JOptionPane.PLAIN_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) PaymentRecordTable.getModel();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + input));
            PaymentRecordTable.setRowSorter(sorter);
        } else if (input == null) {
            return;
        } else if (input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Search input cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshPaymentRecord() throws IOException {
        DefaultTableModel model = (DefaultTableModel) PaymentRecordTable.getModel();
        JTable table = PaymentRecordTable;

        table.setRowSorter(null);
        model.setRowCount(0);

        BufferedReader br = new BufferedReader(new FileReader("Payment_Records.txt"));
        ArrayList<String> paymentList = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            model.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]});
        }

        br.close();

        setupTableSorter(table);
    }

    public void editProfile() throws IOException {
        String[] myInfo = new String[6];
        myInfo[0] = manager.getId();
        myInfo[1] = manager.getUsername();
        myInfo[2] = manager.getPassword();
        myInfo[3] = manager.getName();
        myInfo[4] = manager.getContactNumber();
        myInfo[5] = manager.getEmail();

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
            } else if (!manager.isUsernameUnique(newUsername)) {
                JOptionPane.showMessageDialog(null, "This username already exists. Please enter other username.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[1] = newUsername;
            manager.setUsername(newUsername);
            UsernameField.setText(newUsername);

        } else if (attribute == 1) {
            String newPassword = JOptionPane.showInputDialog(null, "Please enter new password: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newPassword == null) {
                return;
            } else if (newPassword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!manager.validatePassword(newPassword)) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit and one special character.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newConfirmPassword = JOptionPane.showInputDialog(null, "Please re-enter new password: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newPassword.equals(newConfirmPassword)) {
                myInfo[2] = newPassword;
                manager.setPassword(newPassword);
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
            } else if (!manager.validateName(newName)) {
                JOptionPane.showMessageDialog(null, "Name must only contain alphabets.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[3] = newName;
            manager.setName(newName);
            NameField.setText(newName);

        } else if (attribute == 3) {
            String newContact = JOptionPane.showInputDialog(null, "Please enter new contact number: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newContact == null) {
                return;
            } else if (newContact.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Contact number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!manager.validateContactNumber(newContact)) {
                JOptionPane.showMessageDialog(null, "Invalid contact number. Please enter a valid contact number between 9 and 11 digit without any special characters");
                return;
            } else if (!manager.isContactNumberUnique(newContact)) {
                JOptionPane.showMessageDialog(null, "This contact number already exists. Please enter other contact number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            myInfo[4] = newContact;
            manager.setContactNumber(newContact);
            ContactField.setText(newContact);

        } else if (attribute == 4) {
            String newEmail = JOptionPane.showInputDialog(null, "Please enter new email address: ", "Edit Profile", JOptionPane.PLAIN_MESSAGE);
            if (newEmail == null) {
                return;
            } else if (newEmail.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email address cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (manager.validateEmail(newEmail) == false) {
                JOptionPane.showMessageDialog(null, "Invalid email address format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (manager.isEmailUnique(newEmail) == false) {
                JOptionPane.showMessageDialog(null, "This email address already exists. Please enter other email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            myInfo[5] = newEmail;
            manager.setEmail(newEmail);
            EmailField.setText(newEmail);
        }

        StringJoiner sj = new StringJoiner(",");

        for (int i = 0; i < myInfo.length; i++) {
            sj.add(myInfo[i]);
        }

        BufferedReader br = new BufferedReader(new FileReader("Manager_Info.txt"));
        String line;
        ArrayList managerInfo = new ArrayList();
        while ((line = br.readLine()) != null) {
            managerInfo.add(line);
        }

        for (int i = 0; i < managerInfo.size(); i++) {
            String[] parts = managerInfo.get(i).toString().split(",");
            if (parts[0].equals(manager.getId())) {
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
        SearchUserInfoButton = new JButton();
        RefreshUserInfoButton = new JButton();
        RegistrationPanel = new JPanel();
        RegistrationRequestTab = new JTabbedPane();
        jScrollPane10 = new JScrollPane();
        ManagerRegistrationTable = new JTable();
        jScrollPane11 = new JScrollPane();
        StaffRegistrationTable = new JTable();
        jScrollPane12 = new JScrollPane();
        ResidentRegistrationTable = new JTable();
        SearchRegistrationButton = new JButton();
        ApproveRegistrationButton = new JButton();
        RejectRegistrationButton = new JButton();
        RefreshRegistrationButton = new JButton();
        RoomInformationPanel = new JPanel();
        jScrollPane2 = new JScrollPane();
        RoomInfoTable = new JTable();
        UpdateRoomAvailabilityButton = new JButton();
        ViewRoomInfoDetailsButton = new JButton();
        SearchRoomInfoButton = new JButton();
        RefreshRoomInfoButton = new JButton();
        RoomChangeRequestPanel = new JPanel();
        jScrollPane3 = new JScrollPane();
        RoomChangeRequestTable = new JTable();
        RejectRoomChangeButton = new JButton();
        ApproveRoomChangeButton = new JButton();
        ViewRoomChangeDetailsButton = new JButton();
        SearchRoomChangeButton = new JButton();
        RefreshRoomChangeRequestButton = new JButton();
        PaymentRecordPanel = new JPanel();
        jScrollPane5 = new JScrollPane();
        PaymentRecordTable = new JTable();
        ViewReceiptButton = new JButton();
        SearchPaymentButton = new JButton();
        RefreshPaymentButton = new JButton();
        ProfilePanel = new JPanel();
        UpdateRateButton = new JButton();
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
                        "Manager ID", "Username", "Name", "Contact Number", "Email Address"
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

        setupTableSorter(ManagerInfoTable);

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
        setupTableSorter(StaffInfoTable);

        ResidentInfoTable.setModel(new DefaultTableModel(
                toUserInfoTable(3),
                new String [] {
                        "Resident ID", "Username", "Name", "Contact Number", "Email Address", "Gender", "Room Number", "Room Type", "Overdue Amount"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
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
        setupTableSorter(ResidentInfoTable);

        AddUserButton.setText("Add User");
        AddUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AddUserButtonActionPerformed(evt);
            }
        });

        DeleteUserButton.setText("Delete User");
        DeleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    DeleteUserButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        EditUserButton.setText("Edit User");
        EditUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    EditUserButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        SearchUserInfoButton.setText("Search User");
        SearchUserInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    SearchUserInfoButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        RefreshUserInfoButton.setText("Refresh");
        RefreshUserInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    RefreshUserInfoButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        GroupLayout UserInfoPanelLayout = new GroupLayout(UserInfoPanel);
        UserInfoPanel.setLayout(UserInfoPanelLayout);
        UserInfoPanelLayout.setHorizontalGroup(
                UserInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, UserInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(UserInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(UserInfoTab, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                                        .addGroup(UserInfoPanelLayout.createSequentialGroup()
                                                .addComponent(AddUserButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(DeleteUserButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(EditUserButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(RefreshUserInfoButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                        .addComponent(SearchUserInfoButton)
                                        .addComponent(RefreshUserInfoButton))
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

        RegistrationRequestTab.addTab("Manager", jScrollPane10);

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

        SearchRegistrationButton.setText("Search Registration");
        SearchRegistrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchRegistrationButtonActionPerformed(evt);
            }
        });

        ApproveRegistrationButton.setText("Approve");
        ApproveRegistrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    ApproveRegistrationButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        RejectRegistrationButton.setText("Reject");
        RejectRegistrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    RejectRegistrationButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        RefreshRegistrationButton.setText("Refresh");
        RefreshRegistrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    RefreshRegistrationButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(RejectRegistrationButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(RefreshRegistrationButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                        .addComponent(SearchRegistrationButton)
                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(ApproveRegistrationButton)
                                                .addComponent(RejectRegistrationButton)
                                                .addComponent(RefreshRegistrationButton)))
                                .addContainerGap(42, Short.MAX_VALUE))
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

        UpdateRoomAvailabilityButton.setText("Update Availability");
        UpdateRoomAvailabilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    UpdateRoomAvailabilityButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        UpdateRateButton.setText("Update Rate");
        UpdateRateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    UpdateRateButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ViewRoomInfoDetailsButton.setText("View Details");
        ViewRoomInfoDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    ViewRoomInfoDetailsButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        SearchRoomInfoButton.setText("Search Room");
        SearchRoomInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchRoomInfoButtonActionPerformed(evt);
            }
        });

        RefreshRoomInfoButton.setText("Refresh");
        RefreshRoomInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    RefreshRoomInfoButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        GroupLayout RoomInformationPanelLayout = new GroupLayout(RoomInformationPanel);
        RoomInformationPanel.setLayout(RoomInformationPanelLayout);
        RoomInformationPanelLayout.setHorizontalGroup(
                RoomInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(RoomInformationPanelLayout.createSequentialGroup()
                                .addGroup(RoomInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(RoomInformationPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(UpdateRateButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(UpdateRoomAvailabilityButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(RefreshRoomInfoButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ViewRoomInfoDetailsButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SearchRoomInfoButton))
                                        .addGroup(RoomInformationPanelLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        RoomInformationPanelLayout.setVerticalGroup(
                RoomInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(RoomInformationPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RoomInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(UpdateRoomAvailabilityButton)
                                        .addComponent(UpdateRateButton)
                                        .addComponent(ViewRoomInfoDetailsButton)
                                        .addComponent(SearchRoomInfoButton)
                                        .addComponent(RefreshRoomInfoButton))
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
                try {
                    RejectRoomChangeButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ApproveRoomChangeButton.setText("Approve");
        ApproveRoomChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    ApproveRoomChangeButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ViewRoomChangeDetailsButton.setText("View Details");
        ViewRoomChangeDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewRoomChangeDetailsButtonActionPerformed(evt);
            }
        });

        SearchRoomChangeButton.setText("Search Request");
        SearchRoomChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchRoomChangeButtonActionPerformed(evt);
            }
        });

        RefreshRoomChangeRequestButton.setText("Refresh");
        RefreshRoomChangeRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    RefreshRoomChangeRequestButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        GroupLayout RoomChangeRequestPanelLayout = new GroupLayout(RoomChangeRequestPanel);
        RoomChangeRequestPanel.setLayout(RoomChangeRequestPanelLayout);
        RoomChangeRequestPanelLayout.setHorizontalGroup(
                RoomChangeRequestPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(RoomChangeRequestPanelLayout.createSequentialGroup()
                                .addGroup(RoomChangeRequestPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(RoomChangeRequestPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(ApproveRoomChangeButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(RejectRoomChangeButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(RefreshRoomChangeRequestButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ViewRoomChangeDetailsButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SearchRoomChangeButton))
                                        .addGroup(RoomChangeRequestPanelLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        RoomChangeRequestPanelLayout.setVerticalGroup(
                RoomChangeRequestPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(RoomChangeRequestPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RoomChangeRequestPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(RejectRoomChangeButton)
                                        .addComponent(ApproveRoomChangeButton)
                                        .addComponent(ViewRoomChangeDetailsButton)
                                        .addComponent(SearchRoomChangeButton)
                                        .addComponent(RefreshRoomChangeRequestButton))
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

        ViewReceiptButton.setText("View Receipt");
        ViewReceiptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    ViewReceiptButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        SearchPaymentButton.setText("Search Record");
        SearchPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchPaymentButtonActionPerformed(evt);
            }
        });

        RefreshPaymentButton.setText("Refresh");
        RefreshPaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    RefreshPaymentButtonActionPerformed(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        GroupLayout PaymentRecordPanelLayout = new GroupLayout(PaymentRecordPanel);
        PaymentRecordPanel.setLayout(PaymentRecordPanelLayout);
        PaymentRecordPanelLayout.setHorizontalGroup(
                PaymentRecordPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(PaymentRecordPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(RefreshPaymentButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ViewReceiptButton)
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
                                        .addComponent(ViewReceiptButton)
                                        .addComponent(SearchPaymentButton)
                                        .addComponent(RefreshPaymentButton))
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
        ManagerIDField.setText(manager.getId());

        UsernameField.setEditable(false);
        UsernameField.setText(manager.getUsername());


        PasswordField.setEditable(false);
        PasswordField.setText(manager.getPassword());

        NameField.setEditable(false);
        NameField.setText(manager.getName());

        ContactField.setEditable(false);
        ContactField.setText(manager.getContactNumber());

        EmailField.setEditable(false);
        EmailField.setText(manager.getEmail());

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

    private void AddUserButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        int tab = UserInfoTab.getSelectedIndex();

        if (tab == 1) {
            ManagerAddStaffPage addStaffPage = new ManagerAddStaffPage(manager);
            addStaffPage.setVisible(true);
        } else if (tab == 2) {
            ManagerAddResidentPage addResidentPage = new ManagerAddResidentPage(manager);
            addResidentPage.setVisible(true);
        } else if (tab == 0) {
            ManagerAddManagerPage addManagerPage = new ManagerAddManagerPage(manager);
            addManagerPage.setVisible(true);
        }

    }

    private void DeleteUserButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        deleteUserInfo();
    }

    private void EditUserButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        editUserInfo();
    }

    private void SearchUserInfoButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        searchUserInfo();
    }

    private void ApproveRegistrationButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        approveRegistration();
    }

    private void RejectRegistrationButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        rejectRegistration();
    }

    private void SearchRegistrationButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        searchRegistration();
    }

    private void UpdateRateButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        updateRate();
    }

    private void UpdateRoomAvailabilityButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        updateRoomAvailability();
    }

    private void ViewRoomInfoDetailsButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        viewRoomInfoDetails();
    }

    private void SearchRoomInfoButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        searchRoomInfo();
    }

    private void ViewRoomChangeDetailsButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        viewRoomChangeDetails();
    }

    private void ApproveRoomChangeButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        approveRoomChange();
    }

    private void RejectRoomChangeButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        rejectRoomChange();
    }

    private void SearchRoomChangeButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        searchRoomChange();
    }

    private void ViewReceiptButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        viewReceipt();
    }

    private void SearchPaymentButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        searchPayment();
    }

    private void EditProfileButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        editProfile();
    }

    private void LogOutButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == 0) {
            JOptionPane.showMessageDialog(null, "You have successfully logged out.\nThank you for using APU Hostel Management Payment System.", "Log Out", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
        }
    }

    private void RefreshRegistrationButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        refreshRegistration();
    }

    private void RefreshRoomInfoButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        refreshRoomInfo();
    }

    private void RefreshRoomChangeRequestButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        refreshRoomChangeRequest();
    }

    private void RefreshPaymentButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        refreshPaymentRecord();
    }
    

    private void RefreshUserInfoButtonActionPerformed(ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        refreshUserInfoTable();
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
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagerMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ManagerMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ManagerMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ManagerMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
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
    private JButton AddUserButton;
    private JButton ApproveRegistrationButton;
    private JButton ApproveRoomChangeButton;
    private JTextField ContactField;
    private JLabel ContactLabel;
    private JButton DeleteUserButton;
    private JButton EditProfileButton;
    private JButton EditUserButton;
    private JTextField EmailField;
    private JLabel EmailLabel;
    private JButton LogOutButton;
    private JPanel LogOutPanel;
    private JTextField ManagerIDField;
    private JLabel ManagerIDLabel;
    private JTable ManagerInfoTable;
    private JTabbedPane ManagerMenuTab;
    private JTable ManagerRegistrationTable;
    private JTextField NameField;
    private JLabel NameLabel;
    private JPasswordField PasswordField;
    private JLabel PasswordLabel;
    private JPanel PaymentRecordPanel;
    private JTable PaymentRecordTable;
    private JPanel ProfilePanel;
    private JButton RefreshUserInfoButton;
    private JButton RefreshPaymentButton;
    private JButton RefreshRegistrationButton;
    private JButton RefreshRoomChangeRequestButton;
    private JButton RefreshRoomInfoButton;
    private JTabbedPane RegistrationRequestTab;
    private JButton RejectRegistrationButton;
    private JButton RejectRoomChangeButton;
    private JTable ResidentRegistrationTable;
    private JTable ResidentInfoTable;
    private JPanel RoomChangeRequestPanel;
    private JTable RoomChangeRequestTable;
    private JTable RoomInfoTable;
    private JPanel RoomInformationPanel;
    private JButton SearchPaymentButton;
    private JButton SearchRegistrationButton;
    private JButton SearchRoomChangeButton;
    private JButton SearchRoomInfoButton;
    private JButton SearchUserInfoButton;
    private JTable StaffInfoTable;
    private JTable StaffRegistrationTable;
    private JButton UpdateRoomAvailabilityButton;
    private JTabbedPane UserInfoTab;
    private JTextField UsernameField;
    private JLabel UsernameLabel;
    private JButton ViewReceiptButton;
    private JButton ViewRoomChangeDetailsButton;
    private JButton ViewRoomInfoDetailsButton;
    private JPanel RegistrationPanel;
    private JButton UpdateRateButton;
    private JPanel UserInfoPanel;
    private JScrollPane jScrollPane10;
    private JScrollPane jScrollPane11;
    private JScrollPane jScrollPane12;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane5;
    private JScrollPane jScrollPane7;
    private JScrollPane jScrollPane8;
    private JScrollPane jScrollPane9;
    // End of variables declaration
}