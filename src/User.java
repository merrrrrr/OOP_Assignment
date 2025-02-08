import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.regex.Pattern;

public class User {
    private String id;
    private String username;
    private String password;
    private String name;
    private String contactNumber;
    private String email;

    public User() {
    }

    public User(String id, String username, String password, String name, String contactNumber, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean register(int userType, String registerInfo) throws IOException {
        String filename = getRegisterFilename(userType);
        BufferedReader userRegistration = new BufferedReader(new FileReader(filename));
        BufferedWriter userRegistrationWriter = new BufferedWriter(new FileWriter(filename, true));

        if (userRegistration.read() != -1) {
            userRegistrationWriter.newLine();
        }

        userRegistrationWriter.write(registerInfo);

        userRegistrationWriter.close();
        userRegistration.close();

        return true;
    }

    public String login(int userType, String usernameInput, String passwordInput) throws IOException {
        String filename = getInfoFilename(userType);
        BufferedReader userInfo = new BufferedReader(new FileReader(filename));

        String lines;

        while (((lines = userInfo.readLine()) != null)) {
            String[] line = lines.split(",");
            String username = line[1];
            String password = line[2];

            if (username.equals(usernameInput) && password.equals(passwordInput)) {
                return lines;
            }

        }

        userInfo.close();
        return null;
    }

    public String getInfoFilename(int userType) {
        String filename = "";

        if (userType == 1) {
            filename = "Manager_Info.txt";
        } else if (userType == 2) {
            filename = "Staff_Info.txt";
        } else if (userType == 3) {
            filename = "Resident_Info.txt";
        }

        return filename;
    }

    public String getRegisterFilename(int userType) {
        String filename = "";

        if (userType == 1) {
            filename = "Manager_Registration.txt";
        } else if (userType == 2) {
            filename = "Staff_Registration.txt";
        } else if (userType == 3) {
            filename = "Resident_Registration.txt";
        }

        return filename;
    }

    public boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return name.matches("[a-zA-Z ]+");
    }

    public boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c) && c != ',') {
                hasSpecialChar = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar;
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

    public boolean isUsernameUnique(String username) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Manager_Info.txt"));
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

        br = new BufferedReader(new FileReader("Staff_Info.txt"));
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

        br = new BufferedReader(new FileReader("Resident_Info.txt"));
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

        br = new BufferedReader(new FileReader("Manager_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[0];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Staff_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[0];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[0];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isEmailUnique(String email) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Manager_Info.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[5];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();

        br = new BufferedReader(new FileReader("Staff_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[5];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[5];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Manager_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[4];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Staff_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[4];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[4];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isContactNumberUnique(String contactNumber) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Manager_Info.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[4];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();

        br = new BufferedReader(new FileReader("Staff_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[4];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[4];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Manager_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[3];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Staff_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[3];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[3];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        return true;
    }

    public void updateOverdueAmount() throws IOException {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        BufferedReader overdueReader = new BufferedReader(new FileReader("Overdue_Update.txt"));

        ArrayList<String> overdueList = new ArrayList<>();

        String line;
        while ((line = overdueReader.readLine()) != null) {
            overdueList.add(line);

            String parts[] = line.split(",");
            String chargeType = parts[0];
            int updateMonth = Integer.valueOf(parts[1]);
            int updateYear = Integer.valueOf(parts[2]);

            if ((updateMonth == currentMonth) && (updateYear == currentYear) && chargeType.equals("Overdue")) {
                return;
            }
        }

        BufferedReader paymentRecordReader = new BufferedReader(new FileReader("Payment_Records.txt"));

        ArrayList<String> paymentRecordList = new ArrayList<>();

        while ((line = paymentRecordReader.readLine()) != null) {
            paymentRecordList.add(line);
        }

        paymentRecordReader.close();

        ArrayList<String> paidResidentList = new ArrayList<String>();
        for (int i = 0; i < paymentRecordList.size(); i++) {
            String parts[] = paymentRecordList.get(i).toString().split(",");

            if (parts[6].substring(5,7).equals(String.valueOf(currentMonth))) {
                paidResidentList.add(parts[1]);
            }
        }

        BufferedReader roomTypeReader = new BufferedReader(new FileReader("Room_Type.txt"));
        ArrayList<String> roomTypeList = new ArrayList<String>();

        while ((line = roomTypeReader.readLine()) != null) {
            roomTypeList.add(line);
        }


        BufferedReader residentInfoReader = new BufferedReader(new FileReader("Resident_Info.txt"));
        ArrayList residentInfoList = new ArrayList();
        while ((line = residentInfoReader.readLine()) != null) {
            residentInfoList.add(line);
        }

        residentInfoReader.close();

        for (int i = 0; i < residentInfoList.size(); i++) {
            String parts[] = residentInfoList.get(i).toString().split(",");
            String overdue = parts[9].substring(2);

            if (!paidResidentList.contains(parts[0])) {
                double overdueAmount = Double.valueOf(overdue);

                for (int j = 0; j < roomTypeList.size(); j++) {
                    String roomTypeParts[] = roomTypeList.get(j).toString().split(",");
                    String roomType = roomTypeParts[0];
                    String rate = roomTypeParts[1];

                    if (parts[8].equals(roomType)) {
                        overdueAmount += Double.valueOf(rate);

                    }
                }
                residentInfoList.set(i, parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + parts[7] + "," + parts[8] + ",RM" + String.format("%.2f", overdueAmount));
            }
        }

        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < residentInfoList.size(); i++) {
            sj.add(residentInfoList.get(i).toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("Resident_Info.txt"));
        bw.write(sj.toString());
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader("Overdue_Update.txt"));
        bw = new BufferedWriter(new FileWriter("Overdue_Update.txt", true));

        if (br.read() != -1) {
            bw.newLine();
        }
        bw.write("Overdue," + currentMonth + "," + currentYear);

        br.close();
        bw.close();
    }

    public void updatePenaltyAmount() throws IOException {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        BufferedReader overdueReader = new BufferedReader(new FileReader("Overdue_Update.txt"));

        ArrayList<String> overdueList = new ArrayList<>();

        String line;
        while ((line = overdueReader.readLine()) != null) {
            overdueList.add(line);

            String parts[] = line.split(",");
            String chargeType = parts[0];
            int updateMonth = Integer.valueOf(parts[1]);
            int updateYear = Integer.valueOf(parts[2]);

            if ((updateMonth == currentMonth) && (updateYear == currentYear) && chargeType.equals("Penalty")) {
                return;
            }
        }

        BufferedReader paymentRecordReader = new BufferedReader(new FileReader("Payment_Records.txt"));

        ArrayList paymentRecordList = new ArrayList();

        while ((line = paymentRecordReader.readLine()) != null) {
            paymentRecordList.add(line);
        }

        paymentRecordReader.close();

        ArrayList<String> paidResidentList = new ArrayList<String>();
        for (int i = 0; i < paymentRecordList.size(); i++) {
            String parts[] = paymentRecordList.get(i).toString().split(",");

            if (parts[6].substring(5,7).equals(String.valueOf(currentMonth))) {
                paidResidentList.add(parts[1]);
            }
        }

        BufferedReader roomTypeReader = new BufferedReader(new FileReader("Room_Type.txt"));
        ArrayList<String> roomTypeList = new ArrayList<String>();

        while ((line = roomTypeReader.readLine()) != null) {
            roomTypeList.add(line);
        }


        BufferedReader residentInfoReader = new BufferedReader(new FileReader("Resident_Info.txt"));
        ArrayList residentInfoList = new ArrayList();
        while ((line = residentInfoReader.readLine()) != null) {
            residentInfoList.add(line);
        }

        residentInfoReader.close();

        for (int i = 0; i < residentInfoList.size(); i++) {
            String parts[] = residentInfoList.get(i).toString().split(",");
            String overdue = parts[9].substring(2);

            if (!paidResidentList.contains(parts[0]) && (currentDate.getDayOfMonth() > 5)) {
                double overdueAmount = Double.valueOf(overdue);

                for (int j = 0; j < roomTypeList.size(); j++) {
                    String roomTypeParts[] = roomTypeList.get(j).toString().split(",");
                    String roomType = roomTypeParts[0];
                    String rate = roomTypeParts[1];
                    double penaltyAmount = Double.valueOf(Double.valueOf(rate) * 0.1);

                    if (parts[8].equals(roomType)) {
                        overdueAmount += penaltyAmount;
                    }
                }
                residentInfoList.set(i, parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + parts[7] + "," + parts[8] + ",RM" + String.format("%.2f", overdueAmount));
            }
        }

        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < residentInfoList.size(); i++) {
            sj.add(residentInfoList.get(i).toString());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("Resident_Info.txt"));
        bw.write(sj.toString());
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader("Overdue_Update.txt"));
        bw = new BufferedWriter(new FileWriter("Overdue_Update.txt", true));

        if (br.read() != -1) {
            bw.newLine();
        }
        bw.write("Penalty," + currentMonth + "," + currentYear);

        br.close();
        bw.close();
    }

}
