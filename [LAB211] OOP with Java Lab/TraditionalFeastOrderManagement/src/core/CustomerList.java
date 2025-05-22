package core;

import tool.ConsoleInputter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

public class CustomerList {
    private ArrayList<Customer> customerList=new ArrayList<>();
    private static final String FILE_NAME="customers.dat";
    public static final String custCodePattern="^[CGKcgk]\\d{4}";
    public static final String cusNamePattern="^.{2,25}$";
    public static final String phonePattern = "^(03[2-9]|05[2-9]|08[1-9]|09[0-9])\\d{7}$";
    public static final String emailPattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.+]+\\.[a-zA-Z]{2,}$";
    private boolean existed=true; // biến kiểm tra trạng thái lưu

    /**
     * them khach hang moi vao danh sach
     */
    public void addCustomer(){
        System.out.println("\n===== ADD NEW CUSTOMER =====");

        while(true){
            // Nhập mã khách hàng
            String custCode;
            do{
                custCode=ConsoleInputter.getStr("New cust code", CustomerList.custCodePattern, "Pattern: CGK + 4 digits");
                if(isCustomerExist(custCode)){
                    System.out.println("Customer ID already exists. Please enter a unique ID.");
                }
            } while(isCustomerExist(custCode));

            //nhap ten
            String name=ConsoleInputter.getStr("New name", cusNamePattern, "Name length 2-25!");
            //nhap phone
            String phone=ConsoleInputter.getStr("New phone", phonePattern, "9 or 11 numbers!");
            // nhap mail
            String email=ConsoleInputter.getStr("New email", emailPattern, "Unvalid email!");

            // tạo đối tượng khách hàng mới
            Customer newCust=new Customer(custCode, name, phone, email);
            customerList.add(newCust);
            System.out.println("Customer added successfully!");

            // hỏi người dùng có muốn tiếp tục không
            System.out.print("Do you want to add another customer? (Y/N): ");
            String choice=ConsoleInputter.getStr("").trim().toUpperCase();
            if(!choice.equals("Y")){
                break;
            }
        }
        existed=false; // dánh dấu dữ liệu đã thay đổi

    }

    // cập nhật thông tin khách hàng
    public void updateCustomer(){
        System.out.println("\n===== UPDATE CUSTOMER INFORMATION =====");

        // Nhập mã khách hàng cần cập nhật
        String custCode=ConsoleInputter.getStr("Enter Customer ID: ").trim();
        Customer customer = findCustomerByID(custCode);

        if(customer==null){
            System.out.println("Customer not found!");
            return;
        }

        // hiển thị thông tin hiện tại của khách hàng
        System.out.println("Current Information: " + customer);

        // cập nhật tên khách hàng
        String newName=ConsoleInputter.getStr("Enter new name (Press Enter to keep current): ");
        if(!newName.isEmpty() && ConsoleInputter.isValid(newName,cusNamePattern)){
            customer.setCustName(newName);
        }

        // Cập nhật số điện thoại
        String newPhone=ConsoleInputter.getStr("Enter new phone number (Press Enter to keep current): ");
        if (!newPhone.isEmpty() && ConsoleInputter.isValid(newPhone, phonePattern)){
            customer.setPhone(newPhone);
        }

        // Cập nhật email
        String newEmail=ConsoleInputter.getStr("Enter new email (Press Enter to keep current): ");
        if(!newEmail.isEmpty() && ConsoleInputter.isValid(newEmail, emailPattern)){
            customer.setEmail(newEmail);
        }

        System.out.println("Customer information updated successfully!");
        existed=false; // dánh dấu dữ liệu đã thay đổi

    }

    // tìm kiếm khách hàng theo tên
    public void searchCustomerByName(){
        System.out.println("\n===== SEARCH CUSTOMER BY NAME =====");
        String searchKey=ConsoleInputter.getStr("Enter full or partial name: ").trim().toLowerCase();

        ArrayList<Customer> matchedCustomers=new ArrayList<>();
        for(Customer customer : customerList){
            if(customer.getCustName().toLowerCase().contains(searchKey)){
                matchedCustomers.add(customer);
            }
        }

        if(matchedCustomers.isEmpty()){
            System.out.println("No one matches the search criteria!");
        } else{
            // sắp xếp danh sách theo tên
            Collections.sort(matchedCustomers, Comparator.comparing(Customer::getCustName));

            System.out.println("\n===== MATCHING CUSTOMERS =====");
            for(Customer c : matchedCustomers){
                System.out.println(c);
            }
        }
    }

    // save danh sách khách hàng vào file (CSV format)
    public void saveToFile(){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(FILE_NAME))){
            for(Customer c : customerList){
                bw.write(c.getCustCode() + "," + c.getCustName() + "," + c.getPhone() + "," + c.getEmail());
                bw.newLine();
            }
            System.out.println("Customer data has been successfully saved to " + FILE_NAME);
        } catch(IOException e){
            System.out.println("Error saving customer data: " + e.getMessage());
        }
        existed=true; // dánh dấu dữ liệu đã được lưu

    }

    // read danh sách khách hàng từ file
    public void readFromFile(){
        customerList.clear();
        try(BufferedReader br=new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line=br.readLine())!=null){
                String[] parts=line.split(",");
                if(parts.length==4){
                    customerList.add(new Customer(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                }
            }
            System.out.println("Customer data loaded successfully.");
        } catch(IOException e){
            System.out.println("No existing customer data found.");
        }
    }

    // find khách hàng theo ID
    public Customer findCustomerByID(String customerID){
        for(Customer c : customerList){
            if(c.getCustCode().equals(customerID)){
                return c;
            }
        }
        return null;
    }

    // kiểm tra xem khách hàng có tồn tại hay không
    private boolean isCustomerExist(String custCode){
        for(Customer c : customerList){
            if (c.getCustCode().equals(custCode)){
                return true;
            }
        }
        return false;
    }

    // kiểm tra xem dữ liệu đã được lưu chưa
    public boolean isSaved(){
        return existed;
    }

    // hiển thị danh sách khách hàng
    public void displayCustomers(){
        if(customerList.isEmpty()){
            System.out.println("No customer data available.");
            return;
        }

        System.out.println("\n===== CUSTOMER LIST =====");
        System.out.println("+----------------------------------------------------------------------------------------------------+");
        System.out.println("| Code        | Customer Name            | Phone        | Email                                      |");
        System.out.println("+----------------------------------------------------------------------------------------------------+");
        
        for(Customer c : customerList){
            System.out.printf("| %-11s | %-23s | %-12s | %-42s |\n", 
                c.getCustCode(), 
                c.getCustName(), 
                c.getPhone(),
                c.getEmail());
        }
        System.out.println("+----------------------------------------------------------------------------------------------------+");
    }
}
