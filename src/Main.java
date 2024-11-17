import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 1 for Manager");
        System.out.println("Enter 2 for Staff");
        System.out.println("Enter 3 for Resident");
        System.out.print("User Type: ");
        byte user_type = sc.nextByte();


        if (user_type == 1) {
            Manager m = new Manager();
            m.register();
        } else if (user_type == 2) {
            Staff s = new Staff();
            s.register();
        } else if (user_type == 3) {
            Resident r = new Resident();
            r.register();
        }


    }
}

class Manager {
    private String id;
    private String username;
    private String password;
    private String name;
    private String contactNumber;
    private String gender;


    public Manager() {
    }

    public Manager(String id, String username, String password, String name, String contactNumber, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.contactNumber = contactNumber;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        return "Manager{id = " + id + ", username = " + username + ", password = " + password + ", name = " + name + ", contactNumber = " + contactNumber + ", gender = " + gender + "}";
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


    public void approveManager() {

    }

    public void approveStaff() {

    }


    public void approveResident() {

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
