package core;

import java.io.Serializable;

public class Room implements Serializable{
    private String roomID;
    private String roomName;
    private String roomType;
    private float dailyRate; // gia thue moi ngay
    private float capacity; // suc chua
    private String furnitureDescription; // mo ta noi that
    
    //c-tor
    public Room(){
    }
    
    // c-tor
    public Room(String roomID, String roomName, String roomType, 
            float dailyRate, float capacity, String furnitureDescription) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomType = roomType;
        this.dailyRate = dailyRate;
        this.capacity = capacity;
        this.furnitureDescription = furnitureDescription;
    }
    
    // ham de so sanh voi method
    public Room(String roomID){
        this.roomID=roomID;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public float getDailyRate() {
        return dailyRate;
    }

    public float getCapacity() {
        return capacity;
    }

    public String getFurnitureDescription() {
        return furnitureDescription;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setDailyRate(float dailyRate) {
        this.dailyRate = dailyRate;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public void setFurnitureDescription(String furnitureDescription) {
        this.furnitureDescription = furnitureDescription;
    }

    // so sanh 2 doi tuong
    @Override
    public boolean equals(Object obj) {
        Room room=(Room)obj;
        return this.getRoomID().equalsIgnoreCase(room.getRoomID());
    }   
    
    
} // Room