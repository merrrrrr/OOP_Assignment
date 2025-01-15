import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Resident extends User {
    private String gender;
    private String roomNumber;
    private String username;
    private int overdueAmount;
    private Scanner sc = new Scanner(System.in);

    public Resident() {
        super();
        try (BufferedReader residentInfo = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            String line = residentInfo.readLine();
            if (line != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 3) {
                    this.username = userDetails[1].trim();
                    this.setPassword(userDetails[2].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading resident info: " + e.getMessage());
        }
    }

    public Resident(String id, String username, String password, String name, String gender, String roomNumber, String contactNumber, String email, int overdueAmount) {
        super(id, username, password, name, contactNumber, email);
        this.gender = gender;
        this.roomNumber = roomNumber;
        this.overdueAmount = overdueAmount;
    }

    @Override
    public String toString() {
        return "Resident{id=" + getId() +
                ", username=" + getUsername() +
                ", password=" + getPassword() +
                ", name=" + getName() +
                ", gender=" + gender +
                ", roomNumber=" + roomNumber +
                ", contactNumber=" + getContactNumber() +
                ", email=" + getEmail() +
                ", overdueAmount=" + overdueAmount + "}";
    }

    public void register() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resident_Registration.txt", true))) {
            System.out.print("Username: ");
            String username = sc.nextLine().trim();
            System.out.print("Password: ");
            String password = sc.nextLine().trim();
            System.out.print("Name: ");
            String name = sc.nextLine().trim();
            System.out.print("Gender: ");
            String gender = sc.nextLine().trim();
            System.out.print("Room Type: ");
            String roomNumber = sc.nextLine().trim();
            System.out.print("Contact Number: ");
            String contactNumber = sc.nextLine().trim();
            System.out.print("Email: ");
            String email = sc.nextLine().trim();

            String register = username + "," + password + "," + name + "," + gender + "," + roomNumber + "," + contactNumber + "," + email;
            writer.write(register);
            writer.newLine();
            System.out.println("Your registration request has been sent to the manager for approval.");
        } catch (IOException e) {
            System.err.println("Error during registration: " + e.getMessage());
        }
    }

    public boolean login() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            System.out.print("Username: ");
            String inputUsername = sc.nextLine().trim();
            System.out.print("Password: ");
            String inputPassword = sc.nextLine().trim();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 3) {
                    String storedUsername = userDetails[1].trim();
                    String storedPassword = userDetails[2].trim();

                    if (inputUsername.equals(storedUsername) && inputPassword.equals(storedPassword)) {
                        System.out.println("Welcome " + userDetails[3].trim() + "!");
                        return true;
                    }
                }
            }
            System.out.println("Invalid username or password.");
        } catch (IOException e) {
            System.err.println("Error during login: " + e.getMessage());
        }
        return false;
    }

    public void modify() {
        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            boolean login = false;
            for (int i = 0; i < lines.size(); i++) {
                String[] details = lines.get(i).split(",");
                if (details.length < 9) {
                    continue;
                }

                String username = details[1].trim();
                String password = details[2].trim();

                if (username.equals(this.username) && password.equals(this.getPassword())) {
                    System.out.println("Update Information Page");

                    System.out.print("New Username (enter 'e' to skip): ");
                    String newUsername = sc.nextLine().trim();
                    if (newUsername.equals("e")) newUsername = username;

                    System.out.print("New Password (enter 'e' to skip): ");
                    String newPassword = sc.nextLine().trim();
                    if (newPassword.equals("e")) newPassword = password;

                    System.out.print("New Name (enter 'e' to skip): ");
                    String newName = sc.nextLine().trim();
                    if (newName.equals("e")) newName = details[3];

                    System.out.print("New Gender (enter 'e' to skip): ");
                    String newGender = sc.nextLine().trim();
                    if (newGender.equals("e")) newGender = details[4];

                    System.out.print("New Contact Number (enter 'e' to skip): ");
                    String newContactNumber = sc.nextLine().trim();
                    if (newContactNumber.equals("e")) newContactNumber = details[6];

                    System.out.print("New Email (enter 'e' to skip): ");
                    String newEmail = sc.nextLine().trim();
                    if (newEmail.equals("e")) newEmail = details[7];

                    int overdueAmount = Integer.parseInt(details[8].trim());

                    // Update the record
                    lines.set(i, details[0] + "," + newUsername + "," + newPassword + "," + newName + "," +
                            newGender + "," + details[5] + "," + newContactNumber + "," + newEmail + "," + overdueAmount);
                    login = true;
                    break;
                }
            }

            // Write updated lines back to the file
            if (login) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resident_Info.txt"))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                System.out.println("Information updated successfully.");
            } else {
                System.out.println("User not found or invalid credentials.");
            }

        } catch (IOException e) {
            System.err.println("Error modifying the file: " + e.getMessage());
        }
    }
}