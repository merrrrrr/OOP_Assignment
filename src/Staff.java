import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Staff extends User {
    private String username;
    private String password;
    private boolean isLoggedIn = false;
    private Scanner sc = new Scanner(System.in);

    public Staff() {
        super();
        try (BufferedReader staffInfo = new BufferedReader(new FileReader("Staff_Info.txt"))) {
            String line = staffInfo.readLine();
            if (line != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length >= 3) {
                    this.username = userDetails[1].trim();
                    this.setPassword(userDetails[2].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading staff info: " + e.getMessage());
        }
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

    public void register() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Registration.txt", true))) {
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

            String register = username + "," + password + "," + name + "," + contactNumber + "," + email;
            writer.write(register);
            writer.newLine();
            System.out.println("Your registration request has been sent to the manager for approval.");
        } catch (IOException e) {
            System.err.println("Error during registration: " + e.getMessage());
        }
    }

    public boolean login() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Staff_Info.txt"))) {
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
                        this.isLoggedIn = true; // Ensure this is set to true
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
            // Load all staff data from the file
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Staff_Info.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            boolean userFound = false;
            for (int i = 0; i < lines.size(); i++) {
                String[] details = lines.get(i).split(",");
                if (details.length >= 6 && details[1].equals(this.username)) { // Match the logged-in user
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

                    System.out.print("New Contact Number: ");
                    String newContactNumber = sc.nextLine().trim();
                    if (newContactNumber.equals("e")) newContactNumber = details[4];

                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine().trim();
                    if (newEmail.equals("e")) newEmail = details[5];

                    // Update the record
                    lines.set(i, String.join(",", details[0], newUsername, newPassword, newName, newContactNumber, newEmail));
                    this.username = newUsername; // Update the current object
                    this.setPassword(newPassword);
                    userFound = true;
                    break;
                }
            }

            if (userFound) {
                // Write updated data back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_Info.txt"))) {
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

    public void acceptRoomChange() {
        if (!isLoggedIn) { // Check if the user is logged in
            System.out.println("You need to log in first.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("Change_Room.txt"))) {
            List<String> requests = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                requests.add(line);
            }

            if (requests.isEmpty()) {
                System.out.println("No room change requests found.");
                return;
            }

            System.out.println("Room Change Requests:");
            for (int i = 0; i < requests.size(); i++) {
                System.out.println((i + 1) + ". " + requests.get(i));
            }

            System.out.print("Enter the number of the request to approve/disapprove: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice < 1 || choice > requests.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            String[] requestDetails = requests.get(choice - 1).split(",");
            if (requestDetails.length < 2) {
                System.out.println("Invalid request format.");
                return;
            }

            String residentId = requestDetails[0];
            String roomChangeDescription = requestDetails[1];
            String[] roomTypes = roomChangeDescription.split(" to ");
            if (roomTypes.length < 2) {
                System.out.println("Invalid room change description format.");
                return;
            }

            String currentRoomType = roomTypes[0].trim();
            String newRoomType = roomTypes[1].trim();

            System.out.println("1. Approve");
            System.out.println("2. Disapprove");
            System.out.print("Enter your choice: ");
            int decision = sc.nextInt();
            sc.nextLine(); // Consume newline

            String status;
            if (decision == 1) {
                status = "Approved";
                updateResidentRoomType(residentId, newRoomType);
            } else if (decision == 2) {
                status = "Disapproved";
            } else {
                System.out.println("Invalid choice.");
                return;
            }

            requests.set(choice - 1, requests.get(choice - 1) + "," + status);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Change_Room.txt"))) {
                for (String request : requests) {
                    writer.write(request);
                    writer.newLine();
                }
            }

            System.out.println("Request has been " + status.toLowerCase() + ".");
        } catch (IOException e) {
            System.err.println("Error processing room change requests: " + e.getMessage());
        }
    }

    private void updateResidentRoomType(String residentId, String newRoomType) {
        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userDetails = line.split(",");
                    if (userDetails[0].equals(residentId)) {
                        userDetails[5] = newRoomType;
                        line = String.join(",", userDetails);
                    }
                    lines.add(line);
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resident_Info.txt"))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error updating resident room type: " + e.getMessage());
        }
    }

    public void makePaymentForResident() {
        if (!isLoggedIn) { // Check if the user is logged in
            System.out.println("You need to log in first.");
            return;
        }

        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            if (lines.isEmpty()) {
                System.out.println("No residents found.");
                return;
            }

            System.out.println("Residents:");
            for (int i = 0; i < lines.size(); i++) {
                String[] details = lines.get(i).split(",");
                System.out.println((i + 1) + ". " + details[1] + " - " + details[3] + " (Balance: " + details[8] + ")");
            }

            System.out.print("Enter the number of the resident to pay: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            if (choice < 1 || choice > lines.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            String[] selectedResidentDetails = lines.get(choice - 1).split(",");
            System.out.println("Current Balance: " + selectedResidentDetails[8]);
            System.out.print("Enter Payment Amount to Pay: ");
            double paymentAmount = Double.parseDouble(sc.nextLine().trim());
            double currentBalance = Double.parseDouble(selectedResidentDetails[8].substring(2)); // Remove "RM" prefix
            double newBalance = currentBalance - paymentAmount;
            selectedResidentDetails[8] = "RM" + newBalance;
            lines.set(choice - 1, String.join(",", selectedResidentDetails));

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resident_Info.txt"))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Payment has been processed successfully!");

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error processing payment: " + e.getMessage());
        }
    }

    public void generateReceipt() {
        if (!isLoggedIn) { // Check if the user is logged in
            System.out.println("You need to log in first.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Resident_Info.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            if (lines.isEmpty()) {
                System.out.println("No residents found.");
                return;
            }

            while (true) {
                System.out.println("Residents:");
                for (int i = 0; i < lines.size(); i++) {
                    String[] details = lines.get(i).split(",");
                    System.out.println((i + 1) + ". " + details[1] + " - " + details[3] + " (Balance: " + details[8] + ")");
                }

                System.out.print("Enter the number of the resident to generate receipt: ");
                int choice = Integer.parseInt(sc.nextLine().trim());

                if (choice < 1 || choice > lines.size()) {
                    System.out.println("Invalid choice.");
                    return;
                }

                String[] selectedResidentDetails = lines.get(choice - 1).split(",");
                System.out.println("Generating receipt for " + selectedResidentDetails[1] + " - " + selectedResidentDetails[3] + " (Balance: " + selectedResidentDetails[8] + ")");
                System.out.println("----------------------------------------");
                System.out.println("Name: " + selectedResidentDetails[3]);
                System.out.println("Contact number: " + selectedResidentDetails[4]);
                System.out.println("Email: " + selectedResidentDetails[5]);
                System.out.println("Room type: " + selectedResidentDetails[6]);
                System.out.println("Room number: " + selectedResidentDetails[7]);
                System.out.println("Receipt generate datetime: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                System.out.println("Receipt has been generated successfully!");

                System.out.print("Would you like to (1) generate another receipt or (2) go back to the staff page? ");
                int nextAction = Integer.parseInt(sc.nextLine().trim());

                if (nextAction == 2) {
                    break;
                } else if (nextAction != 1) {
                    System.out.println("Invalid choice. Returning to staff page.");
                    break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error generating receipt: " + e.getMessage());
        }
    }
}