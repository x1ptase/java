package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import tool.ConsoleInputter;

public class GuestList extends ArrayList<Guest>{
    private static final String IDPattern="^\\d{12}$";
    private static final String namePattern="^[a-zA-Z ]{2,25}$";
    private static final String phonePattern="^\\d{10}$"; // only 10 digits
    private static final String desiredRoomIDPattern="^[a-zA-Z]\\d{3}$";
    public static final String FILE_NAME="src\\data\\guestInfor.dat";
    
    public void displayInfo(Guest g, Room r){
        System.out.println("----------------------------------------------------------------");
        System.out.println("Guest information [National ID: " + g.getGuestID() + "]");
        System.out.println("----------------------------------------------------------------");
        System.out.println("\n===== GUEST INFORMATION =====\n");
        System.out.format("%-20s: %s\n", "Guest ID", g.getGuestID());
        System.out.format("%-20s: %s\n", "Guest Name", g.getGuestName().toUpperCase());
        System.out.format("%-20s: %s\n", "Birth Date", ConsoleInputter.dateStr(g.getDoB(), "dd/MM/yyyy"));
        System.out.format("%-20s: %s\n", "Gender", g.isGender() ? "Male" : "Female");
        System.out.format("%-20s: %s\n", "Phone", g.getPhoneNumber());

        System.out.println("\n===== RENTAL INFORMATION =====\n");
        System.out.format("%-20s: %s\n", "Room ID", g.getDesiredRoomID().toUpperCase());
        System.out.format("%-20s: %d days\n", "Rental Duration", g.getRentalDays());
        System.out.format("%-20s: %s\n", "Check-in Date", ConsoleInputter.dateStr(g.getStartDate(), "dd/MM/yyyy"));

        Calendar cal=Calendar.getInstance();
        cal.setTime(g.getStartDate());
        cal.add(Calendar.DATE, g.getRentalDays());
        Date checkoutDate=cal.getTime();
        System.out.format("%-20s: %s\n", "Check out Date", ConsoleInputter.dateStr(checkoutDate, "dd/MM/yyyy"));

        if(g.getCoTenant() != null && !g.getCoTenant().isEmpty()){
            System.out.println("\n===== CO-TENANTS =====\n");
            for(int i=0; i<g.getCoTenant().size(); i++){
                System.out.format("%-20s: %s\n", "Co-tenant " + (i + 1), g.getCoTenant().get(i));
            }
        }

        System.out.println("\n===== ROOM INFORMATION =====\n");
        System.out.format("%-20s:%s\n", "Room ID", r.getRoomID());
        System.out.format("%-20s:%s\n", "Room Name", r.getRoomName());
        System.out.format("%-20s:%s\n", "Room Type", r.getRoomType());
        System.out.format("%-20s:%,f\n", "Daily Rate", r.getDailyRate());
        System.out.format("%-20s:%,f\n", "Capacity", r.getCapacity());
        System.out.format("%-20s:%s\n", "Furniture", r.getFurnitureDescription());

        float totalCost=g.getRentalDays() * r.getDailyRate();
        System.out.format("\n%-20s: $%,f\n", "TOTAL COST", totalCost);
    }
    
    // Function 2: Enter Guest Information     
    public void addGuest(){
        // ID
        String guestID;
        int pos;
        do{
            guestID=ConsoleInputter.getStr("Enter guestID", IDPattern, "ID must be 12 digits!");
            pos=this.indexOf(new Guest(guestID)); // kiem tra ID da ton tai chua
            if(pos >= 0) System.out.println("ID already exists!");
        } while(pos >= 0);
        
        // Name
        String guestName=ConsoleInputter.getStr("Enter guestName", namePattern, "Name length from 2 to 25 characters!");
        
        // doB
        Date doB; int age;
        do{
            doB=ConsoleInputter.getDate("Enter guestBirthdate(dd/MM/yyy)", "dd/MM/yyyy");
            
            /*  getInstance()
            * goi truc tiep Calendar ma khong can tao doi tuong Calendar truoc
            */ 
            Calendar now=Calendar.getInstance();
            Calendar birth=Calendar.getInstance();
            birth.setTime(doB);
            
            age=now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            // kiem tra tuoi chinh xac hon
            if(now.get(Calendar.MONTH) < birth.get(Calendar.MONTH) || 
                    (now.get(Calendar.MONTH) == birth.get(Calendar.MONTH) && 
                    now.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH))){
                age--;
            }
            if(age < 16) 
                System.out.println("Guest must be at least 16 years old!");
        } while(age < 16);
        
        // gender
        boolean gender=ConsoleInputter.getBoolean("Male OR Female");
        
        // phoneNumber
        String guestPhone=ConsoleInputter.getStr("Enter gustPhone", phonePattern, "Phone number must be 10 digits!");
        
        // rentalDays
        int rentalDays;
        rentalDays=ConsoleInputter.getInt("Enter rentalDays", 1, Integer.MAX_VALUE);
        
        // dateStart
        Date dateStart;
        boolean dateValid=true;
        do{
            dateStart=ConsoleInputter.getDate("Enter dateStart(dd/MM/yyyy)", "dd/MM/yyyy");
            
            // kiem tra co trung voi ngay hien tai khong
            if(ConsoleInputter.isSameDay(dateStart, new Date())){
                dateValid=true;
                break;
            }
            
            // kiem tra ngay tuong lai
            dateValid=dateStart.after(new Date());
        } while(!dateValid);
        
        // kiem tra phong trong
        String desiredRoomID;
        boolean isRental;
        do{
            desiredRoomID=ConsoleInputter.getStr("Enter desiredRoomID", desiredRoomIDPattern,
                    "Room ID must have 1 character followed by 3 numbers (Rxxx)!");
            Room room=new Room(desiredRoomID);
            isRental=room.isRented(this, dateStart, rentalDays);
            
            if(isRental) System.out.println("Room is not vacant!"); // phong dang duoc thue
        } while(isRental);
        
        // Co-Tenant
        ArrayList<String> nameOfCoTenant=new ArrayList<>();
        int numberCoTenant=ConsoleInputter.getInt("Enter number Co - Tenant", 0, Integer.MAX_VALUE);
        
        for(int i=0; i<numberCoTenant; i++){
            String nameCoTenant=ConsoleInputter.getStr(String.format("Enter Name Co-Tenant: %d", (i + 1)), namePattern, "Name length from 2 to 25 characters!");
            nameOfCoTenant.add(nameCoTenant);
        }

        Guest newGuest=new Guest(guestID, guestName, doB, gender, guestPhone, desiredRoomID, rentalDays, dateStart, nameOfCoTenant);
        this.add(newGuest);
        RoomList rl=new RoomList();
        rl.readFromFile(RoomList.FILE_NAME);
        displayInfo(newGuest, rl.findRoom(desiredRoomID));
        
    } // addGuest()
    
    // Function 4: Update Guest Stay Information
    
    // Function 5: Search Guest by National ID
    // Function 6: Delete Guest Reservation Before Arrival
    // Function 10: Save Guest Information
            
} // GuestList
