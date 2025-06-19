package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Guest implements Serializable{
    private String guestID; // cccd
    private String guestName; 
    private String doB;
    private String gender;
    private String phoneNumber;
    private String desiredRoomID; // ma phong
    private int rentalDays; // so ngay thue
    private Date startDate; // ngay nhan phong
    private ArrayList<String> coTenant=new ArrayList<>(); // roomMate
    
    // c-tor
    public Guest(){
    }

    // c-tor
    public Guest(String guestID, String guestName, String doB, String gender, 
            String phoneNumber, String desiredRoomID, int rentalDays, Date startDate) {
        this.guestID = guestID;
        this.guestName = guestName;
        this.doB = doB;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.desiredRoomID = desiredRoomID;
        this.rentalDays = rentalDays;
        this.startDate = startDate;
    }
    
    // ham de so sanh voi method
    public Guest(String guestID){
        this.guestID=guestID;
    }

    public String getGuestID() {
        return guestID;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getDoB() {
        return doB;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDesiredRoomID() {
        return desiredRoomID;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public ArrayList<String> getCoTenant() {
        return coTenant;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setDoB(String doB) {
        this.doB = doB;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDesiredRoomID(String desiredRoomID) {
        this.desiredRoomID = desiredRoomID;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setCoTenant(ArrayList<String> coTenant) {
        this.coTenant = coTenant;
    }

    @Override
    public boolean equals(Object obj) {
        Guest guest=(Guest)obj;
        return this.getGuestID().equalsIgnoreCase(guest.getGuestID());
    } 
    
} // Guest
