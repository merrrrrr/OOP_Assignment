import java.io.*;
import java.util.Scanner;

public class Resident extends User{
    private String gender;
    private String roomNumber;

    private int overdueAmount;

    public Resident() {
    }

    public Resident(String id, String username, String password, String name, String gender, String roomNumber, String contactNumber, String email, int overdueAmount) {
        super(id, username, password, name, contactNumber, email);
        this.gender = gender;
        this.roomNumber = roomNumber;
        this.overdueAmount = overdueAmount;
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

    public int getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(int overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    @Override
    public String toString() {
        return "Resident{id = " + getId()
                + ", username = " + getUsername()
                + ", password = " + getPassword()
                + ", name = " + getName()
                + ", gender = " + gender
                + ", roomNumber = " + roomNumber
                + ", contactNumber = " + getContactNumber()
                + ", email = " + getEmail()
                + ", overdueAmount = " + overdueAmount + "}";
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

        BufferedWriter residentRegistrationWriter = new BufferedWriter(new FileWriter("Resident_Registration.txt", true));
        String register = (username + "," + password + "," + name + "," + gender + "," + roomNumber + "," + contactNumber + "," + email);
        System.out.println("Your registration request has been sent to manager for approval.");
        residentRegistrationWriter.write(register);
        residentRegistrationWriter.newLine();
        residentRegistrationWriter.close();
    }

    public boolean login() throws IOException {
        BufferedReader residentInfo = new BufferedReader(new FileReader("Resident_Info.txt"));

        System.out.print("Username: ");
        String userEnteredUsername = sc.next();
        sc.nextLine(); // Consume newline
        System.out.print("Password: ");
        String userEnteredPassword = sc.next();
        sc.nextLine(); // Consume newline

        String lines;
        boolean login = false;

        while (((lines = residentInfo.readLine()) != null)) {
            String[] line = lines.split(",");
            String id = line[0];
            String username = line[1];
            String password = line[2];
            String name = line[3];
            String gender = line[4];
            String roomNumber = line[5];
            String contactNumber = line[6];
            String email = line[7];
            int overdueAmount = Integer.parseInt(line[8]);

            if (username.equals(userEnteredUsername) && password.equals(userEnteredPassword)) {
                System.out.println("Welcome! " + name);
                return true;
            }
        }

        residentInfo.close();
        return false;
    }
}
