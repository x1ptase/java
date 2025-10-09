import java.util.Scanner;

public class Program{
    static void printMenu(){
        System.out.println("----------***----------");
        System.out.println("1.Print all items");
        System.out.println("2.Add new item");
        System.out.println("3.Update item");
        System.out.println("4.Remove item");
        System.out.println("Others: Exit");
        System.out.print("Enter choice:");
    }
    
    public static void main(String[] args) {
        try{
            String itemID, itemName;
            int quantity, choice;
            ItemDAO itemDAO=new ItemDAO();
            Scanner sc=new Scanner(System.in);
            printMenu();
            choice=Integer.parseInt(sc.nextLine());
            while(choice >= 1 && choice <= 4){
                if(choice == 1){
                    itemDAO.printItems();
                } else if(choice == 2){
                    System.out.println("Enter ItemID:");
                    itemID=sc.nextLine();
                    System.out.println("Enter ItemName:");
                    itemName=sc.nextLine();
                    System.out.println("Enter Quantity:");
                    quantity=Integer.parseInt(sc.nextLine());
                    itemDAO.AddNewItem(itemID, itemName, quantity);
                } else if(choice == 3){
                    System.out.println("Enter ItemID:");
                    itemID=sc.nextLine();
                    System.out.println("Enter ItemName:");
                    itemName=sc.nextLine();
                    System.out.println("Enter Quantity:");
                    quantity=Integer.parseInt(sc.nextLine());
                    itemDAO.UpdateItem(itemID, itemName, quantity);
                } else if(choice == 4){
                    System.out.println("Enter ItemID:");
                    itemID=sc.nextLine();
                    itemDAO.RemoveItem(itemID);
                } else{
                    System.exit(0);
                }
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}