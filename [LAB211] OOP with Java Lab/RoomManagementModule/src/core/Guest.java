package core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Guest implements Serializable {
    private String guestID;
    private String guestName;
    private Date doB;
    private String gender;
    private String phoneNumber;
    private String roomID;
    private int rentalDays; 
    private Date startDate;
    private String coTenant;

    public Guest(){
    }

    public Guest(String nationalID, String fullName, Date birthdate, String gender,
                 String phoneNumber, String roomID, int rentalDays, Date startDate, String coTenant) {
        this.guestID = nationalID;
        this.guestName = fullName;
        this.doB = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.roomID = roomID;
        this.rentalDays = rentalDays; 
        this.startDate = startDate;
        this.coTenant = coTenant;
    }

    // getters - setters
    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Date getDoB() {
        return doB;
    }

    public void setDoB(Date doB) {
        this.doB = doB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) { 
        this.rentalDays = rentalDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCoTenant() {
        return coTenant;
    }

    public void setCoTenant(String coTenant) {
        this.coTenant = coTenant;
    }

    @Override
    public boolean equals(Object obj) {
        Guest g=(Guest)obj;
        return this.guestID.equals(g.guestID);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal=Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, rentalDays); 
        Date checkOut=cal.getTime();
        return String.format("Full name: %s\nPhone number: %s\nBirth date: %s\nGender: %s\nRental room: %s\nCheck in: %s\nRental days: %d\nCheck out: %s\nCo-tenant: %s",
                guestName, phoneNumber, sdf.format(doB), gender, roomID,
                sdf.format(startDate), rentalDays, sdf.format(checkOut), coTenant.isEmpty() ? "null" : coTenant);
    }
}