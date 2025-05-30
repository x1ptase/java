package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import tool.ConsoleInputter;

public class RoomList extends ArrayList<Room>{
    private ArrayList<Room> roomList=new ArrayList<>();
    public static final String FILE_NAME="src/data/Active_Room_List.txt";

    // FUNCTION 1: Import Room Data from Text File
    public void readFromFile(){
        roomList.clear();
        int successCount=0, failCount=0;
        try(BufferedReader br=new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line=br.readLine()) != null){
                String[] parts=line.split(";");
                if (parts.length != 6){
                    failCount++;
                    continue;
                }
                String roomID=parts[0].trim();
                String roomName=parts[1].trim();
                String roomType=parts[2].trim();
                String dailyRateStr=parts[3].trim();
                String capacityStr=parts[4].trim();
                String furniture=parts[5].trim();

                // validation
                if(isRoomExist(roomID)){
                    failCount++;
                    continue;
                }
                double dailyRate;
                int capacity;
                try{
                    dailyRate=Double.parseDouble(dailyRateStr);
                    capacity=Integer.parseInt(capacityStr);
                } catch(NumberFormatException e){
                    failCount++;
                    continue;
                }
                if(dailyRate <= 0 || capacity <= 0){
                    failCount++;
                    continue;
                }

                roomList.add(new Room(roomID, roomName, roomType, dailyRate, capacity, furniture));
                successCount++;
            }
            System.out.printf("%d rooms successfully loaded.\n%d entries failed.\n", successCount, failCount);
        } catch(IOException e){
            System.out.println("Cannot read data from Active_Room_List.txt. Please check it.");
            failCount++;
        }
    }

    // FUNCTION 2: Display Available Room List
    public void displayAllRooms(){
        if(roomList.isEmpty()){
            System.out.println("Room list is currently empty, not loaded yet.");
            return;
        }
        
        System.out.println("\nActive Room List");
        System.out.printf("%-8s | %-16s | %-10s | %-8s | %-8s | %s\n",
                "RoomID", "RoomName", "Type", "Rate", "Capacity", "Furniture");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Room room : roomList) {
            System.out.printf("%-8s | %-16s | %-10s | %-8.2f | %-8d | %s\n",
                    room.getRoomID(), room.getRoomName(), room.getRoomType(),
                    room.getDailyRate(), room.getCapacity(), room.getFurniture());
        }
    }

    // FUNCTION 7: List Vacant Rooms
    public void listVacantRooms(GuestList guestList){
        ArrayList<Room> vacantRooms=new ArrayList<>();
        for(Room room : roomList){
            if(guestList.findGuestByRoomID(room.getRoomID()) == null){
                vacantRooms.add(room);
            }
        }
        if(vacantRooms.isEmpty()){
            System.out.println("All rooms are currently rented out — no availability at the moment!");
            return;
        }
        System.out.println("\nAvailable Room List");
        System.out.printf("%-8s | %-16s | %-10s | %-8s | %-8s | %s\n",
                "RoomID", "RoomName", "Type", "Rate", "Capacity", "Furniture");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for(Room room : roomList){
            System.out.printf("%-8s | %-16s | %-10s | %-8.2f | %-8d | %s\n",
                    room.getRoomID(), room.getRoomName(), room.getRoomType(),
                    room.getDailyRate(), room.getCapacity(), room.getFurniture());
        }
    }

    // find room by ID
    public Room findRoomByID(String roomID){
        for(Room room : roomList){
            if(room.getRoomID().equalsIgnoreCase(roomID)){
                return room;
            }
        }
        return null;
    }

    // check if room exists
    private boolean isRoomExist(String roomID){
        return roomList.stream().anyMatch(room -> room.getRoomID().equalsIgnoreCase(roomID));
    }

    // FUNTION 9: Revenue Report by Room Type
    public void revenueReportByRoomType(GuestList guestList){
        String roomType=ConsoleInputter.getStr("Enter room type: ");
        double totalRevenue=0;
        ArrayList<Guest> matchingGuests=new ArrayList<>();
        for(Guest guest : guestList.getGuestList()){
            Room room=findRoomByID(guest.getRoomID());
            if (room != null && room.getRoomType().equalsIgnoreCase(roomType)) {
                matchingGuests.add(guest);
                totalRevenue += room.getDailyRate() * guest.getRentalDays();
            }
        }
        if (matchingGuests.isEmpty()) {
            System.out.println("Invalid room type or no data found!");
            return;
        }
        System.out.println("\nRevenue Report by Room Type");
        System.out.printf("%-10s | %s\n", "RoomType", "Amount");
        System.out.println("--------------------------------");
        System.out.printf("%-10s | %.2f\n", roomType, totalRevenue);
    }
}