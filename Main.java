import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        system: while (true) {
            byte userType = 0;

            mainPage:
            while (true) {
                System.out.println("Welcome to APU Hostel Management Fees Payment System!");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Please enter your choice: ");
                byte loginRegister = sc.nextByte();

                switch (loginRegister) {
                    case 1:
                        userType = getUserType();
                        if (userType == 1) {
                            System.out.println("Manager Login");
                            break mainPage;
                        } else if (userType == 2) {
                            System.out.println("Staff Login");
                            break mainPage;
                        } else if (userType == 3) {
                            System.out.println("Resident Login");
                            break mainPage;
                        } else {
                            System.out.println("Wrong Input. Please try again.");
                        }
                        break;

                    case 2:
                        userType = getUserType();
                        if (userType == 1) {
                            Manager m = new Manager();
                            m.register();
                            break mainPage;
                        } else if (userType == 2) {
                            Staff s = new Staff();
                            s.register();
                            break mainPage;
                        } else if (userType == 3) {
                            Resident r = new Resident();
                            r.register();
                            break mainPage;
                        } else {
                            System.out.println("Wrong Input. Please try again.");
                        }
                        break;

                    case 3:
                        System.out.println("Thank you for using APU Hostel Management Fees Payment System!");
                        break system;

                    default:
                        System.out.println("Wrong input. Please try again.");
                        break;
                }
            }

            if (userType == 1) {
                managerPage:
                while (true) {
                    System.out.println("Manager Page");
                    System.out.println("1. Approve User Registration");
                    System.out.println("2. Search User Account");
                    System.out.println("3. Update User Account");
                    System.out.println("4. Delete User Account");
                    System.out.println("5. Update Rental Rate");
                    System.out.println("6. Log Out");
                    System.out.print("Please enter your choice: ");
                    int managerAction = sc.nextByte();

                    switch (managerAction) {
                        case 1:
                            System.out.println("1. Approve User Registration");
                            break;

                        case 2:
                            System.out.println("2. Search User Account");
                            break;

                        case 3:
                            System.out.println("3. Update User Account");
                            break;

                        case 4:
                            System.out.println("4. Delete User Account");
                            break;

                        case 5:
                            System.out.println("5. Update Rental Rate");
                            break;

                        case 6:
                            System.out.println("6. Log Out");
                            break managerPage;

                        default:
                            System.out.println("Wrong Input. Please try again.");
                    }
                }
            } else if (userType == 2) {
                staffPage:
                while (true) {
                    System.out.println("Staff Page");
                    System.out.println("1. Update individual login account");
                    System.out.println("2. Make payment for Resident");
                    System.out.println("3. Generate Receipt");
                    System.out.println("4. Log Out");
                    System.out.print("Please enter your choice: ");
                    int staffAction = sc.nextByte();

                    switch (staffAction) {
                        case 1:
                            System.out.println("1. Update individual login account");
                            break;

                        case 2:
                            System.out.println("2. Make payment for Resident");
                            break;

                        case 3:
                            System.out.println("3. Generate Receipt");
                            break;

                        case 4:
                            System.out.println("4. Log Out");
                            break staffPage;

                        default:
                            System.out.println("Wrong Input. Please try again.");
                    }
                }
            } else if (userType == 3) {
                residentPage:
                while (true) {
                    System.out.println("Resident Page");
                    System.out.println("1. Update individual login account");
                    System.out.println("2. View Payment records");
                    System.out.println("3. Log Out");
                    System.out.print("Please enter your choice: ");
                    int residentAction = sc.nextByte();

                    switch (residentAction) {
                        case 1:
                            System.out.println("1. Update individual login account");
                            break;

                        case 2:
                            System.out.println("2. View Payment records");
                            break;

                        case 3:
                            System.out.println("3. Log Out");
                            break residentPage;

                        default:
                            System.out.println("Wrong Input. Please try again.");
                    }
                }
            }
        }
    }

    public static byte getUserType() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Manager");
        System.out.println("2. Staff");
        System.out.println("3. Resident");
        System.out.println("4. Back");
        System.out.print("User Type: ");
        byte userType = sc.nextByte();

        return userType;
    }
}

class Manager {
    private String id;
    private String username;
    private String password;
    private String name;
    private String contactNumber;
    private String email;


    public Manager() {
    }

    public Manager(String id, String username, String password, String name, String contactNumber, String email) {
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

    public String toString() {
        return "Manager{id = " + id + ", username = " + username + ", password = " + password + ", name = " + name + ", contactNumber = " + contactNumber + ", email = " + email + "}";
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

        FileWriter fw = new FileWriter("C:\\Users\\Mervin Ooi\\IdeaProjects\\OOP Assignment\\src\\Resident_Registration.txt", true);

        String register = (username + "," + password + "," + name + "," + contactNumber + "," + email);
        fw.write(register);
    }

    public void login() throws IOException {
        BufferedReader manager_info = new BufferedReader(new FileReader("C:\\Users\\Mervin Ooi\\IdeaProjects\\OOP Assignment\\src\\Manager_Info.txt"));

        System.out.println("Username: ");
        String userEnteredUsername = sc.next();
        System.out.println("Password: ");
        String userEnteredPassword = sc.next();

        String lines;
        boolean login = true;

        while(((lines = manager_info.readLine()) != null)) {
            String[] line = lines.split(",");
            String id = line[0];
            String username = line[1];
            String password = line[2];
            String name = line[3];
            String contactNumber = line[4];
            String email = line[5];

            if (username.equals(userEnteredUsername) && password.equals(userEnteredPassword)) {
                System.out.println("Welcome! " + name);
                Manager m = new Manager();
                m.setId(id);
                m.setUsername(username);
                m.setPassword(password);
                m.setName(contactNumber);
                m.setEmail(email);

            }
        }
    }

    public void approveUser() throws IOException {

        System.out.println("1. Approve Manager");
        System.out.println("2. Approve Staff");
        System.out.println("3. Approve Resident");
        byte approve = sc.nextByte();

        switch (approve) {
            case 1:
                BufferedReader managerRegistration = new BufferedReader(new FileReader("C:\\Users\\Mervin Ooi\\IdeaProjects\\OOP Assignment\\src\\Manager_Registration.txt"));

                String lines;


                while(((lines = managerRegistration.readLine()) != null)) {
                    String[] line = lines.split(",");
                    String username = line[0];
                    String password = line[1];
                    String name = line[2];
                    String contactNumber = line[3];
                    String email = line[4];
                    System.out.println("Username: " + username + "\nPassword: " + password + "\nName: " + name + "\nContact Number: " + contactNumber + "\nEmail: " + email + "\n");
                }

                System.out.println("Enter username: ");

                break;

            case 2:
                BufferedReader staffRegistration = new BufferedReader(new FileReader("C:\\Users\\Mervin Ooi\\IdeaProjects\\OOP Assignment\\src\\Staff_Registration.txt"));
                break;

            case 3:
                BufferedReader residentRegistration = new BufferedReader(new FileReader("C:\\Users\\Mervin Ooi\\IdeaProjects\\OOP Assignment\\src\\Resident_Registration.txt"));
                break;

            default:


        }

        System.out.println("Username: ");

    }

    public void searchUser() {

    }

    public void updateUser() {

    }

    public void deleteUser() {

    }

    public void updateRate() {

    }


}



class Staff {
    private String id;
    private String username;
    private String password;
    private String name;
    private String contactNumber;
    private String email;

    public Staff() {
    }


    public Staff(String id, String username, String password, String name, String contactNumber, String email) {
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

    public String toString() {
        return "Staff{id = " + id + ", username = " + username + ", password = " + password + ", name = " + name + ", contactNumber = " + contactNumber + ", email = " + email + "}";
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

        FileWriter fw = new FileWriter("C:\\Users\\Mervin Ooi\\IdeaProjects\\OOP Assignment\\src\\Resident_Registration.txt", true);
        String register = (username + "," + password  + "," + name  + "," + contactNumber + "," +  email);
        fw.write(register);

        fw.close();
    }
}


class Resident {
    private String id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String roomNumber;
    private String contactNumber;
    private String email;
    private int overdueAmount;

    public Resident() {
    }

    public Resident(String id, String username, String password, String name, String gender, String roomNumber, String contactNumber, String email, int overdueAmount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.roomNumber = roomNumber;
        this.contactNumber = contactNumber;
        this.email = email;
        this.overdueAmount = overdueAmount;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public int getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(int overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public String toString() {
        return "Resident{id = " + id + ", username = " + username + ", password = " + password + ", name = " + name + ", gender = " + gender + ", roomNumber = " + roomNumber + ", contactNumber = " + contactNumber + ", email = " + email + ", overdueAmount = " + overdueAmount + "}";
    }

    Scanner sc = new Scanner(System.in);

    public void register() throws IOException {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Gender: ");
        String gender = sc.nextLine();
        System.out.print("Room Number: ");
        String roomNumber = sc.nextLine();
        System.out.print("Contact Number: ");
        String contactNumber = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        FileWriter fw = new FileWriter("C:\\Users\\Mervin Ooi\\IdeaProjects\\OOP Assignment\\src\\Resident_Registration.txt", true);
        String register = (username + "," + password  + "," + name + "," + gender + "," + roomNumber + "," + contactNumber + "," +  email);
        System.out.println(register);
        fw.write(register);

        fw.close();
    }
}

