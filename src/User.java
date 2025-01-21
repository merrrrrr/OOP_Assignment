import java.io.*;
import java.util.*;

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

    Scanner sc = new Scanner(System.in);

    public void register(String filename, User user) throws IOException {
        BufferedReader userRegistration = new BufferedReader(new FileReader(filename));
        BufferedWriter userRegistrationWriter = new BufferedWriter(new FileWriter(filename, true));

        String register = "";

        System.out.print("Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Password: ");
        String password = sc.nextLine().trim();
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Contact Number: ");
        String contactNumber = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        if (user.equals("resident")) {
            System.out.print("Gender: ");
            String gender = sc.nextLine().trim();
            System.out.print("Room Type: ");
            String roomType = sc.nextLine().trim();
            register = (username + "," + password + "," + name + "," + gender + "," + roomType + "," + contactNumber + "," + email);

        } else if (user.equals("manager") || user.equals("staff")) {
            register = (username + "," + password + "," + name + "," + contactNumber + "," + email);

        } else {
            System.out.println("Invalid user type.");
        }

        if (userRegistration.read() != -1) {
            userRegistrationWriter.newLine();
        }

        userRegistrationWriter.write(register);
        System.out.println("Your registration request has been sent to manager for approval.");

        userRegistrationWriter.close();
        userRegistration.close();
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

}
