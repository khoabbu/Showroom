
package showroom.model;

public class Customers {
    
    private int customerId;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;

    // Constructor mặc định
    public Customers() {
    }

    // Constructor có tham số
    public Customers(int customerId, String fullName, String address, String phoneNumber, String email) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // --- Getters and Setters cho tất cả các thuộc tính ---
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        // Hiển thị tên trong ComboBox cho tiện (nếu cần dùng sau này)
        return this.fullName; 
    }
}