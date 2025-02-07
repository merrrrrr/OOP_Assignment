import java.io.*;
import java.util.*;

public class Resident extends User {
    private String roomType;
    private String gender;
    private String roomNo;
    private String username;
    private String overdueAmount;
    private String createdDate;
    private boolean isLoggedIn = false;
    private Scanner sc = new Scanner(System.in);

    public Resident() {

    }

    public Resident(String id, String username, String password, String name, String contactNumber, String email, String gender, String roomNo, String roomType, String overdueAmount, String createdDate) {
        super(id, username, password, name, contactNumber, email);
        this.gender = gender;
        this.roomType = roomType;
        this.roomNo = roomNo;
        this.overdueAmount = overdueAmount;
        this.createdDate = createdDate;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(String overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;

    }

    @Override
    public String toString() {
        return "Resident{id=" + getId() +
                ", username=" + getUsername() +
                ", password=" + getPassword() +
                ", name=" + getName() +
                ", gender=" + gender +
                ", roomNo=" + roomNo +
                ", contactNumber=" + getContactNumber() +
                ", email=" + getEmail() +
                ", overdueAmount=" + overdueAmount + "}";
    }
}