import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

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

    public String generateId(String infoFilename) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(infoFilename));
        String line;
        int count = 0;

        while ((line = br.readLine()) != null) {
            count++;
        }
        br.close();

        boolean isIDExist = true;
        String generatedID = "";

        while (isIDExist) {
            isIDExist = false;
            String prefix = "";
            switch (infoFilename) {
                case "Manager_Info.txt":
                    prefix = "M";
                    break;
                case "Staff_Info.txt":
                    prefix = "S";
                    break;
                case "Resident_Info.txt":
                    prefix = "R";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid user type");
            }

            generatedID =  String.format("%s%04d", prefix, count + 1);


            br = new BufferedReader(new FileReader(infoFilename));
            String infoLine;
            while ((infoLine = br.readLine()) != null) {
                String[] Info = infoLine.split(",");
                if (infoLine.split(",")[0].equals(generatedID)) {
                    isIDExist = true;
                    count++;
                    break;
                }
            }
            br.close();
        }

        return generatedID;
    }

}

