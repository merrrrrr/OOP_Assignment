public class Resident extends User {
    private String roomType;
    private String gender;
    private String roomNo;
    private String payableAmount;

    public Resident() {
    }

    public Resident(String id, String username, String password, String name, String contactNumber, String email, String gender, String roomNo, String roomType, String payableAmount) {
        super(id, username, password, name, contactNumber, email);
        this.gender = gender;
        this.roomType = roomType;
        this.roomNo = roomNo;
        this.payableAmount = payableAmount;
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

    public String getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(String payableAmount) {
        this.payableAmount = payableAmount;
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
                ", payableAmount=" + payableAmount + "}";
    }
}