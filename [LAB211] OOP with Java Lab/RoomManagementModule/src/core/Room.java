package core;

import java.util.Calendar;
import java.util.Date;
import tool.ConsoleInputter;

public class Room {
    private String roomID;
    private String roomName;
    private String roomType;
    private float dailyRate;
    private float capacity;
    private String funrnitureDescription;

    public Room(){
    }

    public Room(String roomID){
        this.roomID = roomID;
    }

    public Room(String roomID, String roomName, String roomType, float dailyRate, float capacity, String funrnitureDescription) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomType = roomType;
        this.dailyRate = dailyRate;
        this.capacity = capacity;
        this.funrnitureDescription = funrnitureDescription;
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

    public float getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(float dailyRate) {
        this.dailyRate = dailyRate;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public String getFurnitureDescription() {
        return funrnitureDescription;
    }

    public void setFunrnitureDescription(String funrnitureDescription) {
        this.funrnitureDescription = funrnitureDescription;
    }

    @Override
    public boolean equals(Object obj){
        Room room=(Room)obj;
        return this.getRoomID().equalsIgnoreCase(room.getRoomID());
    }

    public boolean isRented(GuestList list, Date startDate, int rentals) {
        if (list == null || list.isEmpty() || startDate == null || rentals <= 0) {
            return false;
        }

        // tinh ngay ket thuc cua khoang thoi gian can kiem tra
        Calendar checkCal = Calendar.getInstance();
        checkCal.setTime(startDate);
        checkCal.add(Calendar.DATE, rentals - 1);
        Date checkEndDate = checkCal.getTime();

        for (Guest guest : list) {
            if (guest.getDesiredRID().trim().equalsIgnoreCase(this.roomID.trim())) {
                Date guestStartDate = guest.getStartDate();
                int guestDuration = guest.getRentalDate();

                // tinh ngay ket thuc cua khach
                Calendar guestCal = Calendar.getInstance();
                guestCal.setTime(guestStartDate);
                guestCal.add(Calendar.DATE, guestDuration - 1);
                Date guestEndDate = guestCal.getTime();

                // kiem tra giao nhau cua 2 khoang thoi gian
                if (!checkEndDate.before(guestStartDate) && !guestEndDate.before(startDate)) {
                    return true; // co giao nhau --> phong duoc thue
                }
            }
        }
    return false; // khong giao nhau --> phong trong
}
}//Room