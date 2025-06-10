import tool.ConsoleInputter;
import core.*;

public class RoomOrderManager {
    public static void main(String[] args) {
        RoomList roomList=new RoomList();
        GuestList guestList=new GuestList();

        roomList.readFile(RoomList.fName);
        guestList.readFromFile();
        
        int choice;
        boolean changed = false;
        boolean isSaved = false;
        do {
            choice = ConsoleInputter.intMenu(
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
            if (choice >= 2 && choice <= 5) {
                changed = true;
            }
            switch (choice) {
                case 1://Ok
                    roomList.displayAll();
                    ConsoleInputter.pause();
                    break;
                case 2:
                    guestList.addGuest();//xong
                    ConsoleInputter.pause();
                    break;
                case 3:
                    guestList.updateGuest();//Xong
                    ConsoleInputter.pause();
                    break;
                case 4://xong
                    guestList.seacrchByID();
                    ConsoleInputter.pause();
                    break;
                case 6://xong
                    guestList.displayAll();
                    ConsoleInputter.pause();
                    break;
                case 5://xong
                    guestList.deleteGuest();
                    ConsoleInputter.pause();
                    break;
                case 7://xong
                    roomList.vacantRoomList(guestList).displayAll();
                    ConsoleInputter.pause();
                    break;
                case 8://xong
                    roomList.monthlyReport(guestList);
                    ConsoleInputter.pause();
                    break;
                case 9://xong
                    roomList.revenueReport(guestList);
                    ConsoleInputter.pause();
                    break;
                case 10://xong
                    guestList.saveToFile();
                    ConsoleInputter.pause();
                    isSaved = true;
                    break;
                case 11:
                    if (changed && !isSaved) {
                        boolean resp = tool.ConsoleInputter.getBoolean("Data changed. Save or not");
                        if (resp) {
                            guestList.saveToFile();
                        }
                    }
            }

        } while (choice != 11);
    }
}
