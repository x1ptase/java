package core;

public class Customer {
    private String customerID; // Mã khách hàng (Cxxxx, Gxxxx, Kxxxx)
    private String name; // Họ tên khách hàng
    private String phoneNumber; // Số điện thoại (10 số, mạng hợp lệ)
    private String email; // Địa chỉ email

    // Constructor
    public Customer(String customerID, String name, String phoneNumber, String email) {
        this.customerID = customerID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters
    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString() - Hiển thị thông tin khách hàng
    @Override
    public String toString() {
        return String.format("Customer ID: %s | Name: %s | Phone: %s | Email: %s",
                customerID, name, phoneNumber, email);
    }
}
