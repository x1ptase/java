import java.util.Scanner;

public class RoomManager{
    private static final Scanner inp=new Scanner(System.in);

    public static void displayMenu(){
        System.out.println("\n========= ROOM MANAGEMENT MODULE =========");
        System.out.println("0. Import Room Data from Text File");
        System.out.println("1. Display Available Room List");
        System.out.println("2. Enter Guest Information");
        System.out.println("3. Update Guest Stay Information");
        System.out.println("4. Search Guest by National ID");
        System.out.println("5. Delete Guest Reservation Before Arrival");
        System.out.println("6. Display All Guest");
        System.out.println("7. List Vacant Rooms");
        System.out.println("8. Monthly Revenue Report");
        System.out.println("9. Revenue Report by Room Type");
        System.out.println("10. Save Guest Information");
        System.out.println("11. Quit");
        System.out.println("=========================================");
    }

    public static int getUserChoice(){
        int choice;
        while(true){
            try{
                System.out.print("Please select an option (0-11): ");
                choice=Integer.parseInt(inp.nextLine().trim());
                if(choice >= 0 && choice <= 11){
                    return choice;
                } else{
                    System.out.println("Invalid input. Please enter a number between 1 and 11.");
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }
}