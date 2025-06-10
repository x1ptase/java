package core;

import tool.ConsoleInputter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GuestList {
    private ArrayList<Guest> guestList=new ArrayList<>();
    private static final String FILE_NAME="src/data/guestInfo.dat";
    private static final String nationalIDPattern="^\\d{12}$";
    private static final String namePattern = "^.{2,25}$";
    private static final String phonePattern = "^\\d{9}$|^\\d{11}$";
    private static final String roomIDPattern = "^[A-Za-z]\\d{0,4}$";
    private boolean existed=true;

    // FUNCTION 3: Enter Guest Information
    public void addGuest(RoomList roomList){
        System.out.println("\n===== ADD intelligent New Guest =====");
        String nationalID=ConsoleInputter.getStr("National ID (12 digits)", nationalIDPattern, "Must be 12 digits!");
        if(isGuestExist(nationalID)){
            System.out.println("National ID already exists!");
            return;
        }
        String fullName=ConsoleInputter.getStr("Full name (2-25 chars)", namePattern, "Must be 2-25 chars starting with a letter!");
        Date birthdate=ConsoleInputter.getDate("Birthdate (dd/MM/yyyy): ", "dd/MM/yyyy");
        String gender=ConsoleInputter.getStr("Gender (M/F)", "[MF]", "Must be M or F!");
        String phone=ConsoleInputter.getStr("Phone number: ", phonePattern, "9 or 11 numbers!");
        String roomID=ConsoleInputter.getStr("Desired Room ID", roomIDPattern, "Up to 5 chars, start with letter!");
        Room room=roomList.findRoomByID(roomID);
        
        if(room == null){
            System.out.println("Room ID does not exist!");
            return;
        }
        
        if(findGuestByRoomID(roomID) != null){
            System.out.println("Room is already occupied!");
            return;
        }
        
        int rentalDays=ConsoleInputter.getInt("Number of rental days", 1, Integer.MAX_VALUE);
        Date startDate=ConsoleInputter.getDate("Start date (dd/MM/yyyy): ", "dd/MM/yyyy");
        if(!startDate.after(new Date())){
            System.out.println("Start date must be in the future!");
            return;
        }
        String coTenant=ConsoleInputter.getStr("Co-tenant name (optional, press Enter to skip): ");

        Guest guest=new Guest(nationalID, fullName, birthdate, gender, phone, roomID, rentalDays, startDate, coTenant);
        guestList.add(guest);
        System.out.printf("Guest registered successfully for room %s\nRental from %s for %d days\n",
                roomID, new SimpleDateFormat("dd/MM/yyyy").format(startDate), rentalDays);
        existed=false;
    }

    // FUNCTION 4: Update Guest Stay Information
    public void updateGuest(){
        System.out.println("\n===== UPDATE GUEST INFORMATION =====");
        String guestID=ConsoleInputter.getStr("National ID (12 digits)", nationalIDPattern, "Must be 12 digits!");
        Guest guest=findGuestByNationalID(guestID);
        if(guest == null){
            System.out.println("No guest found with the requested ID!");
            return;
        }
        System.out.println("Current Guest Information: ");
        System.out.println(guest);
        String newPhone=ConsoleInputter.getStr("Enter new phone number (Press Enter to keep current): ");
        if(!newPhone.isEmpty() && ConsoleInputter.isValid(newPhone, phonePattern)){
            guest.setPhoneNumber(newPhone);
        }
        int rentalDays=ConsoleInputter.getInt("New rental days (Enter 0 to keep)", 0, Integer.MAX_VALUE);
        if(rentalDays > 0) guest.setRentalDays(rentalDays);
        String coTenant=ConsoleInputter.getStr("New co-tenant name (Enter to keep, 'none' to clear): ");
        if(!coTenant.isEmpty()) guest.setCoTenant(coTenant.equals("none") ? "" : coTenant);
        System.out.println("Guest information updated for ID: " + guestID);
        existed = false;
    }

    // FUNCTION 5: Search Guest by National ID
    public void searchGuestByNationalID(RoomList roomList){
        System.out.println("\n===== SEARCH GUEST BY NATIONAL ID =====");
        String guestID=ConsoleInputter.getStr("National ID (12 digits)", nationalIDPattern, "Must be 12 digits!");
        Guest guest=findGuestByNationalID(guestID);
        if(guest == null){
            System.out.println("No guest found with the requested ID!");
            return;
        }
        Room room=roomList.findRoomByID(guest.getRoomID());
        System.out.println("Guest information [National ID: " + guestID + "]");
        System.out.println(guest);
        if(room != null){
            System.out.println("Room information:");
            System.out.println(room);
        }
    }

    // FUNCTION 6: Delete Guest Reservation Before Arrival
    public void deleteGuestReservation(RoomList roomList) {
        System.out.println("\n===== DELETE GUEST RESERVATION =====");
        String guestID=ConsoleInputter.getStr("National ID (12 digits)", nationalIDPattern, "Must be 12 digits!");
        Guest guest=findGuestByNationalID(guestID);
        if(guest == null){
            System.out.println("Booking details for ID " + guestID + " could not be found.");
            return;
        }
        if(!guest.getStartDate().after(new Date())){
            System.out.println("The room booking for this guest cannot be cancelled!");
            return;
        }
        System.out.println("Guest information [National ID: " + guestID + "]");
        System.out.println(guest);
        Room room=roomList.findRoomByID(guest.getRoomID());
        if(room != null){
            System.out.println("Room information:");
            System.out.println(room);
        }
        boolean confirm=ConsoleInputter.getBoolean("Do you really want to cancel this guest's room booking?");
        if(confirm){
            guestList.remove(guest);
            System.out.println("The booking associated with ID " + guestID + " has been successfully canceled.");
            existed=false;
        }
    }

    // FUNCTION 8: Monthly Revenue Report
    public void monthlyRevenueReport(RoomList roomList){
        System.out.println("\n===== MONTHLY REVENUE REPORT =====");
        String monthYear=ConsoleInputter.getStr("Enter month (MM/YYYY)", "\\d{2}/\\d{4}", "Must be MM/YYYY!");
        String[] parts=monthYear.split("/");
        int month=Integer.parseInt(parts[0]);
        int year=Integer.parseInt(parts[1]);
        if(month < 1 || month > 12){
            System.out.println("Invalid month!");
            return;
        }

        ArrayList<Guest> filteredGuests=new ArrayList<>();
        for(Guest guest : guestList){
            Calendar cal=Calendar.getInstance();
            cal.setTime(guest.getStartDate());
            if(cal.get(Calendar.MONTH) + 1 == month && cal.get(Calendar.YEAR) == year) {
                filteredGuests.add(guest);
            }
        }

        if(filteredGuests.isEmpty()){
            System.out.println("There is no data on guests who have rented rooms.");
            return;
        }

        System.out.printf("\nMonthly Revenue Report - '%s'\n", monthYear);
        System.out.printf("%-8s | %-15s | %-10s | %-10s | %s\n",
                "RoomID", "Room Name", "Room Type", "DailyRate", "Amount");
        System.out.println("------------------------------");
        for(Guest guest : filteredGuests){
            Room room=roomList.findRoomByID(guest.getRoomID());
            if(room != null){
                double amount=room.getDailyRate()*guest.getRentalDays();
                System.out.printf("%-8s | %-15s | %-10s | %-10.2f | %.2f\n",
                        room.getRoomID(), room.getRoomName(), room.getRoomType(),
                        room.getDailyRate(), amount);
            }
        }
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

    public Guest findGuestByNationalID(String nationalID){
        for(Guest guest : guestList){
            if(guest.getGuestID().equals(nationalID)){
                return guest;
            }
        }
        return null;
    }

    public Guest findGuestByRoomID(String roomID){
        for(Guest guest : guestList){
            if(guest.getRoomID().equalsIgnoreCase(roomID)){
                return guest;
            }
        }
        return null;
    }

    private boolean isGuestExist(String guestID){
        return guestList.stream().anyMatch(guest -> guest.getGuestID().equals(guestID));
    }

    public boolean isSaved(){
        return existed;
    }

    public ArrayList<Guest> getGuestList(){
        return guestList;
    }
}