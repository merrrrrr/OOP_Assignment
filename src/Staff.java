import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Staff extends User {
    private String username;
    private String password;

    public Staff() {
        this.username = "staff";
        this.password = "password";
    }

    public Staff(String id, String username, String password, String name, String contactNumber, String email) {
        super(id, username, password, name, contactNumber, email);
    }

    @Override
    public String toString() {
        return "Staff{id = " + getId()
                + ", username = " + getUsername()
                + ", password = " + getPassword()
                + ", name = " + getName()
                + ", contactNumber = " + getContactNumber()
                + ", email = " + getEmail() + "}";
    }

    Scanner sc = new Scanner(System.in);

    public void register() throws IOException {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Contact Number: ");
        String contactNumber = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        BufferedWriter bw = new BufferedWriter(new FileWriter("Staff_Registration.txt", true));
        String register = (username + "," + password + "," + name + "," + contactNumber + "," + email);
        System.out.println("Your registration request has been sent to manager for approval.");
        bw.write(register);
        bw.newLine();

        bw.close();
    }

    public boolean login() throws IOException {
        try (BufferedReader staffInfo = new BufferedReader(new FileReader("Staff_Info.txt"))) {
            System.out.print("Username: ");
            String userEnteredUsername = sc.next().trim();
            sc.nextLine(); // Consume newline
            System.out.print("Password: ");
            String userEnteredPassword = sc.next().trim();
            sc.nextLine(); // Consume newline

            String lines;
            boolean login = false;

            while ((lines = staffInfo.readLine()) != null) {
                String[] line = lines.split(",");
                if (line.length < 6) {
                    continue; // Skip lines that do not have the expected number of fields
                }
                String username = line[1].trim();
                String password = line[2].trim();

                if (username.equals(userEnteredUsername) && password.equals(userEnteredPassword)) {
                    System.out.println("Welcome! " + line[3].trim());
                    this.username = username; // Set the logged-in username
                    this.password = password; // Set the logged-in password
                    login = true;
                    break;
                }
            }

            if (!login) {
                System.out.println("Invalid Username or Password.");
            }

            return login;
        }
    }

    public void modify() {
        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Staff_Info.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            boolean login = false;
            for (int i = 0; i < lines.size(); i++) {
                String[] details = lines.get(i).split(",");
                if (details.length < 6) {
                    continue;
                }

                String username = details[1].trim();
                String password = details[2].trim();

                if (details[1].equals(username) && details[2].equals(password)) {
                    System.out.print("New Username (enter 'e' to skip): ");
                    String newUsername = sc.nextLine().trim();
                    if (newUsername.equals("e")) newUsername = details[1];

                    System.out.print("New Password (enter 'e' to skip): ");
                    String newPassword = sc.nextLine().trim();
                    if (newPassword.equals("e")) newPassword = details[2];

                    System.out.print("New Name (enter 'e' to skip): ");
                    String newName = sc.nextLine().trim();
                    if (newName.equals("e")) newName = details[3];

                    System.out.print("New Contact Number (enter 'e' to skip): ");
                    String newContactNumber = sc.nextLine().trim();
                    if (newContactNumber.equals("e")) newContactNumber = details[4];

                    System.out.print("New Email (enter 'e' to skip): ");
                    String newEmail = sc.nextLine().trim();
                    if (newEmail.equals("e")) newEmail = details[5];

                    lines.set(i, details[0] + "," + newUsername + "," + newPassword + "," + newName + "," + newContactNumber + "," + newEmail);
                    login = true;
                    break;
                }
            }

            if (login) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Info.txt"))) {
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