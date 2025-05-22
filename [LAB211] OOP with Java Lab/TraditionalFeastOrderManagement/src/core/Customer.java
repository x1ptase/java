package core;

public class Customer {
    private String custCode; // Mã khách hàng (Cxxxx, Gxxxx, Kxxxx)
    private String custName; // Họ tên khách hàng
    private String phone; // Số điện thoại (10 số, mạng hợp lệ)
    private String email; // Địa chỉ email

    public Customer(){ 
    }
    
    // Constructor
    public Customer(String customerID, String name, String phoneNumber, String email) {
        this.custCode = customerID;
        this.custName = name;
        this.phone = phoneNumber;
        this.email = email;
    }

    // Getters
    public String getCustCode() {
        return custCode;
    }

    public String getCustName() {
        return custName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setCustName(String name) {
        this.custName = name;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString() - Hiển thị thông tin khách hàng
    @Override
    public String toString() {
        return String.format("Customer ID: %s | Name: %s | Phone: %s | Email: %s",
                custCode, custName, phone, email);
    }
}
