import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class User {
    private String id;
    private String username;
    private String password;
    private String name;
    private String contactNumber;
    private String email;

    public User() {
    }

    public User(String id, String username, String password, String name, String contactNumber, String email) {
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

    Scanner sc = new Scanner(System.in);

    public boolean register(int userType, String registerInfo) throws IOException {
        String filename = getRegisterFilename(userType);
        BufferedReader userRegistration = new BufferedReader(new FileReader(filename));
        BufferedWriter userRegistrationWriter = new BufferedWriter(new FileWriter(filename, true));

        if (userRegistration.read() != -1) {
            userRegistrationWriter.newLine();
        }

        userRegistrationWriter.write(registerInfo);

        userRegistrationWriter.close();
        userRegistration.close();

        return true;
    }

    public String login(int userType, String usernameInput, String passwordInput) throws IOException {
        String filename = getInfoFilename(userType);
        BufferedReader userInfo = new BufferedReader(new FileReader(filename));

        String lines;

        while (((lines = userInfo.readLine()) != null)) {
            String[] line = lines.split(",");
            String username = line[1];
            String password = line[2];

            if (username.equals(usernameInput) && password.equals(passwordInput)) {
                return lines;
            }

        }

        userInfo.close();
        return null;
    }

    public String getInfoFilename(int userType) {
        String filename = "";

        if (userType == 1) {
            filename = "Manager_Info.txt";
        } else if (userType == 2) {
            filename = "Staff_Info.txt";
        } else if (userType == 3) {
            filename = "Resident_Info.txt";
        }

        return filename;
    }

    public String getRegisterFilename(int userType) {
        String filename = "";

        if (userType == 1) {
            filename = "Manager_Registration.txt";
        } else if (userType == 2) {
            filename = "Staff_Registration.txt";
        } else if (userType == 3) {
            filename = "Resident_Registration.txt";
        }

        return filename;
    }

    public boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return name.matches("[a-zA-Z ]+");
    }

    public boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c) && c != ',') {
                hasSpecialChar = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar;
    }

    public boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return email != null && p.matcher(email).matches();
    }

    public boolean validateContactNumber(String contactNumber) {
        if (contactNumber.length() >= 9 && contactNumber.length() <= 11) {
            for (char c : contactNumber.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public boolean isUsernameUnique(String username) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Manager_Info.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[1];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();

        br = new BufferedReader(new FileReader("Staff_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[1];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();

        br = new BufferedReader(new FileReader("Resident_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[1];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();

        br = new BufferedReader(new FileReader("Manager_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[0];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Staff_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[0];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingUsername = parts[0];
                if (existingUsername.equals(username)) {
                    br.close();
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isEmailUnique(String email) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Manager_Info.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[5];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();

        br = new BufferedReader(new FileReader("Staff_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[5];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[5];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Manager_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[4];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Staff_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[4];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingEmail = parts[4];
                if (existingEmail.equals(email)) {
                    br.close();
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isContactNumberUnique(String contactNumber) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Manager_Info.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[4];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }
        br.close();

        br = new BufferedReader(new FileReader("Staff_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[4];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Info.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[4];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Manager_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[3];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Staff_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[3];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        br = new BufferedReader(new FileReader("Resident_Registration.txt"));
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String existingContactNumber = parts[3];
                if (existingContactNumber.equals(contactNumber)) {
                    br.close();
                    return false;
                }
            }
        }

        return true;
    }

}
