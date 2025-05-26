import core.CustomerList;
import core.FeastMenuList;
import core.OrderList;
import tool.ConsoleInputter;

public class Main{
    public static void main(String[] args){
        // khởi tạo danh sách khách hàng, thực đơn và đơn hàng từ file
        CustomerList customerList=new CustomerList();
        customerList.readFromFile(); // dọc danh sách khách hàng từ file
        
        FeastMenuList menuList=new FeastMenuList();
        menuList.readFromFile(); // dọc danh sách thực đơn từ file

        OrderList orderList=new OrderList();
        orderList.readFromFile(); // dọc danh sách đơn hàng từ file

        int choice;
        do{
            OrderManager.displayMenu(); // hiển thị menu
            choice=OrderManager.getUserChoice(); // nhận lựa chọn từ người dùng

            switch(choice){
                case 1:
                    customerList.addNew();
                    break;
                case 2:
                    customerList.updateCustomer();
                    break;
                case 3:
                    customerList.searchCustomerByName();
                    break;
                case 4:
                    menuList.displayAllMenus();
                    break;
                case 5:
                    orderList.placeOrder(customerList, menuList);
                    break;
                case 6:
                    orderList.updateOrder(menuList);
                    break;
                case 7:
                    orderList.saveToFile();
                    customerList.saveToFile();
                    System.out.println("Data has been saved successfully!");
                    break;
                case 8:
//                    orderList.displayOrders(customerList, menuList);
                    orderList.displayOrders();
                    customerList.displayCustomers();
                    break;
                case 9:
                    if(!orderList.isSaved() || !customerList.isSaved()){
                        System.out.print("Do you want to save the data before exiting? (Y/N): ");
                        String confirm=ConsoleInputter.getStr("").toUpperCase();
                        if(confirm.equals("Y")){
                            orderList.saveToFile();
                            customerList.saveToFile();
                            System.out.println("Data saved successfully.");
                        }
                    }
                    System.out.println("Exiting the program. GOOD LUCK!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while(choice!=9);
    }
}
