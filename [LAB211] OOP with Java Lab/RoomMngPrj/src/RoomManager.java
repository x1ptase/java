
import tool.ConsoleInputter;
import core.*;

public class RoomManager {

    public static void main(String[] args) {
        RoomList rList = new RoomList();
        GuestList gList = new GuestList();

        rList.readFromFile(RoomList.FILE_NAME);
        gList.readFromFile(GuestList.fname);
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
                    rList.displayAll();
                    ConsoleInputter.pause();
                    break;
                case 2:
                    gList.addGuest();//xong
                    ConsoleInputter.pause();
                    break;
                case 3:
                    gList.updateGuest();//Xong
                    ConsoleInputter.pause();
                    break;
                case 4://xong
                    gList.seacrchByID();
                    ConsoleInputter.pause();
                    break;
                case 6://xong
                    gList.displayAll();
                    ConsoleInputter.pause();
                    break;
                case 5://xong
                    gList.deleteGuest();
                    ConsoleInputter.pause();
                    break;
                case 7://xong
                    rList.vacantRoomList(gList).displayAll();
                    ConsoleInputter.pause();
                    break;
                case 8://xong
                    rList.monthlyReport(gList);
                    ConsoleInputter.pause();
                    break;
                case 9://xong
                    rList.revenueReport(gList);
                    ConsoleInputter.pause();
                    break;
                case 10://xong
                    gList.saveFile(GuestList.fname);
                    ConsoleInputter.pause();
                    isSaved = true;
                    break;
                case 11:
                    if (changed && !isSaved) {
                        boolean resp = tool.ConsoleInputter.getBoolean("Data changed. Save or not");
                        if (resp) {
                            gList.saveFile(GuestList.fname);
                        }
                    }
            }

        } while (choice != 11);
    }
}