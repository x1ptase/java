package core;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Room implements Serializable{
    private String roomID;
    private String roomName;
    private String roomType;
    private float dailyRate; // gia thue moi ngay
    private float capacity; // suc chua
    private String furnitureDescription; // mo ta noi that
    
    //c-tor default
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

    // kiem tra phong 
    public boolean isRented(GuestList list, Date startDate, int rentals){
        if(list == null || list.isEmpty() || startDate == null || rentals <= 0){
            return false; // khong co thong tin dat phong --> phong trong
        }

        // tinh ngay ket thuc cua khoang thoi gian can kiem tra
        Calendar checkCal=Calendar.getInstance();
        checkCal.setTime(startDate);
        checkCal.add(Calendar.DATE, rentals - 1); // vd: rentals=3, startDate + 2 days
        Date checkEndDate=checkCal.getTime();

        for(Guest guest : list){
            // kiem tra ma phong: guest && current
            if(guest.getDesiredRoomID().trim().equalsIgnoreCase(this.roomID.trim())){
                Date guestStartDate=guest.getStartDate();
                int guestDuration=guest.getRentalDays();

                // tinh ngay ket thuc cua khach
                Calendar guestCal=Calendar.getInstance();
                guestCal.setTime(guestStartDate);
                guestCal.add(Calendar.DATE, guestDuration - 1);
                Date guestEndDate=guestCal.getTime();
                
                /*  guestStartDate=24/6/25 guestDuration=3 --> guestEndDate=26/6/25
                *   24/6 - 26/6
                *   23/6 - 25/6
                *   checkEndDate(25/6) khong truoc guestStartDate(24/6) true
                *   guestEndDate(26/6) khong truoc startDate(23/6) true
                */
                
                // kiem tra giao nhau cua 2 khoang thoi gian
                if(!checkEndDate.before(guestStartDate) && !guestEndDate.before(startDate)){
                    return true; // co giao nhau --> phong duoc thue
                }
            }
        }
        return false; // khong giao nhau --> phong trong
    }
    
} // Room