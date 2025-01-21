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
    private String roomNo;
    private String username;
    private double overdueAmount;
    private boolean isLoggedIn = false;
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

    public Resident(String id, String username, String password, String name, String gender, String roomNo, String contactNumber, String email, double overdueAmount) {
        super(id, username, password, name, contactNumber, email);
        this.gender = gender;
        this.roomNo = roomNo;
        this.overdueAmount = overdueAmount;
    }

    @Override
    public String toString() {
        return "Resident{id=" + getId() +
                ", username=" + getUsername() +
                ", password=" + getPassword() +
                ", name=" + getName() +
                ", gender=" + gender +
                ", roomNo=" + roomNo +
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
            String roomType = sc.nextLine().trim();
            System.out.print("Contact Number: ");
            String contactNumber = sc.nextLine().trim();
            System.out.print("Email: ");
            String email = sc.nextLine().trim();

            String register = username + "," + password + "," + name + "," + gender + "," + roomType + "," + contactNumber + "," + email;
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
                        this.username = storedUsername;
                        this.setPassword(storedPassword);
                        this.isLoggedIn = true; // Set isLoggedIn to true
                        return true;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error during login: " + e.getMessage());
        }
        return false;
    }

    public void modify() {
        if (!isLoggedIn) { // Check if the user is logged in
            System.out.println("You need to log in first.");
            return;
        }

        try {
            // Load all resident data from the file
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            boolean userFound = false;
            for (int i = 0; i < lines.size(); i++) {
                String[] details = lines.get(i).split(",");
                if (details.length >= 9 && details[1].equals(this.username) && details[2].equals(this.getPassword())) {
                    System.out.println("Welcome, " + details[3] + "! Modify your details below. Enter 'e' to skip a field.");

                    System.out.print("New Username: ");
                    String newUsername = sc.nextLine().trim();
                    if (newUsername.equals("e")) newUsername = details[1];

                    System.out.print("New Password: ");
                    String newPassword = sc.nextLine().trim();
                    if (newPassword.equals("e")) newPassword = details[2];

                    System.out.print("New Name: ");
                    String newName = sc.nextLine().trim();
                    if (newName.equals("e")) newName = details[3];

                    System.out.print("New Gender: ");
                    String newGender = sc.nextLine().trim();
                    if (newGender.equals("e")) newGender = details[4];

                    System.out.print("New Contact Number: ");
                    String newContactNumber = sc.nextLine().trim();
                    if (newContactNumber.equals("e")) newContactNumber = details[6];

                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine().trim();
                    if (newEmail.equals("e")) newEmail = details[7];

                    String overdueAmount = details[8]; // Overdue amount remains unchanged

                    // Update the record
                    lines.set(i, String.join(",", details[0], newUsername, newPassword, newName, newGender,
                            details[5], newContactNumber, newEmail, overdueAmount));
                    userFound = true;
                    break;
                }
            }

            if (userFound) {
                // Write updated data back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resident_Info.txt"))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                System.out.println("Your details have been updated successfully!");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } catch (IOException e) {
            System.err.println("Error during modification: " + e.getMessage());
        }
    }

    public void viewOverdueAmount() {
        if (!isLoggedIn) { // Check if the user is logged in
            System.out.println("You need to log in first.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 9 && userDetails[1].equals(this.username)) {
                    System.out.println("Your overdue amount is: " + userDetails[8]);
                    return;
                }
            }
            System.out.println("User not found.");
        } catch (IOException e) {
            System.err.println("Error reading resident info: " + e.getMessage());
        }
    }

    public void viewPaymentRecords() {
        if (!isLoggedIn) { // Check if the user is logged in
            System.out.println("You need to log in first.");
            return;
        }

    }

    public void changingRoom() {
        if (!isLoggedIn) { // Check if the user is logged in
            System.out.println("You need to log in first.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
            String line;
            List<String> lines = new ArrayList<>();
            String currentRoomType = null;
            String residentId = null;

            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 9 && userDetails[1].equals(this.username)) {
                    currentRoomType = userDetails[5];
                    residentId = userDetails[0];
                }
                lines.add(line);
            }

            if (currentRoomType == null) {
                System.out.println("User not found.");
                return;
            }

            System.out.println("Current Room Type: " + currentRoomType);
            System.out.println("Choose new room type:");
            System.out.println("1. Single Room");
            System.out.println("2. Double Sharing Room");
            System.out.println("3. Triple Sharing Room");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            String newRoomType;
            switch (choice) {
                case 1:
                    newRoomType = "Single Room";
                    break;
                case 2:
                    newRoomType = "Double Sharing Room";
                    break;
                case 3:
                    newRoomType = "Triple Sharing Room";
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Change_Room.txt", true))) {
                writer.write(residentId + "," + "From " + currentRoomType + " to " + newRoomType);
                writer.newLine();
                System.out.println("Your room change request has been submitted for approval.");
            } catch (IOException e) {
                System.err.println("Error writing to Change_Room.txt: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error reading Resident_Info.txt: " + e.getMessage());
        }
    }
}