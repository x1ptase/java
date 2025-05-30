package core;

public class Room {
    private String roomID; // ID room
    private String roomName; // name room
    private String roomType; // type room
    private double dailyRate; // price
    private int capacity; // people
    private String furniture; // room description 
    
    
    public Room(){
        
    }

    public Room(String roomID, String roomName, String roomType, double dailyRate, int capacity, String furniture) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomType = roomType;
        this.dailyRate = dailyRate;
        this.capacity = capacity;
        this.furniture = furniture;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }


    @Override
    public boolean equals(Object obj) {
        Room rm=(Room)obj;
        return this.roomID.equals(rm.roomID);
    }
    
    @Override
    public String toString() {
       return String.format("RoomID: %s | RoomName: %s | Type: %s | Rate: %.0f | Capacity: %d | Furniture: %s",
                roomID, roomName, roomType, dailyRate, capacity, furniture);
    }
    
    
}
