package core;

import data.Acceptable;
import data.Inputter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

public class CustomerList {
    private ArrayList<Customer> customerList = new ArrayList<>();
    private static final String FILE_NAME = "customers.dat";
    private boolean saved = true; // Biến kiểm tra trạng thái lưu

    /**
     * them khach hang moi vao danh sach
     */
    public void addCustomer() {
        System.out.println("\n===== ADD NEW CUSTOMER =====");

        while (true) {
            // Nhập mã khách hàng
            String customerID;
            do {
                customerID = Inputter.getStringWithPattern("Enter customer ID (Cxxxx, Gxxxx, Kxxxx): ",
                        Acceptable.CUSTOMER_ID);
                if (isCustomerExist(customerID)) {
                    System.out.println("Customer ID already exists. Please enter a unique ID.");
                }
            } while (isCustomerExist(customerID));

            // Nhập tên khách hàng
            String name = Inputter.getStringWithPattern("Enter name (2-25 characters): ", Acceptable.CUSTOMER_NAME);

            // Nhập số điện thoại
            String phone = Inputter.getStringWithPattern("Enter phone number (10 digits): ", Acceptable.PHONE_NUMBER);

            // Nhập email
            String email = Inputter.getStringWithPattern("Enter email: ", Acceptable.EMAIL);

            // Tạo đối tượng khách hàng mới
            Customer newCustomer = new Customer(customerID, name, phone, email);
            customerList.add(newCustomer);
            System.out.println("Customer added successfully!");

            // Hỏi người dùng có muốn tiếp tục không
            System.out.print("Do you want to add another customer? (Y/N): ");
            String choice = Inputter.getString("").trim().toUpperCase();
            if (!choice.equals("Y")) {
                break;
            }
        }
        saved = false; // Đánh dấu dữ liệu đã thay đổi

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
            customer.setName(newName);
        }

        // Cập nhật số điện thoại
        String newPhone = Inputter.getString("Enter new phone number (Press Enter to keep current): ");
        if (!newPhone.isEmpty() && Acceptable.isValid(newPhone, Acceptable.PHONE_NUMBER)) {
            customer.setPhoneNumber(newPhone);
        }

        // Cập nhật email
        String newEmail = Inputter.getString("Enter new email (Press Enter to keep current): ");
        if (!newEmail.isEmpty() && Acceptable.isValid(newEmail, Acceptable.EMAIL)) {
            customer.setEmail(newEmail);
        }

        System.out.println("Customer information updated successfully!");
        saved = false; // Đánh dấu dữ liệu đã thay đổi

    }

    // Tìm kiếm khách hàng theo tên
    public void searchCustomerByName() {
        System.out.println("\n===== SEARCH CUSTOMER BY NAME =====");
        String searchKey = Inputter.getString("Enter full or partial name: ").trim().toLowerCase();

        ArrayList<Customer> matchedCustomers = new ArrayList<>();
        for (Customer customer : customerList) {
            if (customer.getName().toLowerCase().contains(searchKey)) {
                matchedCustomers.add(customer);
            }
        }

        if (matchedCustomers.isEmpty()) {
            System.out.println("No one matches the search criteria!");
        } else {
            // Sắp xếp danh sách theo tên
            Collections.sort(matchedCustomers, Comparator.comparing(Customer::getName));

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
                bw.write(c.getCustomerID() + "," + c.getName() + "," + c.getPhoneNumber() + "," + c.getEmail());
                bw.newLine();
            }
            System.out.println("Customer data has been successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }
        saved = true; // Đánh dấu dữ liệu đã được lưu

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
            if (c.getCustomerID().equals(customerID)) {
                return c;
            }
        }
        return null;
    }

    // Kiểm tra xem khách hàng có tồn tại hay không
    private boolean isCustomerExist(String customerID) {
        for (Customer c : customerList) {
            if (c.getCustomerID().equals(customerID)) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra xem dữ liệu đã được lưu chưa
    public boolean isSaved() {
        return saved;
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
                c.getCustomerID(), 
                c.getName(), 
                c.getPhoneNumber(),
                c.getEmail());
        }
        
        System.out.println("+----------------------------------------------------------------------------------------------------+");
    }
}
