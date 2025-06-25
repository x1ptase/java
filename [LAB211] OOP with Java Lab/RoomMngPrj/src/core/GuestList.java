package core;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import tool.ConsoleInputter;

public class GuestList extends ArrayList<Guest>{
    private static final String IDPattern="^\\d{12}$";
    private static final String namePattern="^[a-zA-Z ]{2,25}$";
    private static final String phonePattern="^\\d{10}$"; // 10 digits only
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

    // Function 3
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
     
    // Function 4
    public void updateGuest(){
        String IDCheck=ConsoleInputter.getStr("Enter guestID", IDPattern, "ID  must has 12 degits");
        for(Guest g : this){
            if(g.getGuestID().equalsIgnoreCase(IDCheck)){
                //  updateID
                int rentalDate;
                rentalDate=ConsoleInputter.getInt("Enter Rental Date", 1, Integer.MAX_VALUE);
                g.setRentalDays(rentalDate);
                
                // updateDate
                Date newStartDate;
                do{
                    newStartDate=ConsoleInputter.getDate("Enter new date", "dd/MM/yyyy");
                } while(newStartDate.before(g.getStartDate()));
                g.setStartDate(newStartDate);
                
                // updateRoom
                boolean isRen;
                String desiredRoomID;
                do{
                    desiredRoomID=ConsoleInputter.getStr("Enter Desired Room ID", desiredRoomIDPattern, "RoomID must has a character next 3 digits");
                    Room room=new Room(desiredRoomID);
                    isRen=room.isRented(this, newStartDate, rentalDate);
                    if(isRen) 
                        System.out.println("Room is not vacant");
                } while(isRen);
                g.setDesiredRoomID(desiredRoomID);
                
                // update coTenant
                boolean isAdd;
                if(g.getCoTenant().isEmpty())
                    isAdd=true;
                else
                    isAdd=ConsoleInputter.getBoolean("Is add more Co-Tenant?");
               
                if(isAdd){
                    int numberAdd;
                    numberAdd=ConsoleInputter.getInt("Number add Co-Tenant", 0, Integer.MAX_VALUE);

                    for(int i=0; i<numberAdd; i++){
                        String nameCoTenant=ConsoleInputter.getStr("Enter Name Co-Tenant " + (i + 1));
                        g.getCoTenant().add(nameCoTenant);
                    }
                } else{
                    if(g.getCoTenant() == null){
                        System.out.println("Not have Co-Tenant to remove");
                        return;
                    }
                    boolean isStop=false;
                    while(!isStop){
                        int removePos=ConsoleInputter.intMenu(g.getCoTenant().toArray());
                        g.getCoTenant().remove(removePos - 1);
                        isStop=ConsoleInputter.getBoolean("Do you want to stop?");
                    }
                }
                RoomList rl=new RoomList();
                rl.readFromFile(RoomList.FILE_NAME);
                displayInfo(g, rl.findRoom(g.getDesiredRoomID()));
                return;
            }
        }
        System.out.println("No guest found with the requested ID!");
    }

    // Function 5
    public void searchByID(){
        String idCheck=ConsoleInputter.getStr("Enter guestID", IDPattern, "ID must has 12 digits");
        for(Guest thi : this){
            if(thi.getGuestID().equalsIgnoreCase(idCheck)){
                RoomList rl=new RoomList();
                rl.readFromFile(RoomList.FILE_NAME);
                displayInfo(thi, rl.findRoom(thi.getDesiredRoomID()));
            }
        }
    }

    // Function 6
    public void deleteGuest(){
        String idCheck=ConsoleInputter.getStr("Enter guestID", IDPattern, "ID must has 12 digits");
        int pos=this.indexOf(new Guest(idCheck));
        if(pos < 0){
            System.out.println("Guest is not exsited");
            return;
        }
        Guest gPos=this.get(pos);
        // chi xoa duoc if startDate o tuong lai (chua nhan phong)
        if(gPos.getStartDate().after(new Date())){
            this.remove(gPos);
            System.out.println("Delete successfully");
        } else
            System.out.println("Guest's reservation has not started yet. Cannot delete.");
    }

    // Function 7 (Them vao cho de theo doi)
    public void displayAll(){
        if(this.isEmpty()){
            System.out.println("No guests in the list.");
            return;
        }

        System.out.println("\n==================== ALL GUESTS LIST ====================");
        System.out.printf("%-15s | %-20s | %-12s | %-8s | %-12s | %-20s | %-8s\n",
                "Guest ID", "Name", "Phone", "Gender", "Room ID", "Start Day", "Day");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        for(Guest guest : this){
            System.out.printf("%-15s | %-20s | %-12s | %-8s | %-12s | %-20s | %-8d\n",
                    guest.getGuestID(),
                    guest.getGuestName().toUpperCase(),
                    guest.getPhoneNumber(),
                    guest.isGender() ? "Male" : "Female",
                    guest.getDesiredRoomID().toUpperCase(),
                    ConsoleInputter.dateStr(guest.getStartDate(), "dd/MM/yyyy"),
                    guest.getRentalDays());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    // Function 11
    public void saveToFile(String fName){
        if(this.isEmpty()){
            System.out.println("List is empty!");
            return;
        }
        try(ObjectOutputStream fo=new ObjectOutputStream(new FileOutputStream(fName))){
            for(Guest cust : this){
                fo.writeObject(cust);
            }
            System.out.println("Saved successfully to file.");
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void readFromFile(String fName){
        this.clear();
        try{
            File f=new File(fName);
            if(!f.exists()){
                System.out.println("File does not exist.");
                return;
            }
            try(ObjectInputStream fi=new ObjectInputStream(new FileInputStream(f))){
                while(true){
                    try{
                        Guest cust=(Guest)fi.readObject();
                        this.add(cust);
                    } catch(EOFException e){
                        break;
                    }
                }
                System.out.println("Read file is successfully!");
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}