import tool.ConsoleInputter;
import core.*;

public class RoomManager{
    public static void main(String[] args){
        RoomList roomList=new RoomList();
        GuestList guestList=new GuestList();

         try{
            roomList.readFromFile(RoomList.FILE_NAME);
            guestList.readFromFile(GuestList.FILE_NAME);
        } catch(Exception e){
            System.out.println("Cannot read file: " + e.getMessage());
            return;
        }
        
        int choice;
        boolean changed=false;
        boolean isSaved=false;
        do{
            choice=ConsoleInputter.intMenu(
                    "Display Available Room List",
                    "Enter Guest Information",
                    "Update Guest Stay Information",
                    "Search Guest by National ID",
                    "Delete Guest Reservation Before Arrival",
                    "Display All Guest",
                    "List Vacant Rooms",
                    "Monthly Revenue Report",
                    "Revenue Report by Room Type",
                    "Save Guest Information",
                    "Exit");
            if(choice >= 2 && choice <= 5){
                changed=true;
            }
            
            switch(choice){
                case 1:
                    roomList.displayAll();
                    ConsoleInputter.pause();
                    break;
                case 2:
                    guestList.addGuest();
                    ConsoleInputter.pause();
                    break;
                case 3:
                    guestList.updateGuest();
                    ConsoleInputter.pause();
                    break;
                case 4:
                    guestList.searchByID();
                    ConsoleInputter.pause();
                    break;
                case 5:
                    guestList.deleteGuest();
                    ConsoleInputter.pause();
                    break;
                case 6:
                    guestList.displayAll();
                    ConsoleInputter.pause();
                    break;
                case 7:
                    roomList.vacantRoomList(guestList).displayAll();
                    ConsoleInputter.pause();
                    break;
                case 8:
                    roomList.monthlyReport(guestList);
                    ConsoleInputter.pause();
                    break;
                case 9:
                    roomList.revenueReport(guestList);
                    ConsoleInputter.pause();
                    break;
                case 10:
                    guestList.saveToFile(GuestList.FILE_NAME);
                    ConsoleInputter.pause();
                    isSaved=true;
                    changed=false;
                    System.out.println("Saved guestInfor successfully!");
                    break;
                case 11:
                    if(changed && !isSaved){
                        boolean resp=tool.ConsoleInputter.getBoolean("Data changed. Do you want to save?");
                        if(resp){
                            guestList.saveToFile(GuestList.FILE_NAME);
                            System.out.println("Saved guestInfor successfully!");
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