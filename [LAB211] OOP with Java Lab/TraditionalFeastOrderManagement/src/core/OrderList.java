package core;

import tool.ConsoleInputter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class OrderList{
    private ArrayList<Order> orderList=new ArrayList<>();
    private static int orderCounter=1; // tạo mã đơn hàng tự động
    private static final String FILE_NAME="src/data/feast_order_service.dat";
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    private boolean existed=true; // biến kiểm tra trạng thái lưu


    // dat tiec
    public void placeOrder(CustomerList customerList, FeastMenuList menuList){
        System.out.println("\n===== PLACE A NEW ORDER =====");

        // Display available menus first
        menuList.displayAllMenus();
        System.out.println(); // Add spacing

        // nhập mã khách hàng
        Customer customer;
        while(true){
            String custCode=ConsoleInputter.getStr("Enter Customer ID: ");
            customer=customerList.findCustomerByID(custCode);
            if(customer!=null){
                break;
            }
            System.out.println("Customer ID not found. Please try again.");
        }

        // nhập mã thực đơn
        FeastMenu menuItem;
        while(true){
            String menuID=ConsoleInputter.getStr("Enter Set Menu ID: ");
            menuItem=menuList.findMenuByID(menuID);
            if(menuItem!=null){
                break;
            }
            System.out.println("Set Menu ID not found. Please try again.");
        }

        // nhập số bàn (phải lớn hơn 0)
        int numTable;
        while(true){
            numTable=ConsoleInputter.getInt("Enter number of tables", 1, Integer.MAX_VALUE);
            if(numTable>0){
                break;
            }
            System.out.println("Invalid input. Number of tables must be greater than 0.");
        }

        // nhập ngày tổ chức (phải là ngày tương lai)
        Date eventDate;
        while(true){
            eventDate=ConsoleInputter.getDate("Enter new event date (dd/MM/yyyy): ", "dd/MM/yyyy");
            if(!eventDate.after(new Date())){
                System.out.println("Invalid date! The event date must be in the future.");
                continue;
            }
            if(!isDuplicateOrder(customer.getCustCode(), menuItem.getMenuID(), eventDate)) {
                break;
            }
            System.out.println("Duplicate order! This customer already booked this menu on the same date.");
        }


        // tạo mã đơn hàng tự động
        String orderID="ORD" + (orderCounter++);

        // tính tổng tiền
        double totalCost=menuItem.getPrice()*numTable;

        // tạo đơn hàng mới
        Order newOrder=new Order(orderID, customer.getCustCode(), menuItem.getMenuID(), numTable, eventDate, totalCost);
        orderList.add(newOrder);

        // hiển thị thông tin đơn hàng
        System.out.println("\nOrder placed successfully!");
        System.out.println(newOrder);
        existed=false; //dánh dấu dữ liệu đã thay đổi

    }

    // cập nhật đơn hàng
    public void updateOrder(FeastMenuList menuList){
        System.out.println("\n===== UPDATE ORDER =====");

        // nhập mã đơn hàng cần cập nhật
        String orderID=ConsoleInputter.getStr("Enter Order ID: ").trim();
        Order order=findOrderByID(orderID);

        if(order==null){
            System.out.println("Order not found!");
            return;
        }

        // hiển thị thông tin hiện tại của đơn hàng
        System.out.println("Current Order Information: ");
        System.out.println(order);

        // nhập mã thực đơn mới (hoặc giữ nguyên)
        String newMenuID=ConsoleInputter.getStr("Enter new Set Menu ID (Press Enter to keep current): ").trim();
        if(!newMenuID.isEmpty()){
            FeastMenu newMenu=menuList.findMenuByID(newMenuID);
            if(newMenu!=null){
                order.setMenuID(newMenuID);
                order.setTotalCost(newMenu.getPrice()*order.getNumTable());
            } else{
                System.out.println("Invalid Set Menu ID. Keeping current value.");
            }
        }

        // nhập số bàn mới (hoặc giữ nguyên)
        String newNumTableStr=ConsoleInputter.getStr("Enter new number of tables (Press Enter to keep current): ").trim();
        if(!newNumTableStr.isEmpty()){
            int newTableCount=Integer.parseInt(newNumTableStr);
            if(newTableCount>0){
                order.setNumTable(newTableCount);
                order.setTotalCost(menuList.findMenuByID(order.getMenuID()).getPrice()*newTableCount);
            } else{
                System.out.println("Invalid number of tables. Keeping current value.");
            }
        }

        // nhập ngày tổ chức mới (hoặc giữ nguyên)
        String newEventDateStr=ConsoleInputter.getStr("Enter new event date (dd/MM/yyyy) (Press Enter to keep current): ").trim();
        if(!newEventDateStr.isEmpty()){
            Date newEventDate=ConsoleInputter.getDate("Enter new event date (dd/MM/yyyy): ", "dd/MMyyyy");
            if(newEventDate.after(new Date())){
                order.setEventDate(newEventDate);
            } else{
                System.out.println("Invalid date. Keeping current value.");
            }
        }

        System.out.println("Order updated successfully!");
        existed=false; // dánh dấu dữ liệu đã thay đổi

    }

    public boolean isSaved(){
        return existed;
    }

    // save danh sách đơn hàng vào file (CSV format)
    public void saveToFile(){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(FILE_NAME))){
            for(Order o : orderList){
                bw.write(o.getOrderID() + "," + o.getCustCode() + "," + o.getMenuID() + "," +
                        o.getNumTable() + "," + dateFormat.format(o.getEventDate()) + "," + o.getTotalCost());
                bw.newLine();
            }
            System.out.println("Order data has been successfully saved to " + FILE_NAME);
        } catch(IOException e){
            System.out.println("Error saving order data: " + e.getMessage());
        }
        existed=true; // dánh dấu dữ liệu đã được lưu

    }

    // dọc danh sách đơn hàng từ file
    public void readFromFile(){
        orderList.clear();
        try(BufferedReader br=new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line=br.readLine()) != null){
                String[] parts=line.split(",");
                if(parts.length==6){
                    orderList.add(new Order(
                        parts[0].trim(), // Order ID
                        parts[1].trim(), // Customer ID
                        parts[2].trim(), // Menu ID
                        Integer.parseInt(parts[3].trim()), // Table Count
                        dateFormat.parse(parts[4].trim()), // Event Date
                        Double.parseDouble(parts[5].trim()) // Cost
                    ));
                }
            }
            System.out.println("Order data loaded successfully.");
        } catch(IOException e){
            System.out.println("No existing order data found.");
        } catch(Exception e){
            System.out.println("Error reading order data: " + e.getMessage());
        }
    }


    // tìm đơn hàng theo ID
    private Order findOrderByID(String orderID){
        for(Order o : orderList){
            if(o.getOrderID().equalsIgnoreCase(orderID)){
                return o;
            }
        }
        return null;
    }

    // kiểm tra xem đơn hàng có bị trùng không
    private boolean isDuplicateOrder(String customerID, String menuID, Date eventDate){
        for(Order o : orderList){
            if(o.getCustCode().equals(customerID) &&
                o.getMenuID().equals(menuID) &&
                o.getEventDate().equals(eventDate)){
                return true;
            }
        }
        return false;
    }

    // hiển thị danh sách đơn hàng
    public void displayOrders(){
        if(orderList.isEmpty()){
            System.out.println("No orders available.");
            return;
        }

        System.out.println("\n===== ORDER LIST =====");
        System.out.println("+---------------------------------------------------------------------------------------------------+");
        System.out.println("| ID    | Customer ID | Set Menu | Tables | Event Date  | Cost                                      |");
        System.out.println("+---------------------------------------------------------------------------------------------------+");
        
        for(Order o : orderList){
            System.out.printf("| %-5s | %-11s | %-8s | %-6d | %-11s | %-,41.0f |\n",
                o.getOrderID(),
                o.getCustCode(),
                o.getMenuID(),
                o.getNumTable(),
                dateFormat.format(o.getEventDate()),
                o.getTotalCost());
        }
        System.out.println("+---------------------------------------------------------------------------------------------------+");
    }
//    public void displayOrders(CustomerList customerList, FeastMenuList menuList) {
//    if (orderList.isEmpty()) {
//        System.out.println("No orders available.");
//        return;
//    }
//
//    for (Order order : orderList) {
//        Customer customer = customerList.findCustomerByID(order.getCustCode());
//        FeastMenu menu = menuList.findMenuByID(order.getMenuID());
//
//        System.out.println("\nCustomer order information [Order ID: " + order.getOrderID() + "]");
//        System.out.println("---");
//        System.out.println("Customer code : " + customer.getCustCode());
//        System.out.println("Customer name : " + customer.getCustName());
//        System.out.println("Phone number : " + customer.getPhone());
//        System.out.println("Email    : " + customer.getEmail());
//        System.out.println("---");
//        System.out.println("Code of Set Menu: " + menu.getMenuID());
//        System.out.println("Set menu name   : " + menu.getName());
//        System.out.println("Event date    : " + dateFormat.format(order.getEventDate()));
//        System.out.println("Number of tables: " + order.getNumTable());
//        System.out.println("Price    : " + String.format("%,.0f", menu.getPrice()) + " Vnd");
//        System.out.println("Ingredients:");
//        System.out.println(menu.getIngredients());
//        System.out.println("---");
//        System.out.println("Total cost    : " + String.format("%,.0f", order.getTotalCost()) + " Vnd");
//        System.out.println();
//    }
//    }
}
