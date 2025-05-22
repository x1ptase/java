package core;

import data.Acceptable;
import data.Inputter;
import tool.ConsoleInputter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

public class CustomerList {
    private ArrayList<Customer> customerList = new ArrayList<>();
    private static final String FILE_NAME = "customers.dat";
    public static final String custCodePattern = "^[CGKcgk]\\d{4}$";
    public static final String cusNamePattern = "^[a-zA-Z ]{2,25}$";
    public static final String phonePattern = "^\\d{9}|\\d{11}$";
    public static final String emailPattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+.[a-zA-Z0-9_-]+$";
    private boolean existed=true; // Biến kiểm tra trạng thái lưu

    /**
     * them khach hang moi vao danh sach
     */
    public void addCustomer() {
        System.out.println("\n===== ADD NEW CUSTOMER =====");

        while (true) {
            // Nhập mã khách hàng
            String custCode;
            do {
                custCode = ConsoleInputter.getStr("New cust code", CustomerList.custCodePattern, "Pattern: CGK + 4 digits");
                if (isCustomerExist(custCode)) {
                    System.out.println("Customer ID already exists. Please enter a unique ID.");
                }
            } while (isCustomerExist(custCode));

            //nhap ten
            String name=ConsoleInputter.getStr("New name", cusNamePattern, "Name length 2-25!");
            //nhap phone
            String phone=ConsoleInputter.getStr("New phone", phonePattern, "9 or 11 numbers!");
            // nhap mail
            String email=ConsoleInputter.getStr("New email", emailPattern, "Unvalid email!");

            // Tạo đối tượng khách hàng mới
            Customer newCustomer = new Customer(custCode, name, phone, email);
            customerList.add(newCustomer);
            System.out.println("Customer added successfully!");

            // Hỏi người dùng có muốn tiếp tục không
            System.out.print("Do you want to add another customer? (Y/N): ");
            String choice=ConsoleInputter.getStr("").trim().toUpperCase();
            if (!choice.equals("Y")){
                break;
            }
        }
        existed=false; // Đánh dấu dữ liệu đã thay đổi

    }

    // Cập nhật thông tin khách hàng
    public void updateCustomer() {
        System.out.println("\n===== UPDATE CUSTOMER INFORMATION =====");

        // Nhập mã khách hàng cần cập nhật
        String customerID = Inputter.getString("Enter Customer ID: ").trim();
        Customer customer = findCustomerByID(customerID);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        // Hiển thị thông tin hiện tại của khách hàng
        System.out.println("Current Information: " + customer);

        // Cập nhật tên khách hàng
        String newName = Inputter.getString("Enter new name (Press Enter to keep current): ");
        if (!newName.isEmpty() && Acceptable.isValid(newName, Acceptable.CUSTOMER_NAME)) {
            customer.setCustName(newName);
        }

        // Cập nhật số điện thoại
        String newPhone = Inputter.getString("Enter new phone number (Press Enter to keep current): ");
        if (!newPhone.isEmpty() && Acceptable.isValid(newPhone, Acceptable.PHONE_NUMBER)) {
            customer.setPhone(newPhone);
        }

        // Cập nhật email
        String newEmail = Inputter.getString("Enter new email (Press Enter to keep current): ");
        if (!newEmail.isEmpty() && Acceptable.isValid(newEmail, Acceptable.EMAIL)) {
            customer.setEmail(newEmail);
        }

        System.out.println("Customer information updated successfully!");
        existed = false; // Đánh dấu dữ liệu đã thay đổi

    }

    // Tìm kiếm khách hàng theo tên
    public void searchCustomerByName() {
        System.out.println("\n===== SEARCH CUSTOMER BY NAME =====");
        String searchKey = Inputter.getString("Enter full or partial name: ").trim().toLowerCase();

        ArrayList<Customer> matchedCustomers = new ArrayList<>();
        for (Customer customer : customerList) {
            if (customer.getCustName().toLowerCase().contains(searchKey)) {
                matchedCustomers.add(customer);
            }
        }

        if (matchedCustomers.isEmpty()) {
            System.out.println("No one matches the search criteria!");
        } else {
            // Sắp xếp danh sách theo tên
            Collections.sort(matchedCustomers, Comparator.comparing(Customer::getCustName));

            System.out.println("\n===== MATCHING CUSTOMERS =====");
            for (Customer c : matchedCustomers) {
                System.out.println(c);
            }
        }
    }

    // Lưu danh sách khách hàng vào file (CSV format)
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer c : customerList) {
                bw.write(c.getCustCode() + "," + c.getCustName() + "," + c.getPhone() + "," + c.getEmail());
                bw.newLine();
            }
            System.out.println("Customer data has been successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }
        existed = true; // Đánh dấu dữ liệu đã được lưu

    }

    // Đọc danh sách khách hàng từ file
    public void readFromFile() {
        customerList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    customerList.add(new Customer(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                }
            }
            System.out.println("Customer data loaded successfully.");
        } catch (IOException e) {
            System.out.println("No existing customer data found.");
        }
    }

    // Tìm khách hàng theo ID
    public Customer findCustomerByID(String customerID) {
        for (Customer c : customerList) {
            if (c.getCustCode().equals(customerID)) {
                return c;
            }
        }
        return null;
    }

    // Kiểm tra xem khách hàng có tồn tại hay không
    private boolean isCustomerExist(String customerID) {
        for (Customer c : customerList) {
            if (c.getCustCode().equals(customerID)) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra xem dữ liệu đã được lưu chưa
    public boolean isSaved() {
        return existed;
    }

    // Hiển thị danh sách khách hàng
    public void displayCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("No customer data available.");
            return;
        }

        System.out.println("\n===== CUSTOMER LIST =====");
        System.out.println("+----------------------------------------------------------------------------------------------------+");
        System.out.println("| Customer ID | Name                     | Phone Number | Email                                      |");
        System.out.println("+----------------------------------------------------------------------------------------------------+");
        
        for (Customer c : customerList) {
            System.out.printf("| %-11s | %-23s | %-12s | %-42s |\n", 
                c.getCustCode(), 
                c.getCustName(), 
                c.getPhone(),
                c.getEmail());
        }
        
        System.out.println("+----------------------------------------------------------------------------------------------------+");
    }
}
