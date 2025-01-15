//import java.io.*;
//import java.util.*;
//
//class User {
//    protected String username;
//    protected String password;
//
//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public boolean authenticate(String password) {
//        return this.password.equals(password);
//    }
//}
//
//class Manager extends User {
//    public Manager(String username, String password) {
//        super(username, password);
//    }
//
//    public void registerResident(Resident resident) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("residents.txt", true))) {
//            writer.write(resident.toString() + "\n");
//        }
//    }
//
//    public void setFees(double fees) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("fees.txt"))) {
//            writer.write(String.valueOf(fees));
//        }
//    }
//}
//
//class Resident extends User {
//    private String fullName;
//    private String roomNumber;
//    private List<String> paymentRecords = new ArrayList<>();
//
//    public Resident(String username, String password, String fullName, String roomNumber) {
//        super(username, password);
//        this.fullName = fullName;
//        this.roomNumber = roomNumber;
//    }
//
//    public void makePayment(double amount) throws IOException {
//        String record = "Paid: " + amount + " on " + new Date();
//        paymentRecords.add(record);
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(username + "_payments.txt", true))) {
//            writer.write(record + "\n");
//        }
//    }
//
//    public void viewPayments() throws IOException {
//        File file = new File(username + "_payments.txt");
//        if (file.exists()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//        } else {
//            System.out.println("No payment records found.");
//        }
//    }
//
//    @Override
//    public String toString() {
//        return username + "," + password + "," + fullName + "," + roomNumber;
//    }
//}
//
//public class AHMFPS {
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) throws IOException {
//        System.out.println("Welcome to APU Hostel Management Fees Payment System");
//
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine();
//
//        User user = authenticate(username, password);
//        if (user == null) {
//            System.out.println("Invalid username or password.");
//            return;
//        }
//
//        if (user instanceof Manager) {
//            handleManagerActions((Manager) user);
//        } else if (user instanceof Resident) {
//            handleResidentActions((Resident) user);
//        }
//    }
//
//    private static User authenticate(String username, String password) throws IOException {
//        File file = new File("users.txt");
//        if (!file.exists()) return null;
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts[0].equals(username)) {
//                    if (parts[2].equals("Manager") && new Manager(parts[0], parts[1]).authenticate(password)) {
//                        return new Manager(parts[0], parts[1]);
//                    } else if (parts[2].equals("Resident") && new Resident(parts[0], parts[1], parts[3], parts[4]).authenticate(password)) {
//                        return new Resident(parts[0], parts[1], parts[3], parts[4]);
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private static void handleManagerActions(Manager manager) throws IOException {
//        while (true) {
//            System.out.println("\nManager Actions:");
//            System.out.println("1. Register Resident");
//            System.out.println("2. Set Fees");
//            System.out.println("3. Exit");
//            System.out.print("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter resident username: ");
//                    String username = scanner.nextLine();
//                    System.out.print("Enter password: ");
//                    String password = scanner.nextLine();
//                    System.out.print("Enter full name: ");
//                    String fullName = scanner.nextLine();
//                    System.out.print("Enter room number: ");
//                    String roomNumber = scanner.nextLine();
//                    manager.registerResident(new Resident(username, password, fullName, roomNumber));
//                    System.out.println("Resident registered successfully.");
//                    break;
//                case 2:
//                    System.out.print("Enter fees: ");
//                    double fees = Double.parseDouble(scanner.nextLine());
//                    manager.setFees(fees);
//                    System.out.println("Fees updated successfully.");
//                    break;
//                case 3:
//                    return;
//                default:
//                    System.out.println("Invalid option. Try again.");
//            }
//        }
//    }
//
//    private static void handleResidentActions(Resident resident) throws IOException {
//        while (true) {
//            System.out.println("\nResident Actions:");
//            System.out.println("1. Make Payment");
//            System.out.println("2. View Payments");
//            System.out.println("3. Exit");
//            System.out.print("Choose an option: ");
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter amount: ");
//                    double amount = Double.parseDouble(scanner.nextLine());
//                    resident.makePayment(amount);
//                    System.out.println("Payment made successfully.");
//                    break;
//                case 2:
//                    System.out.println("Payment Records:");
//                    resident.viewPayments();
//                    break;
//                case 3:
//                    return;
//                default:
//                    System.out.println("Invalid option. Try again.");
//            }
//        }
//    }
//}
