package core;

import java.io.*;
import java.util.ArrayList;

public class RoomList extends ArrayList<Room> {
    public static final String FILE_NAME="src\\data\\Active_Room_List.txt";
    
    // kiem tra roomID da ton tai trong ds phong chua
    private boolean containsRoomID(String roomID){
        for(Room r : this){
            if (r.getRoomID().equals(roomID))
                return true;
        }
        return false;
    }
    // Function 1: Import Room Data from Text File
    public void readFromFile(String fName){
        try{
            FileReader fr=new FileReader(fName);
            BufferedReader bf=new BufferedReader(fr);
            String line;
            while((line = bf.readLine()) != null){
                String parts[]=line.split(";");
                if(parts.length == 6){
                    String roomID=parts[0];
                    if(!containsRoomID(roomID)){

                        String name=parts[1];
                        String type=parts[2];
                        float dailyRate=Float.parseFloat(parts[3]);
                        float capacity=Float.parseFloat(parts[4]);
                        String description=parts[5];
                        this.add(new Room(roomID, name, type, dailyRate, capacity, description));
                    }
                }
            }
            fr.close();
            bf.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    // Function 2: Display Available Room List 
    public void displayAll() {
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-6s | %-20s | %-9s | %-6s | %-9s | %-30s\n",
                "RoomID", "RoomName", "Type", "Rate", "Capacity", "Furniture");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (Room room : this) {
            System.out.format("%-6s | %-20s | %-9s | %-6.1f | %-9.2f | %-30s\n",
                    room.getRoomID(),
                    room.getRoomName(),
                    room.getRoomType(),
                    (float)room.getDailyRate(),
                    room.getCapacity(),
                    room.getFurnitureDescription());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }
   
    // Function 7
        
    // Function 8
        
    // Function 9
     
} // RoomList
