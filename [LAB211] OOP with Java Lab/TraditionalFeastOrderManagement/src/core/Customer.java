package core;

public class Customer{
    private String custCode; // mã khách hàng (Cxxxx, Gxxxx, Kxxxx)
    private String custName; // tên khách hàng
    private String phone; // số điện thoại (9-11 số, VN)
    private String email; // dịa chỉ email
    
    // Ctor - Contructor
    public Customer(){ 
    }
    
    // Constructor
    public Customer(String custCode, String name, String phone, String email){
        this.custCode=custCode;
        this.custName=name;
        this.phone=phone;
        this.email=email;
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
    public String toString(){
        return String.format("Code: %s | Customer Name: %s | Phone: %s | Email: %s",
                custCode, custName, phone, email);
    }
}
