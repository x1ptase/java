package core;

import data.Inputter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class OrderList {
    private ArrayList<Order> orderList = new ArrayList<>();
    private static int orderCounter = 1; // Tạo mã đơn hàng tự động
    private static final String FILE_NAME = "feast_order_service.dat";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private boolean saved = true; // Biến kiểm tra trạng thái lưu


    // Đặt tiệc
    public void placeOrder(CustomerList customerList, FeastMenuList menuList) {
        System.out.println("\n===== PLACE A NEW ORDER =====");

        // Display available menus first
        menuList.displayAllMenus();
        System.out.println(); // Add spacing

        // Nhập mã khách hàng
        Customer customer;
        while (true) {
            String customerID = Inputter.getString("Enter Customer ID: ");
            customer = customerList.findCustomerByID(customerID);
            if (customer != null) {
                break;
            }
            System.out.println("❌ Customer ID not found. Please try again.");
        }

        // Nhập mã thực đơn
        FeastMenu menuItem;
        while (true) {
            String menuID = Inputter.getString("Enter Set Menu ID: ");
            menuItem = menuList.findMenuByID(menuID);
            if (menuItem != null) {
                break;
            }
            System.out.println("❌ Set Menu ID not found. Please try again.");
        }

        // Nhập số bàn (phải lớn hơn 0)
        int tableCount;
        while (true) {
            tableCount = Inputter.getInt("Enter number of tables: ");
            if (tableCount > 0) {
                break;
            }
            System.out.println("❌ Invalid input. Number of tables must be greater than 0.");
        }

        // Nhập ngày tổ chức (phải là ngày tương lai)
        Date eventDate;
        while (true) {
            eventDate = Inputter.getFutureDate("Enter event date (dd/MM/yyyy): ");
            if (!isDuplicateOrder(customer.getCustCode(), menuItem.getMenuID(), eventDate)) {
                break;
            }
            System.out.println("❌ Duplicate order! This customer already booked this menu on the same date.");
        }

        // Tạo mã đơn hàng tự động
        String orderID = "ORD" + (orderCounter++);

        // Tính tổng tiền
        double totalCost = menuItem.getPrice() * tableCount;

        // Tạo đơn hàng mới
        Order newOrder = new Order(orderID, customer.getCustCode(), menuItem.getMenuID(), tableCount, eventDate, totalCost);
        orderList.add(newOrder);

        // Hiển thị thông tin đơn hàng
        System.out.println("\n✅ Order placed successfully!");
        System.out.println(newOrder);
        saved = false; // Đánh dấu dữ liệu đã thay đổi

    }

    // Cập nhật đơn hàng
    public void updateOrder(FeastMenuList menuList) {
        System.out.println("\n===== UPDATE ORDER =====");

        // Nhập mã đơn hàng cần cập nhật
        String orderID = Inputter.getString("Enter Order ID: ").trim();
        Order order = findOrderByID(orderID);

        if (order == null) {
            System.out.println("❌ Order not found!");
            return;
        }

        // Hiển thị thông tin hiện tại của đơn hàng
        System.out.println("Current Order Information: ");
        System.out.println(order);

        // Nhập mã thực đơn mới (hoặc giữ nguyên)
        String newMenuID = Inputter.getString("Enter new Set Menu ID (Press Enter to keep current): ").trim();
        if (!newMenuID.isEmpty()) {
            FeastMenu newMenu = menuList.findMenuByID(newMenuID);
            if (newMenu != null) {
                order.setMenuID(newMenuID);
                order.setTotalCost(newMenu.getPrice() * order.getTableCount());
            } else {
                System.out.println("❌ Invalid Set Menu ID. Keeping current value.");
            }
        }

        // Nhập số bàn mới (hoặc giữ nguyên)
        String newTableCountStr = Inputter.getString("Enter new number of tables (Press Enter to keep current): ").trim();
        if (!newTableCountStr.isEmpty()) {
            int newTableCount = Integer.parseInt(newTableCountStr);
            if (newTableCount > 0) {
                order.setTableCount(newTableCount);
                order.setTotalCost(menuList.findMenuByID(order.getMenuID()).getPrice() * newTableCount);
            } else {
                System.out.println("❌ Invalid number of tables. Keeping current value.");
            }
        }

        // Nhập ngày tổ chức mới (hoặc giữ nguyên)
        String newEventDateStr = Inputter.getString("Enter new event date (dd/MM/yyyy) (Press Enter to keep current): ").trim();
        if (!newEventDateStr.isEmpty()) {
            Date newEventDate = Inputter.getFutureDate("Enter new event date (dd/MM/yyyy): ");
            if (newEventDate.after(new Date())) {
                order.setEventDate(newEventDate);
            } else {
                System.out.println("❌ Invalid date. Keeping current value.");
            }
        }

        System.out.println("✅ Order updated successfully!");
        saved = false; // Đánh dấu dữ liệu đã thay đổi

    }

    public boolean isSaved() {
        return saved;
    }

    // Lưu danh sách đơn hàng vào file (CSV format)
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Order o : orderList) {
                bw.write(o.getOrderID() + "," + o.getCustomerID() + "," + o.getMenuID() + "," +
                        o.getTableCount() + "," + dateFormat.format(o.getEventDate()) + "," + o.getTotalCost());
                bw.newLine();
            }
            System.out.println("✅ Order data has been successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("❌ Error saving order data: " + e.getMessage());
        }
        saved = true; // Đánh dấu dữ liệu đã được lưu

    }

    // Đọc danh sách đơn hàng từ file
    public void readFromFile() {
        orderList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    orderList.add(new Order(
                        parts[0].trim(), // Order ID
                        parts[1].trim(), // Customer ID
                        parts[2].trim(), // Menu ID
                        Integer.parseInt(parts[3].trim()), // Table Count
                        dateFormat.parse(parts[4].trim()), // Event Date
                        Double.parseDouble(parts[5].trim()) // Total Cost
                    ));
                }
            }
            System.out.println("✅ Order data loaded successfully.");
        } catch (IOException e) {
            System.out.println("❌ No existing order data found.");
        } catch (Exception e) {
            System.out.println("❌ Error reading order data: " + e.getMessage());
        }
    }


    // Tìm đơn hàng theo ID
    private Order findOrderByID(String orderID) {
        for (Order o : orderList) {
            if (o.getOrderID().equalsIgnoreCase(orderID)) {
                return o;
            }
        }
        return null;
    }

    // Kiểm tra xem đơn hàng có bị trùng không
    private boolean isDuplicateOrder(String customerID, String menuID, Date eventDate) {
        for (Order o : orderList) {
            if (o.getCustomerID().equals(customerID) &&
                o.getMenuID().equals(menuID) &&
                o.getEventDate().equals(eventDate)) {
                return true;
            }
        }
        return false;
    }

    // Hiển thị danh sách đơn hàng
    public void displayOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        System.out.println("\n===== ORDER LIST =====");
        System.out.println("+----------------------------------------------------------------------------------------------------+");
        System.out.println("| Order ID | Customer ID | Menu ID | Tables | Event Date  | Total Cost (VND) |");
        System.out.println("+----------------------------------------------------------------------------------------------------+");
        
        for (Order o : orderList) {
            System.out.printf("| %-8s | %-11s | %-7s | %6d | %-11s | %,15.2f |\n",
                o.getOrderID(),
                o.getCustomerID(),
                o.getMenuID(),
                o.getTableCount(),
                dateFormat.format(o.getEventDate()),
                o.getTotalCost());
        }
        
        System.out.println("+----------------------------------------------------------------------------------------------------+");
    }
}
