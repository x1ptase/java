import core.RoomList;
import core.GuestList;
import tool.ConsoleInputter;

public class Main {
    public static void main(String[] args) {
        RoomList roomList=new RoomList();
        GuestList guestList=new GuestList();

        // load initial data
        roomList.readFromFile();
        guestList.readFromFile();

        int choice;
        do{
            RoomManager.displayMenu();
            choice=RoomManager.getUserChoice();

            switch(choice){
                case 1:
                    roomList.readFromFile();
                    break;
                case 2:
                    roomList.displayAllRooms();
                    break;
                case 3:
                    guestList.addGuest(roomList);
                    break;
                case 4:
                    guestList.updateGuest();
                    break;
                case 5:
                    guestList.searchGuestByNationalID(roomList);
                    break;
                case 6:
                    guestList.deleteGuestReservation(roomList);
                    break;
                case 7:
                    roomList.listVacantRooms(guestList);
                    break;
                case 8:
                    guestList.monthlyRevenueReport(roomList);
                    break;
                case 9:
                    roomList.revenueReportByRoomType(guestList);
                    break;
                case 10:
                    guestList.saveToFile();
                    System.out.println("Data has been saved successfully!");
                    break;
                case 11:
                    if(!guestList.isSaved()){
                        System.out.print("Do you want to save the data before exiting? (Y/N): ");
                        String confirm=ConsoleInputter.getStr("").toUpperCase();
                        if(confirm.equals("Y")){
                            guestList.saveToFile();
                            System.out.println("Data saved successfully.");
                        }
                    }
                    System.out.println("Exiting the program. GOOD LUCK!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while(choice != 11);
    }
}