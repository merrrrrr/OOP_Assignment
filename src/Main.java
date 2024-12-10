import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        system: while (true) {
            Scanner sc = new Scanner(System.in);
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
                        loginLoop:
                        while (true) {
                            if (userType == 1) {
                                Manager manager = new Manager();
                                System.out.println("Manager Login");
                                boolean login = manager.login();
                                if (login == true) {
                                    break mainPage;
                                } else {
                                    System.out.println("Invalid Username or Password");
                                    continue;
                                }

                            } else if (userType == 2) {
                                Staff staff = new Staff();
                                System.out.println("Staff Login");
                                boolean login = staff.login();
                                if (login == true) {
                                    break mainPage;
                                } else {
                                    System.out.println("Invalid Username or Password");
                                    continue;
                                }

                            } else if (userType == 3) {
                                Resident resident = new Resident();
                                System.out.println("Resident Login");
                                boolean login = resident.login();
                                if (login == true) {
                                    break mainPage;
                                } else {
                                    System.out.println("Invalid Username or Password");
                                    continue;
                                }
                            } else if (userType == 4) {
                                break;
                            } else {
                                System.out.println("Wrong Input. Please try again.");
                            }
                        }

                        break;

                    case 2:
                        userType = getUserType();
                        if (userType == 1) {
                            Manager m = new Manager();
                            m.register();
                            break;
                        } else if (userType == 2) {
                            Staff s = new Staff();
                            s.register();
                            break;
                        } else if (userType == 3) {
                            Resident r = new Resident();
                            r.register();
                            break;
                        } else if (userType == 4) {
                            break;
                        } else {
                            System.out.println("Wrong Input. Please try again.");
                            break;
                        }

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
                    Manager manager = new Manager();

                    System.out.println("Manager Page");
                    System.out.println("1. Approve User Registration");
                    System.out.println("2. Search User Account");
                    System.out.println("3. Update User Account");
                    System.out.println("4. Delete User Account");
                    System.out.println("5. Update Rental Rate");
                    System.out.println("6. Log Out");
                    System.out.print(": ");
                    int managerAction = sc.nextInt();

                    switch (managerAction) {
                        case 1:

                            manager.approveUser();
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
                    System.out.println("2. Make Payment for Resident");
                    System.out.println("3. Generate Receipt");
                    System.out.println("4. Log Out");
                    System.out.println(": ");
                    int staffAction = sc.nextInt();

                    switch (staffAction) {
                        case 1:
                            System.out.println("1. Update individual login account");
                            break;

                        case 2:
                            System.out.println("2. Make Payment for Resident");
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
                    break;
                }

            } else if (userType == 3) {
                residentPage:
                while (true) {
                    System.out.println("Resident Page");
                    System.out.println("1. Update individual login account");
                    System.out.println("2. View Payment records");
                    System.out.println("3. Log Out");
                    System.out.print(": ");
                    int residentAction = sc.nextInt();

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
                    break;
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
