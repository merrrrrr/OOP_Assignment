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
        System.out.print("Username: ");
        String username = sc.nextLine();
        String lines;

//        BufferedReader managerInfo = new BufferedReader(new FileReader("Manager_Info.txt"));
//        while(((lines = managerInfo.readLine()) != null)) {
//            String[] line = lines.split(",");
//            if (line[0].equals(username)) {
//                System.out.println("This username has been used. Please enter other username.");
//                break;
//            }
//
//        }

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

    public boolean login() throws IOException {
        BufferedReader managerInfo = new BufferedReader(new FileReader("Manager_Info.txt"));

        System.out.print("Username: ");
        String userEnteredUsername = sc.next();
        System.out.print("Password: ");
        String userEnteredPassword = sc.next();

        String lines;
        boolean login = false;

        while (((lines = managerInfo.readLine()) != null)) {
            String[] line = lines.split(",");
            String id = line[0];
            String username = line[1];
            String password = line[2];
            String name = line[3];
            String contactNumber = line[4];
            String email = line[5];

            if (username.equals(userEnteredUsername) && password.equals(userEnteredPassword)) {
                System.out.println("Welcome! " + name);
                return true;
            }
        }

        managerInfo.close();
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

    public void searchUser() throws IOException {
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

        switch (userType) {
            case 1:
                switch (searchType) {
                    case 1:
                        System.out.println("Search staff by any: ");
                        String searchAny = sc.next();
                        BufferedReader managerInfo = new BufferedReader(new FileReader("Manager_Info.txt"));
                        String lines;
                        int result = 0;
                        boolean userFound = false;

                        while ((lines = managerInfo.readLine()) != null) {
                            if (lines.contains(searchAny)) {
                                String[] line = lines.split(",");
                                String id = line[0];
                                String username = line[1];
                                String password = line[2];
                                String name = line[3];
                                String contactNumber = line[4];
                                String email = line[5];

                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                userFound = true;
                                break;

                            } else {
                                System.out.println("Username not found.");
                            }

                        }

                    case 2:
                        System.out.print("Search Manager by ID: ");
                        String searchID = sc.next();

                        managerInfo = new BufferedReader(new FileReader("Manager_Info.txt"));
                        result = 0;
                        userFound = false;

                        while ((lines = managerInfo.readLine()) != null) {
                            String[] line = lines.split(",");
                            String id = line[0];
                            String username = line[1];
                            String password = line[2];
                            String name = line[3];
                            String contactNumber = line[4];
                            String email = line[5];

                            if (id.contains(searchID)) {
                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                userFound = true;
                                break;
                            } else {
                                System.out.println("ID not found.");
                            }
                        }

                        System.out.println(result + " result(s) found.");

                        managerInfo.close();
                        break;

                    case 3:
                        System.out.print("Search Manager by Username: ");
                        String searchUsername = sc.next();

                        managerInfo = new BufferedReader(new FileReader("Manager_Info.txt"));
                        result = 0;
                        userFound = false;

                        while ((lines = managerInfo.readLine()) != null) {
                            String[] line = lines.split(",");
                            String id = line[0];
                            String username = line[1];
                            String password = line[2];
                            String name = line[3];
                            String contactNumber = line[4];
                            String email = line[5];

                            if (username.contains(searchUsername)) {
                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                userFound = true;
                                break;
                            } else {
                                System.out.println("Username not found.");
                            }
                        }

                        System.out.println(result + " result(s) found.");
                        managerInfo.close();

                        break;

                    case 4:
                        System.out.println("Search Manager by Name: ");
                        String searchName = sc.next();
                        managerInfo = new BufferedReader(new FileReader("Manager_Info.txt"));
                        result = 0;
                        userFound = false;

                        while ((lines = managerInfo.readLine()) != null) {
                            String[] line = lines.split(",");
                            String id = line[0];
                            String username = line[1];
                            String password = line[2];
                            String name = line[3];
                            String contactNumber = line[4];
                            String email = line[5];

                            if (username.contains(searchName)) {
                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                userFound = true;
                                break;
                            } else {
                                System.out.println("Username not found.");
                            }
                        }

                        System.out.println(result + " result(s) found.");
                        managerInfo.close();
                        break;

                    default:
                        System.out.println("Wrong input. Please try again.");
                        break;
                }
                break;

            case 2:
                switch (searchType) {
                    case 1:
                        System.out.println("Search staff by any: ");
                        String searchAny = sc.next();
                        BufferedReader staffInfo = new BufferedReader(new FileReader("Staff_Info.txt"));
                        String lines;
                        int result = 0;
                        boolean userFound = false;

                        while ((lines = staffInfo.readLine()) != null) {
                            if (lines.contains(searchAny)) {
                                String[] line = lines.split(",");
                                String id = line[0];
                                String username = line[1];
                                String password = line[2];
                                String name = line[3];
                                String contactNumber = line[4];
                                String email = line[5];

                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                userFound = true;
                                break;

                            } else {
                                System.out.println("Username not found.");
                            }

                        }

                    case 2:
                        System.out.print("Search Staff by ID: ");
                        String searchID = sc.next();

                        staffInfo = new BufferedReader(new FileReader("Staff_Info.txt"));
                        result = 0;
                        userFound = false;

                        while ((lines = staffInfo.readLine()) != null) {
                            String[] line = lines.split(",");
                            String id = line[0];
                            String username = line[1];
                            String password = line[2];
                            String name = line[3];
                            String contactNumber = line[4];
                            String email = line[5];

                            if (id.contains(searchID)) {
                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                userFound = true;
                                break;
                            } else {
                                System.out.println("ID not found.");
                            }
                        }

                        System.out.println(result + " result(s) found.");

                        staffInfo.close();
                        break;

                    case 3:
                        System.out.print("Search Staff by Username: ");
                        String searchUsername = sc.next();

                        staffInfo = new BufferedReader(new FileReader("Staff_Info.txt"));
                        result = 0;
                        userFound = false;

                        while ((lines = staffInfo.readLine()) != null) {
                            String[] line = lines.split(",");
                            String id = line[0];
                            String username = line[1];
                            String password = line[2];
                            String name = line[3];
                            String contactNumber = line[4];
                            String email = line[5];

                            if (username.contains(searchUsername)) {
                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                userFound = true;
                                break;
                            } else {
                                System.out.println("Username not found.");
                            }
                        }

                        System.out.println(result + " result(s) found.");
                        staffInfo.close();

                        break;

                    case 4:
                        System.out.println("Search Staff by Name: ");
                        String searchName = sc.next();
                        staffInfo = new BufferedReader(new FileReader("Staff_Info.txt"));
                        result = 0;
                        userFound = false;

                        while ((lines = staffInfo.readLine()) != null) {
                            String[] line = lines.split(",");
                            String id = line[0];
                            String username = line[1];
                            String password = line[2];
                            String name = line[3];
                            String contactNumber = line[4];
                            String email = line[5];

                            if (username.contains(searchName)) {
                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                userFound = true;
                                break;
                            } else {
                                System.out.println("Username not found.");
                            }
                        }

                        System.out.println(result + " result(s) found.");
                        staffInfo.close();
                        break;
                    default:
                        System.out.println("Wrong input. Please try again.");
                        break;
                }
                break;
            case 3:
                switch (searchType) {
                    case 1:
                        System.out.println("Search resident by any: ");
                        String searchAny = sc.next();
                        BufferedReader residentInfo = new BufferedReader(new FileReader("Resident_Info.txt"));
                        String lines;
                        int result = 0;
                        boolean userFound = false;

                        while ((lines = residentInfo.readLine()) != null) {
                            if (lines.contains(searchAny)) {
                                String[] line = lines.split(",");
                                String id = line[0];
                                String username = line[1];
                                String password = line[2];
                                String name = line[3];
                                String gender = line[4];
                                String roomNumber = line[5];
                                String contactNumber = line[6];
                                String email = line[7];
                                String overdueAmount = line[8];

                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Gender: " + gender);
                                System.out.println("Room Number: " + roomNumber);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                System.out.println("Overdue Amount: " + overdueAmount);
                                userFound = true;
                                break;

                            } else {
                                System.out.println("Username not found.");
                            }

                        }

                    case 2:
                        System.out.print("Search Resident by ID: ");
                        String searchID = sc.next();

                        residentInfo = new BufferedReader(new FileReader("Resident_Info.txt"));
                        result = 0;
                        userFound = false;

                        while ((lines = residentInfo.readLine()) != null) {
                            String[] line = lines.split(",");
                            String id = line[0];
                            String username = line[1];
                            String password = line[2];
                            String name = line[3];
                            String gender = line[4];
                            String roomNumber = line[5];
                            String contactNumber = line[6];
                            String email = line[7];
                            String overdueAmount = line[8];

                            if (id.contains(searchID)) {
                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Gender: " + gender);
                                System.out.println("Room Number: " + roomNumber);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                System.out.println("Overdue Amount: " + overdueAmount);
                                userFound = true;
                                break;
                            } else {
                                System.out.println("ID not found.");
                            }
                        }

                        System.out.println(result + " result(s) found.");

                        residentInfo.close();
                        break;

                    case 3:
                        System.out.print("Search Resident by Username: ");
                        String searchUsername = sc.next();

                        residentInfo = new BufferedReader(new FileReader("Resident_Info.txt"));
                        result = 0;
                        userFound = false;

                        while ((lines = residentInfo.readLine()) != null) {
                            String[] line = lines.split(",");
                            String id = line[0];
                            String username = line[1];
                            String password = line[2];
                            String name = line[3];
                            String gender = line[4];
                            String roomNumber = line[5];
                            String contactNumber = line[6];
                            String email = line[7];
                            String overdueAmount = line[8];

                            if (id.contains(searchID)) {
                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Gender: " + gender);
                                System.out.println("Room Number: " + roomNumber);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                System.out.println("Overdue Amount: " + overdueAmount);
                                userFound = true;
                                break;

                            } else {
                                System.out.println("Username not found.");
                            }
                        }

                        System.out.println(result + " result(s) found.");
                        residentInfo.close();

                        break;

                    case 4:
                        System.out.println("Search Resident by Name: ");
                        String searchName = sc.next();
                        residentInfo = new BufferedReader(new FileReader("Resident_Info.txt"));
                        result = 0;
                        userFound = false;

                        while ((lines = residentInfo.readLine()) != null) {
                            String[] line = lines.split(",");
                            String id = line[0];
                            String username = line[1];
                            String password = line[2];
                            String name = line[3];
                            String gender = line[4];
                            String roomNumber = line[5];
                            String contactNumber = line[6];
                            String email = line[7];
                            String overdueAmount = line[8];

                            if (id.contains(searchID)) {
                                System.out.println("Result" + ++result);
                                System.out.println("ID: " + id);
                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("Name: " + name);
                                System.out.println("Gender: " + gender);
                                System.out.println("Room Number: " + roomNumber);
                                System.out.println("Contact Number: " + contactNumber);
                                System.out.println("Email: " + email);
                                System.out.println("Overdue Amount: " + overdueAmount);
                                userFound = true;
                                break;

                            } else {
                                System.out.println("Username not found.");
                            }
                        }

                        System.out.println(result + " result(s) found.");
                        residentInfo.close();
                        break;

                    default:
                        System.out.println("Wrong input. Please try again.");
                        break;
                }
                break;

            default:
                System.out.println("Wrong input. Please try again.");
                break;
        }
    }

    public void updateUser() {
        System.out.println("1. Update Manager");
        System.out.println("2. Update Staff");
        System.out.println("3. Update Resident");
        System.out.println("Please enter the user type you want to update: ");
        int userType = sc.nextInt();



    }

    public void deleteUser() {

    }

    public void updateRate() {

    }


}