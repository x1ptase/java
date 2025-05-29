package core;

import tool.ConsoleInputter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class OrderList{
    public ArrayList<Order> orderList=new ArrayList<>();
    public static final String FILE_NAME="src/data/feast_order_service.dat";
    public static final SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    private boolean existed=true;

    public void placeOrder(CustomerList customerList, FeastMenuList menuList){
        System.out.println("\n===== PLACE A NEW ORDER =====");
        menuList.displayAllMenus();
        System.out.println();

        Customer customer;
        while(true){
            String custCode=ConsoleInputter.getStr("Enter Customer ID: ");
            customer=customerList.findCustomerByID(custCode);
            if(customer!=null){
                break;
            }
            System.out.println("Customer ID not found. Please try again.");
        }

        FeastMenu menuItem;
        while(true){
            String menuID=ConsoleInputter.getStr("Enter Set Menu ID: ");
            menuItem=menuList.findMenuByID(menuID);
            if(menuItem!=null){
                break;
            }
            System.out.println("Set Menu ID not found. Please try again.");
        }

        int numTable=ConsoleInputter.getInt("Enter number of tables", 1, Integer.MAX_VALUE);

        Date eventDate;
        while(true){
            eventDate=ConsoleInputter.getDate("Enter event date (dd/MM/yyyy): ", "dd/MM/yyyy");
            if(!eventDate.after(new Date())){
                System.out.println("Invalid date! The event date must be in the future.");
                continue;
            }
            if(!isDuplicateOrder(customer.getCustCode(), menuItem.getMenuID(), eventDate)){
                break;
            }
            System.out.println("Duplicate order! This customer already booked this menu on the same date.");
        }
        
        // tạo mã đơn hàng tự dộng
        String orderID=ConsoleInputter.dateKeyGen();
        
        // tính tổng tiền
        double totalCost=menuItem.getPrice()*numTable;
        
        // tạo đơn hàng mới
        String priceStr=String.format("%,d", (int)menuItem.getPrice());
        Order newOrder=new Order(orderID, customer.getCustCode(), menuItem.getMenuID(), 
                                 numTable, priceStr, eventDate, totalCost);
        orderList.add(newOrder);
        
        // Hiển thị thông tin đơn hàng theo form yêu cầu
        System.out.println("\nOrder placed successfully!");
        System.out.println("---------------------------------------------");
        System.out.println("Customer order information [Order ID: " + orderID + "]");
        System.out.println("---------------------------------------------");
        System.out.println("Customer code : " + customer.getCustCode());
        System.out.println("Customer name : " + customer.getCustName());
        System.out.println("Phone number  : " + customer.getPhone());
        System.out.println("Email         : " + customer.getEmail());
        System.out.println("---------------------------------------------");
        System.out.println("Code of Set Menu: " + menuItem.getMenuID());
        System.out.println("Set menu name   : " + menuItem.getName());
        System.out.println("Event date      : " + new SimpleDateFormat("dd/MM/yyyy").format(eventDate));
        System.out.println("Number of tables: " + numTable);
        System.out.println("Price           : " + String.format("%,d Vnd", (int)menuItem.getPrice()));
        System.out.println("Ingredients:");

        // hiển thị ingredients theo từng phần
        String[] categories=menuItem.getIngredients().split("#");
        for(String category : categories){
            String cleanedCategory=category.trim();
            if(cleanedCategory.startsWith("+ ")){
                cleanedCategory=cleanedCategory.substring(2);
            }
            System.out.println("+ " + cleanedCategory.replace(";", "; "));
        }

        System.out.println("---------------------------------------------");
        System.out.println("Total           : " + String.format("%,d Vnd", (int)totalCost));
        System.out.println("---------------------------------------------");
        
        existed=false;
    }
    
    
    // cập nhật đơn hàng
    public void updateOrder(FeastMenuList menuList){
        System.out.println("\n===== UPDATE ORDER =====");
        String orderID = ConsoleInputter.getStr("Enter Order ID: ").trim();
        Order order = findOrderByID(orderID);

        if(order==null){
            System.out.println("Order not found!");
            return;
        }

        System.out.println("Current Order Information: ");
        System.out.println(order);

        String newMenuID=ConsoleInputter.getStr("Enter new Set Menu ID (Press Enter to keep current): ").trim();
        if (!newMenuID.isEmpty()){
            FeastMenu newMenu=menuList.findMenuByID(newMenuID);
            if(newMenu != null){
                order.setMenuID(newMenuID);
                order.setPrice(String.format("%,d", (int)newMenu.getPrice()));
                order.setTotalCost(newMenu.getPrice() * order.getNumTable());
            } else{
                System.out.println("Invalid Set Menu ID. Keeping current value.");
            }
        }

        String newNumTableStr=ConsoleInputter.getStr("Enter new number of tables (Press Enter to keep current): ").trim();
        if (!newNumTableStr.isEmpty()){
            try {
                int newTableCount=Integer.parseInt(newNumTableStr);
                if(newTableCount > 0){
                    order.setNumTable(newTableCount);
                    FeastMenu menu = menuList.findMenuByID(order.getMenuID());
                    order.setTotalCost(menu.getPrice() * newTableCount);
                } else {
                    System.out.println("Invalid number of tables. Keeping current value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Keeping current value.");
            }
        }

        String newEventDateStr = ConsoleInputter.getStr("Enter new event date (dd/MM/yyyy) (Press Enter to keep current): ").trim();
        if (!newEventDateStr.isEmpty()) {
            try {
                Date newEventDate = ConsoleInputter.getDate("Enter new event date (dd/MM/yyyy): ", "dd/MM/yyyy");
                if (newEventDate.after(new Date())) {
                    order.setEventDate(newEventDate);
                } else {
                    System.out.println("Invalid date. Keeping current value.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Keeping current value.");
            }
        }

        System.out.println("Order updated successfully!");
        existed = false;
    }

    public boolean isSaved() {
        return existed;
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Order o : orderList) {
                bw.write(o.getOrderID() + "," + dateFormat.format(o.getEventDate()) + "," + 
                        o.getCustCode() + "," + o.getMenuID() + "," + o.getPrice() + "," + 
                        o.getNumTable() + "," + o.getTotalCost());
                bw.newLine();
            }
            System.out.println("Order data has been successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving order data: " + e.getMessage());
        }
        existed = true;
    }

    public void readFromFile() {
        orderList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    orderList.add(new Order(
                        parts[0].trim(), // Order ID
                        parts[2].trim(), // Customer ID
                        parts[3].trim(), // Menu ID
                        Integer.parseInt(parts[5].trim()), // Table Count
                        parts[4].trim(), // Price
                        dateFormat.parse(parts[1].trim()), // Event Date
                        Double.parseDouble(parts[6].trim()) // Cost
                    ));
                }
            }
            System.out.println("Order data loaded successfully.");
        } catch (IOException e) {
            System.out.println("No existing order data found.");
        } catch (Exception e) {
            System.out.println("Error reading order data: " + e.getMessage());
        }
    }

    private Order findOrderByID(String orderID) {
        for (Order o : orderList) {
            if (o.getOrderID().equalsIgnoreCase(orderID)) {
                return o;
            }
        }
        return null;
    }

    private boolean isDuplicateOrder(String customerID, String menuID, Date eventDate) {
        for (Order o : orderList) {
            if (o.getCustCode().equals(customerID) &&
                o.getMenuID().equals(menuID) &&
                dateFormat.format(o.getEventDate()).equals(dateFormat.format(eventDate))) {
                return true;
            }
        }
        return false;
    }

    public void displayOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        System.out.println("\n===== ORDER LIST =====");
        System.out.println("+------------------------------------------------------------------------------------------+");
        System.out.println("| ID             | Event Date  | Customer ID | Set Menu | Price     | Tables |        Cost |");
        System.out.println("+------------------------------------------------------------------------------------------+");
        
        for (Order o : orderList) {
            System.out.printf("| %-14s | %-11s | %-11s | %-8s | %-8s | %-6d | %,11.0f |\n",
                o.getOrderID(),
                dateFormat.format(o.getEventDate()),
                o.getCustCode(),
                o.getMenuID(),
                o.getPrice(),
                o.getNumTable(),
                o.getTotalCost());
        }
        System.out.println("+------------------------------------------------------------------------------------------+");
    }
}