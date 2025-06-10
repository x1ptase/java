package core;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import tool.ConsoleInputter;

public class GuestList extends ArrayList<Guest> {
    private ArrayList<Guest> guestList=new ArrayList<>();
    public static final String FILE_NAME = "src\\data\\guestInfor.dat";
    private static final String nationalIDPattern = "^\\d{12}$";
    private static final String namePattern = "^[a-zA-Z ]{2,25}$";
    private static final String phonePattern = "^\\d{10}$"; 
    private static final String roomIDPattern = "^[a-zA-Z]\\d{3}$";
    private boolean existed=true;

    public void addGuest(){
        int age;
        Date birthDate;
        Date dateStart;
        ArrayList<String> nameOfCoTenant = new ArrayList<>();
        int rentalDate;
        String roomID;
        String idGuest;
        
        int pos;
        do {
            idGuest = ConsoleInputter.getStr("Enter Guest ID", nationalIDPattern, "Id is 12 digits");
            pos = this.indexOf(new Guest(idGuest));
            if (pos >= 0) {
                System.out.println("Id is exist");
            }
        } while (pos >= 0);
        String nameGuest = ConsoleInputter.getStr("Enter Guest Name", namePattern, "Name length have between 2 and 25");
        do {
            birthDate = ConsoleInputter.getDate("Enter Guest Birthdate(dd/MM/yyyy)", "dd/MM/yyyy");

            Calendar now = Calendar.getInstance();
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthDate);
            age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            if (age < 16) {
                System.out.println("Guest must be at least 16 years old.");
                return;
            }
        } while (age < 16);

        boolean gender = ConsoleInputter.getBoolean("Is man?");
        String phoneGuest = ConsoleInputter.getStr("Enter Guest Phone", phonePattern, "Phone have 10 digits");

        rentalDate = ConsoleInputter.getInt("Enter Rental Date", 1, Integer.MAX_VALUE);

        boolean dateValid = true;
        do {
            dateStart = ConsoleInputter.getDate("Enter Date Start(dd/MM/yyyy)", "dd/MM/yyyy");

            if (ConsoleInputter.isSameDay(dateStart, new Date())) {
                dateValid = true;
                break;
            }
            dateValid = dateStart.after(new Date());
        } while (!dateValid);

        boolean isRen;
        do {
            roomID = ConsoleInputter.getStr("Enter Desired Room ID", roomIDPattern, "Room id have a character next 3 digits");
            Room room = new Room(roomID);
            isRen = room.isRented(this, dateStart, rentalDate);
            if (isRen) {
                System.out.println("Room is not vacant");
            }
        } while (isRen);

        int numberCoTenant = ConsoleInputter.getInt("Enter number Co - Tenant", 0, Integer.MAX_VALUE);
        for (int i = 0; i < numberCoTenant; i++) {
            String nameCoTenant = ConsoleInputter.getStr(String.format("Enter Name Co-Tenant: %d", (i + 1)), namePattern, "Name length have between 2 and 25");
            nameOfCoTenant.add(nameCoTenant);
        }

        Guest newGuest = new Guest(idGuest, nameGuest, birthDate, gender, phoneGuest, roomID, rentalDate, dateStart, nameOfCoTenant);
        this.add(newGuest);
        RoomList rl = new RoomList();
        rl.readFile(RoomList.fName);
        displayInfo(newGuest, rl.findRoom(roomID));
    }

    public void updateGuest() {
        String idCheck = ConsoleInputter.getStr("Enter Guest ID", nationalIDPattern, "Id has 12 degits");
        for (Guest g : this) {
            if (g.getID().equalsIgnoreCase(idCheck)) {
                int rentalDate;
                rentalDate = ConsoleInputter.getInt("Enter Rental Date", 1, Integer.MAX_VALUE);
                g.setRentalDate(rentalDate);

                Date newStartDate;
                do {
                    newStartDate = ConsoleInputter.getDate("Enter new date", "dd/MM/yyyy");
                } while (newStartDate.before(g.getStartDate()));

                g.setStartDate(newStartDate);
                boolean isRen;
                String desiredRoomID;
                do {
                    desiredRoomID = ConsoleInputter.getStr("Enter Desired Room ID", roomIDPattern, "Room id have a character next 3 digits");
                    Room room = new Room(desiredRoomID);
                    isRen = room.isRented(this, newStartDate, rentalDate);
                    if (isRen) {
                        System.out.println("Room is not vacant");
                    }
                } while (isRen);
                g.setDesiredRID(desiredRoomID);
                boolean isAdd;
                if (g.getCoTenant().isEmpty()) {
                    isAdd = true;
                } else {
                    isAdd = ConsoleInputter.getBoolean("Is add more Co-Tenant");
                }
                if (isAdd) {
                    int numberAdd;
                    numberAdd = ConsoleInputter.getInt("Number add", 0, Integer.MAX_VALUE);

                    for (int i = 0; i < numberAdd; i++) {
                        String nameCoTenant = ConsoleInputter.getStr("Enter Name Co-Tenant " + (i + 1));
                        g.getCoTenant().add(nameCoTenant);
                    }
                } else {
                    if (g.getCoTenant() == null) {
                        System.out.println("No have Co_Tenant to remove");
                        return;
                    }
                    boolean isStop = false;
                    while (!isStop) {
                        int removePos = ConsoleInputter.intMenu(g.getCoTenant().toArray());
                        g.getCoTenant().remove(removePos - 1);
                        isStop = ConsoleInputter.getBoolean("Is stop?");
                    }
                }
                RoomList rl = new RoomList();
                rl.readFile(RoomList.fName);
                displayInfo(g, rl.findRoom(g.getDesiredRID()));
                return;
            }
        }
        System.out.println("No guest found with the requested ID!");
    }

    public void seacrchByID() {
        String idCheck = ConsoleInputter.getStr("Enter Guest ID", nationalIDPattern, "Id is 12 digits");
        for (Guest thi : this) {
            if (thi.getID().equalsIgnoreCase(idCheck)) {

                RoomList rl = new RoomList();
                rl.readFile(RoomList.fName);
                displayInfo(thi, rl.findRoom(thi.getDesiredRID()));
            }
        }
    }

    public void deleteGuest() {
        String idCheck = ConsoleInputter.getStr("Enter Guest ID", nationalIDPattern, "Id is 12 digits");
        int pos = this.indexOf(new Guest(idCheck));
        if (pos < 0) {
            System.out.println("Guest is not exsited");
            return;
        }
        Guest gPos = this.get(pos);
        if (gPos.getStartDate().after(new Date())) {
            this.remove(gPos);
            System.out.println("delete successfull");
        } else {
            System.out.println("Guest's reservation has not started yet. Cannot delete.");
        }
    }

    public void displayInfo(Guest g, Room r) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Guest information [National ID: " + g.getID() + "]");
        System.out.println("----------------------------------------------------------------");
        System.out.println("\n===== GUEST INFORMATION =====\n");
        System.out.format("%-20s: %s\n", "Guest ID", g.getID());
        System.out.format("%-20s: %s\n", "Guest Name", g.getName().toUpperCase());
        System.out.format("%-20s: %s\n", "Birth Date", ConsoleInputter.dateStr(g.getBirthDate(), "dd/MM/yyyy"));
        System.out.format("%-20s: %s\n", "Gender", g.isGender() ? "Male" : "Female");
        System.out.format("%-20s: %s\n", "Phone", g.getPhone());

        System.out.println("\n===== RENTAL INFORMATION =====\n");
        System.out.format("%-20s: %s\n", "Room ID", g.getDesiredRID().toUpperCase());
        System.out.format("%-20s: %d days\n", "Rental Duration", g.getRentalDate());
        System.out.format("%-20s: %s\n", "Check-in Date", ConsoleInputter.dateStr(g.getStartDate(), "dd/MM/yyyy"));

        Calendar cal = Calendar.getInstance();
        cal.setTime(g.getStartDate());
        cal.add(Calendar.DATE, g.getRentalDate());
        Date checkoutDate = cal.getTime();
        System.out.format("%-20s: %s\n", "Check-out Date", ConsoleInputter.dateStr(checkoutDate, "dd/MM/yyyy"));

        if (g.getCoTenant() != null && !g.getCoTenant().isEmpty()) {
            System.out.println("\n===== CO-TENANTS =====\n");
            for (int i = 0; i < g.getCoTenant().size(); i++) {
                System.out.format("%-20s: %s\n", "Co-tenant " + (i + 1), g.getCoTenant().get(i));
            }
        }

        System.out.println("\n===== ROOM INFORMATION =====\n");
        System.out.format("%-20s:%s\n", "Room ID", r.getRoomID());
        System.out.format("%-20s:%s\n", "Room Name", r.getRoomName());
        System.out.format("%-20s:%s\n", "Room Type", r.getRoomType());
        System.out.format("%-20s:%,f\n", "Daily Rate", r.getDailyRate());
        System.out.format("%-20s:%,f\n", "Capacity", r.getCapacity());
        System.out.format("%-20s:%s\n", "Furniture", r.getFunrnitureDescription());

        float totalCost = g.getRentalDate() * r.getDailyRate();
        System.out.format("\n%-20s: $%,f\n", "TOTAL COST", totalCost);
    }

    public void displayAll() {
        if (this.isEmpty()) {
            System.out.println("No guests in the list.");
            return;
        }

        System.out.println("\n==================== ALL GUESTS LIST ====================");
        System.out.printf("%-15s | %-20s | %-12s | %-8s | %-12s | %-20s | %-8s\n",
                "Guest ID", "Name", "Phone", "Gender", "Room ID", "Start Day", "Day");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        for (Guest guest : this) {
            System.out.printf("%-15s | %-20s | %-12s | %-8s | %-12s | %-20s | %-8d\n",
                    guest.getID(),
                    guest.getName().toUpperCase(),
                    guest.getPhone(),
                    guest.isGender() ? "Male" : "Female",
                    guest.getDesiredRID().toUpperCase(),
                    ConsoleInputter.dateStr(guest.getStartDate(), "dd/MM/yyyy"),
                    guest.getRentalDate());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    // FUNCTION 10: Save Guest Information
    public void saveToFile(){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(guestList);
            System.out.println("Guest information has been successfully saved to \"" + FILE_NAME + "\".");
        } catch(IOException e){
            System.out.println("Error saving guest information: " + e.getMessage());
        }
        existed=true;
    }

    public void readFromFile(){
        guestList.clear();
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FILE_NAME))){
            guestList=(ArrayList<Guest>)ois.readObject();
            System.out.println("Guest data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing guest data found.");
        }
    }
}
