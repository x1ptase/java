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

    public String getFunrnitureDescription() {
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

    public boolean isRented(GuestList list, Date sDate, int rentals){
        if(list.isEmpty()){
            return false;
        }

        for(Guest guest : list){
            if(guest.getDesiredRID().trim().equalsIgnoreCase(this.roomID.trim())){
                Date startDate=guest.getStartDate();
                int duration=guest.getRentalDate();

                Calendar cal=Calendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.DATE, duration - 1);
                Date endDate=cal.getTime();

                for(int i=0; i<rentals; i++){
                    Calendar checkCal=Calendar.getInstance();
                    checkCal.setTime(sDate);
                    checkCal.add(Calendar.DATE, i);
                    Date checkDate=checkCal.getTime();
                    if(ConsoleInputter.isSameDay(checkDate, startDate))
                        return true;
                    if(!checkDate.before(startDate) && !checkDate.after(endDate))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean isRented(GuestList list, Date sDate){
        if(list.isEmpty()){
            return false;
        }
        for(Guest guest : list){
            if(guest.getDesiredRID().trim().equalsIgnoreCase(this.roomID.trim())){
                Date startDate=guest.getStartDate();
                int duration=guest.getRentalDate();

                Calendar cal=Calendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.DATE, duration - 1);
                Date endDate=cal.getTime();

                if(!sDate.before(startDate) && !sDate.after(endDate))
                    return true;
            }
        }
        return false;
    }
}//Room