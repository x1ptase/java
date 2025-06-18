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
    private static final String idPattern="^\\d{12}$";
    private static final String namePattern="^[a-zA-Z ]{2,25}$";
    private static final String phonePattern="^\\d{10}$"; // 10 digits only
    private static final String desiredIDPattern="^[a-zA-Z]\\d{3}$";
    public static final String FILE_NAME="src\\data\\guestInfor.dat";

    public void addGuest(){
        int age;
        Date doB;
        Date dateStart;
        ArrayList<String> nameOfCoTenant=new ArrayList<>();
        int rentalDate;
        String desiredRoomID;
        String idGuest;
        
        int pos;
        do{
            idGuest=ConsoleInputter.getStr("Enter guestID", idPattern, "ID is 12 digits");
            pos=this.indexOf(new Guest(idGuest));
            if(pos >= 0)
                System.out.println("ID is exist");
        } while(pos >= 0);
        
        String nameGuest=ConsoleInputter.getStr("Enter guestName", namePattern, "Name length have between 2 and 25");
        do{
            doB=ConsoleInputter.getDate("Enter Guest Birthdate(dd/MM/yyyy)", "dd/MM/yyyy");

            Calendar now=Calendar.getInstance();
            Calendar birth=Calendar.getInstance();
            birth.setTime(doB);
            age=now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            if(age < 16){
                System.out.println("Guest must be at least 16 years old.");
                return;
            }
        } while(age < 16);

        boolean gender=ConsoleInputter.getBoolean("Male/Female");
        
        String phoneGuest=ConsoleInputter.getStr("Enter Guest Phone", phonePattern, "Phone must have 10 digits");

        rentalDate=ConsoleInputter.getInt("Enter Rental Date", 1, Integer.MAX_VALUE);

        boolean dateValid=true;
        do{
            dateStart=ConsoleInputter.getDate("Enter Date Start(dd/MM/yyyy)", "dd/MM/yyyy");

            if(ConsoleInputter.isSameDay(dateStart, new Date())){
                dateValid=true;
                break;
            }
            dateValid=dateStart.after(new Date());
        } while(!dateValid);

        boolean isRen;
        do{
            desiredRoomID=ConsoleInputter.getStr("Enter Desired Room ID", desiredIDPattern, "RoomID must has a character next 3 digits");
            Room room=new Room(desiredRoomID);
            isRen=room.isRented(this, dateStart, rentalDate);
            if(isRen)
                System.out.println("Room is not vacant");
        } while(isRen);

        int numberCoTenant=ConsoleInputter.getInt("Enter number Co - Tenant", 0, Integer.MAX_VALUE);
        for(int i=0; i<numberCoTenant; i++){
            String nameCoTenant=ConsoleInputter.getStr(String.format("Enter Name Co-Tenant: %d", (i + 1)), namePattern, "Name length have between 2 and 25");
            nameOfCoTenant.add(nameCoTenant);
        }

        Guest newGuest=new Guest(idGuest, nameGuest, doB, gender, phoneGuest, desiredRoomID, rentalDate, dateStart, nameOfCoTenant);
        this.add(newGuest);
        RoomList rl=new RoomList();
        rl.readFromFile(RoomList.FILE_NAME);
        displayInfo(newGuest, rl.findRoom(desiredRoomID));
    }

    public void updateGuest(){
        String idCheck=ConsoleInputter.getStr("Enter guestID", idPattern, "ID  must has 12 degits");
        for(Guest g : this){
            if(g.getGuestID().equalsIgnoreCase(idCheck)){
                int rentalDate;
                rentalDate=ConsoleInputter.getInt("Enter Rental Date", 1, Integer.MAX_VALUE);
                g.setRentalDate(rentalDate);

                Date newStartDate;
                do{
                    newStartDate=ConsoleInputter.getDate("Enter new date", "dd/MM/yyyy");
                } while (newStartDate.before(g.getStartDate()));
                g.setStartDate(newStartDate);
                
                boolean isRen;
                String desiredRoomID;
                do{
                    desiredRoomID=ConsoleInputter.getStr("Enter Desired Room ID", desiredIDPattern, "RoomID must has a character next 3 digits");
                    Room room=new Room(desiredRoomID);
                    isRen=room.isRented(this, newStartDate, rentalDate);
                    if(isRen) 
                        System.out.println("Room is not vacant");
                } while(isRen);
                g.setDesiredRID(desiredRoomID);
                
                boolean isAdd;
                if(g.getCoTenant().isEmpty())
                    isAdd=true;
                else
                    isAdd=ConsoleInputter.getBoolean("Is add more Co-Tenant");
               
                if(isAdd){
                    int numberAdd;
                    numberAdd=ConsoleInputter.getInt("Number add", 0, Integer.MAX_VALUE);

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
                displayInfo(g, rl.findRoom(g.getDesiredRID()));
                return;
            }
        }
        System.out.println("No guest found with the requested ID!");
    }

    public void searchByID(){
        String idCheck=ConsoleInputter.getStr("Enter guestID", idPattern, "ID must has 12 digits");
        for(Guest thi : this){
            if(thi.getGuestID().equalsIgnoreCase(idCheck)){
                RoomList rl=new RoomList();
                rl.readFromFile(RoomList.FILE_NAME);
                displayInfo(thi, rl.findRoom(thi.getDesiredRID()));
            }
        }
    }

    public void deleteGuest(){
        String idCheck=ConsoleInputter.getStr("Enter guestID", idPattern, "ID must has 12 digits");
        int pos=this.indexOf(new Guest(idCheck));
        if(pos < 0){
            System.out.println("Guest is not exsited");
            return;
        }
        Guest gPos=this.get(pos);
        if(gPos.getStartDate().after(new Date())){
            this.remove(gPos);
            System.out.println("Delete successfully");
        } else
            System.out.println("Guest's reservation has not started yet. Cannot delete.");
    }

    public void displayInfo(Guest g, Room r){
        System.out.println("----------------------------------------------------------------");
        System.out.println("Guest information [National ID: " + g.getGuestID() + "]");
        System.out.println("----------------------------------------------------------------");
        System.out.println("\n===== GUEST INFORMATION =====\n");
        System.out.format("%-20s: %s\n", "Guest ID", g.getGuestID());
        System.out.format("%-20s: %s\n", "Guest Name", g.getGuestName().toUpperCase());
        System.out.format("%-20s: %s\n", "Birth Date", ConsoleInputter.dateStr(g.getDoB(), "dd/MM/yyyy"));
        System.out.format("%-20s: %s\n", "Gender", g.isGender() ? "Male" : "Female");
        System.out.format("%-20s: %s\n", "Phone", g.getPhone());

        System.out.println("\n===== RENTAL INFORMATION =====\n");
        System.out.format("%-20s: %s\n", "Room ID", g.getDesiredRID().toUpperCase());
        System.out.format("%-20s: %d days\n", "Rental Duration", g.getRentalDate());
        System.out.format("%-20s: %s\n", "Check-in Date", ConsoleInputter.dateStr(g.getStartDate(), "dd/MM/yyyy"));

        Calendar cal=Calendar.getInstance();
        cal.setTime(g.getStartDate());
        cal.add(Calendar.DATE, g.getRentalDate());
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
        System.out.format("%-20s:%s\n", "Furniture", r.getFunrnitureDescription());

        float totalCost=g.getRentalDate() * r.getDailyRate();
        System.out.format("\n%-20s: $%,f\n", "TOTAL COST", totalCost);
    }

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
                    guest.getPhone(),
                    guest.isGender() ? "Male" : "Female",
                    guest.getDesiredRID().toUpperCase(),
                    ConsoleInputter.dateStr(guest.getStartDate(), "dd/MM/yyyy"),
                    guest.getRentalDate());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

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