import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends User{

    public Manager() {
    }

    public Manager(String id, String username, String password, String name, String contactNumber, String email) {
        super(id, username, password, name, contactNumber, email);
    }

    @Override
    public String toString() {
        return "Manager{id = " + getId()
                + ", username = " + getUsername()
                + ", password = " + getPassword()
                + ", name = " + getName()
                + ", contactNumber = " + getContactNumber()
                + ", email = " + getEmail() + "}";
    }

    Scanner sc = new Scanner(System.in);

    public void register() throws IOException {
        String lines;
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

        BufferedReader managerRegistration = new BufferedReader(new FileReader("Manager_Registration.txt"));
        BufferedWriter managerRegistrationWriter = new BufferedWriter(new FileWriter("Manager_Registration.txt", true));

        if (managerRegistration.read() != -1) {
            managerRegistrationWriter.newLine();
        }

        String register = (username + "," + password + "," + name + "," + contactNumber + "," + email);
        managerRegistrationWriter.write(register);
        System.out.println("Your registration request has been sent to manager for approval.");

        managerRegistrationWriter.close();

//        managerInfo.close();
    }

    public boolean login(String usernameInput, String passwordInput) throws IOException {
        String filename = getInfoFilename(1);
        BufferedReader userInfo = new BufferedReader(new FileReader(filename));

        String lines;

        while (((lines = userInfo.readLine()) != null)) {
            String[] line = lines.split(",");
            String id = line[0];
            String username = line[1];
            String password = line[2];
            String name = line[3];
            String contactNumber = line[4];
            String email = line[5];

            if (username.equals(usernameInput) && password.equals(passwordInput)) {
                return true;
            }
        }

        userInfo.close();
        return false;
    }

    public void approveUser() throws IOException {

        String lines;
        String id = "";
        ArrayList<String> registerList = new ArrayList<>();

        System.out.println("1. Approve Manager");
        System.out.println("2. Approve Staff");
        System.out.println("3. Approve Resident");
        System.out.print(": ");
        byte approve = sc.nextByte();
        sc.nextLine();

        switch (approve) {
            case 1:
                // Reading from Manager_Registration.txt
                BufferedReader managerRegistration = new BufferedReader(new FileReader("Manager_Registration.txt"));
                while ((lines = managerRegistration.readLine()) != null) {
                    registerList.add(lines); // Store all lines in memory
                }
                managerRegistration.close();

                // Display the list of users for approval
                for (String line : registerList) {
                    String[] details = line.split(",");
                    System.out.println("Username: " + details[0] +
                            "\nPassword: " + details[1] +
                            "\nName: " + details[2] +
                            "\nContact Number: " + details[3] +
                            "\nEmail: " + details[4] + "\n");
                }

                System.out.println("Enter username to approve: ");
                String approveUser = sc.nextLine();

                BufferedWriter managerInfoWriter = new BufferedWriter(new FileWriter("Manager_Info.txt", true));
                BufferedWriter managerRegistrationWriter = new BufferedWriter(new FileWriter("Manager_Registration.txt"));

                boolean userFound = false;
                managerRegistration = new BufferedReader(new FileReader("Manager_Registration.txt"));
                for (String line : registerList) {
                    String[] details = line.split(",");
                    String username = details[0];

                    if (username.equals(approveUser)) {
                        userFound = true;

                        // Assign ID based on Manager_Info.txt line count
                        BufferedReader managerInfo = new BufferedReader(new FileReader("Manager_Info.txt"));
                        int length = 0;
                        while (managerInfo.readLine() != null) {
                            length++;
                        }
                        managerInfo.close();

                        if (length < 10) {
                            id = "M000" + length;
                        } else if (length < 100) {
                            id = "M00" + length;
                        } else if (length < 1000) {
                            id = "M0" + length;
                        } else if (length < 10000) {
                            id = "M" + length;
                        } else {
                            System.out.println("The maximum number of users has been reached. Please remove inactive users.");
                            break;
                        }

                        // Write approved user to Manager_Info.txt
                        managerInfoWriter.write("\n" + id + "," + line);
                        System.out.println("This user has been approved successfully!");
                    } else {
                        if (managerRegistration.readLine() != null) {
                            // Write unapproved users back to Manager_Registration.txt
                            managerRegistrationWriter.write("\n" + line);
                        } else {
                            managerRegistrationWriter.write(line);
                        }

                    }
                }

                if (!userFound) {
                    System.out.println("Username not found for approval.");
                }

                managerInfoWriter.close();
                managerRegistrationWriter.close();
                break;

            case 2:
                BufferedReader staffRegistration = new BufferedReader(new FileReader("Staff_Registration.txt"));
                while ((lines = staffRegistration.readLine()) != null) {
                    registerList.add(lines); // Store all lines in memory
                }
                staffRegistration.close();

                // Display the list of users for approval
                for (String line : registerList) {
                    String[] details = line.split(",");
                    System.out.println("Username: " + details[0] +
                            "\nPassword: " + details[1] +
                            "\nName: " + details[2] +
                            "\nContact Number: " + details[3] +
                            "\nEmail: " + details[4] + "\n");
                }

                System.out.println("Enter username to approve: ");
                approveUser = sc.nextLine();

                BufferedWriter staffInfoWriter = new BufferedWriter(new FileWriter("Staff_Info.txt", true));
                BufferedWriter staffRegistrationWriter = new BufferedWriter(new FileWriter("Staff_Registration.txt"));

                userFound = false;
                staffRegistration = new BufferedReader(new FileReader("Staff_Registration.txt"));
                for (String line : registerList) {
                    String[] details = line.split(",");
                    String username = details[0];

                    if (username.equals(approveUser)) {
                        userFound = true;

                        // Assign ID based on Manager_Info.txt line count
                        BufferedReader staffInfo = new BufferedReader(new FileReader("Staff_Info.txt"));
                        int length = 0;
                        while (staffInfo.readLine() != null) {
                            length++;
                        }
                        staffInfo.close();

                        if (length < 10) {
                            id = "S000" + length;
                        } else if (length < 100) {
                            id = "S00" + length;
                        } else if (length < 1000) {
                            id = "S0" + length;
                        } else if (length < 10000) {
                            id = "S" + length;
                        } else {
                            System.out.println("The maximum number of users has been reached. Please remove inactive users.");
                            break;
                        }

                        // Write approved user to Staff_Info.txt
                        staffInfoWriter.write("\n" + id + "," + line);
                        System.out.println("This user has been approved successfully!");
                    } else {
                        if (staffRegistration.readLine() != null) {
                            // Write unapproved users back to Staff_Registration.txt
                            staffRegistrationWriter.write("\n" + line);
                        } else {
                            staffRegistrationWriter.write(line);
                        }

                    }
                }

                if (!userFound) {
                    System.out.println("Username not found for approval.");
                }

                staffInfoWriter.close();
                staffRegistrationWriter.close();
                break;

            case 3:
                BufferedReader residentRegistration = new BufferedReader(new FileReader("Resident_Registration.txt"));
                while ((lines = residentRegistration.readLine()) != null) {
                    registerList.add(lines); // Store all lines in memory
                }
                residentRegistration.close();

                // Display the list of users for approval
                for (String line : registerList) {
                    String[] details = line.split(",");
                    System.out.println("Username: " + details[0] +
                            "\nPassword: " + details[1] +
                            "\nName: " + details[2] +
                            "\nGender: " + details[3] +
                            "\nRoom_No: " + details[4] +
                            "\nContact Number: " + details[5] +
                            "\nEmail: " + details[6] + "\n");
                }

                System.out.println("Enter username to approve: ");
                approveUser = sc.nextLine();

                BufferedWriter residentInfoWriter = new BufferedWriter(new FileWriter("Resident_Info.txt", true));
                BufferedWriter residentRegistrationWriter = new BufferedWriter(new FileWriter("Resident_Registration.txt"));

                userFound = false;
                residentRegistration = new BufferedReader(new FileReader("Resident_Registration.txt"));
                for (String line : registerList) {
                    String[] details = line.split(",");
                    String username = details[0];

                    if (username.equals(approveUser)) {
                        userFound = true;

                        // Assign ID based on Manager_Info.txt line count
                        BufferedReader residentInfo = new BufferedReader(new FileReader("Resident_Info.txt"));
                        int length = 0;
                        while (residentInfo.readLine() != null) {
                            length++;
                        }
                        residentInfo.close();

                        if (length < 10) {
                            id = "R000" + length;
                        } else if (length < 100) {
                            id = "R00" + length;
                        } else if (length < 1000) {
                            id = "R0" + length;
                        } else if (length < 10000) {
                            id = "R" + length;
                        } else {
                            System.out.println("The maximum number of users has been reached. Please remove inactive users.");
                            break;
                        }

                        // Write approved user to Resident_Info.txt
                        residentInfoWriter.write("\n" + id + "," + line);
                        System.out.println("This user has been approved successfully!");
                    } else {
                        if (residentRegistration.readLine() != null) {
                            // Write unapproved users back to Resident_Registration.txt
                            residentRegistrationWriter.write("\n" + line);
                        } else {
                            residentRegistrationWriter.write(line);
                        }

                    }
                }

                if (!userFound) {
                    System.out.println("Username not found for approval.");
                }

                residentInfoWriter.close();
                residentRegistrationWriter.close();
                break;

            default:


        }

    }

    public void searchEditDelete() throws IOException {
        System.out.println("1. Search Manager");
        System.out.println("2. Search Staff");
        System.out.println("3. Search Resident");
        System.out.println("Please enter the user type you want to search: ");
        int userType = sc.nextInt();

        System.out.println("1. Search by Any");
        System.out.println("2. Search by ID");
        System.out.println("3. Search by Username");
        System.out.println("4. Search by Name");
        System.out.println("Please enter the search type: ");
        int searchType = sc.nextInt();

        String fileName = "";
        switch (userType) {
            case 1:
                fileName = "Manager_Info.txt";
                break;
            case 2:
                fileName = "Staff_Info.txt";
                break;
            case 3:
                fileName = "Resident_Info.txt";
                break;
            default:
                System.out.println("Wrong input. Please try again.");
                return;
        }

        System.out.print("Enter search value: ");
        String searchValue = sc.next();

        boolean exist = searchUser(fileName, searchType, searchValue);

        if (exist) {
            System.out.println("Do you want to edit the user? (Y/N)");
            String edit = sc.next();

            if (edit.equalsIgnoreCase("Y")) {
                updateUser(fileName);

            } else if (edit.equalsIgnoreCase("N")) {
                System.out.println("Do you want to delete the user? (Y/N)");
                String delete = sc.next();

                if (delete.equalsIgnoreCase("Y")) {
                    deleteUser(fileName);

                } else if (delete.equalsIgnoreCase("N")) {
                    System.out.println("Back to main menu.");

                } else {
                    System.out.println("Invalid input.");
                }

            } else {
                System.out.println("Invalid input.");
            }
        }


    }

    private boolean searchUser(String fileName, int searchType, String searchValue) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        int result = 0;
        boolean userFound = false;

        while ((line = br.readLine()) != null) {
            String[] details = line.split(",");
            String id = details[0];
            String username = details[1];
            String name = details[3];

            boolean match = false;
            switch (searchType) {
                case 1:
                    match = line.contains(searchValue);
                    break;
                case 2:
                    match = id.contains(searchValue);
                    break;
                case 3:
                    match = username.contains(searchValue);
                    break;
                case 4:
                    match = name.contains(searchValue);
                    break;
                default:
                    System.out.println("Wrong input. Please try again.");
                    break;
            }

            if (match) {
                if (fileName.equals("Resident_Info.txt")) {
                    System.out.println("Result " + ++result);
                    System.out.println("ID: " + id);
                    System.out.println("Username: " + username);
                    System.out.println("Name: " + name);
                    System.out.println("Gender: " + details[4]);
                    System.out.println("Room Number: " + details[5]);
                    System.out.println("Contact Number: " + details[6]);
                    System.out.println("Email: " + details[7]);
                    System.out.println("Overdue Amount: " + details[8]);

                } else {
                    System.out.println("Result " + ++result);
                    System.out.println("ID: " + id);
                    System.out.println("Username: " + username);
                    System.out.println("Name: " + name);
                    System.out.println("Contact Number: " + details[4]);
                    System.out.println("Email: " + details[5]);
                }

                userFound = true;
            }
        }

        if (!userFound) {
            System.out.println("No results found.");
        }

        br.close();
        return userFound;
    }


    public void updateUser(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));

        String line;
        System.out.println("1. Edit Username");
        System.out.println("2. Edit Password");
        System.out.println("3. Edit Name");
        System.out.println("4. Edit Contact Number");
        System.out.println("5. Edit Email");

        if (fileName.equals("Resident_Info.txt")) {
            System.out.println("6. Edit Room Type");
            System.out.println("7. Edit Overdue Amount");
        }

        System.out.print("Please enter the update type: ");
        int updateType = sc.nextInt();

        switch (updateType) {
            case 1:
                System.out.print("Enter new username: ");
                String newUsername = sc.next();
                break;
            case 2:
                System.out.print("Enter new password: ");
                String newPassword = sc.next();
                break;
            case 3:
                System.out.print("Enter new name: ");
                String newName = sc.next();
                break;
            case 4:
                System.out.print("Enter new contact number: ");
                String newContactNumber = sc.next();
                break;
            case 5:
                System.out.print("Enter new email: ");
                String newEmail = sc.next();
                break;
            case 6:
                System.out.print("Enter new room type: ");
                String newRoomType = sc.next();
                break;
            case 7:
                System.out.print("Enter new overdue amount: ");
                String newOverdueAmount = sc.next();
                break;
            default:
                System.out.println("Wrong input. Please try again.");
                break;
        }





    }

    public void deleteUser(String fileName) {

    }

    public void updateRate() {

    }


}