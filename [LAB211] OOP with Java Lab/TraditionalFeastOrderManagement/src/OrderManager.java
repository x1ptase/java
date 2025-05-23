import java.util.Scanner;

public class OrderManager{
    private static final Scanner inp=new Scanner(System.in);

    /**
     * displays the main menu of the program
     */
    public static void displayMenu(){
        System.out.println("\n========= TRADITIONAL FEAST ORDER MANAGEMENT =========");
        System.out.println("1. Register customers.");
        System.out.println("2. Update customer information.");
        System.out.println("3. Search for customer information by name.");
        System.out.println("4. Display feast menu.");
        System.out.println("5. Place a feast order.");
        System.out.println("6. Update order information.");
        System.out.println("7. Save data to file.");
        System.out.println("8. Display customer/Order lists");
        System.out.println("9. Exit");
        System.out.println("=======================================================");
    }

    public static int getUserChoice(){
        int choice;
        while(true){
            try{
                System.out.print("Please select an option (1-9): ");
                choice=Integer.parseInt(inp.nextLine().trim());
                if(choice>=1 && choice<=9){
                    return choice;
                } else{
                    System.out.println("Invalid input. Please enter a number between 1 and 9.");
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }
}
