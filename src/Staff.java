import java.io.*;
import java.util.Scanner;

public class Staff extends User{

    public Staff() {
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
        BufferedReader staffInfo = new BufferedReader(new FileReader("Staff_Info.txt"));

        System.out.print("Username: ");
        String userEnteredUsername = sc.next();
        sc.nextLine(); // Consume newline
        System.out.print("Password: ");
        String userEnteredPassword = sc.next();
        sc.nextLine(); // Consume newline

        String lines;
        boolean login = false;

        while (((lines = staffInfo.readLine()) != null)) {
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

        staffInfo.close();
        return false;
    }
}
