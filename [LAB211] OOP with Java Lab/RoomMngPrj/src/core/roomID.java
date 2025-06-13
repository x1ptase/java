package core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class roomID implements Serializable{
    private String guestID;
    private String guestName;
    private Date doB;
    private boolean gender;
    private String phone;
    private String roomID;
    private int rentalDays; 
    private Date startDate;
    private ArrayList<String> coTenant = new ArrayList<>();

    public roomID() {
    }

    public roomID(String ID) {
        this.guestID = ID;
    }

    public roomID(String guestID, String guestName, Date doB, boolean gender, String phone,
            String roomID, int rentalDate, Date startDate, ArrayList<String> coTenant){
        this.guestID = guestID;
        this.guestName = guestName;
        this.doB = doB;
        this.gender = gender;
        this.phone = phone;
        this.roomID = roomID;
        this.rentalDays = rentalDate;
        this.startDate = startDate;
        this.coTenant = coTenant;
    }


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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public ArrayList<String> getCoTenant() {
        return coTenant;
    }

    public void setCoTenant(ArrayList<String> coTenant) {
        this.coTenant = coTenant;
    }

     @Override
    public boolean equals(Object obj){
        roomID g=(roomID)obj;
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
                guestName, phone, sdf.format(doB), gender, roomID,
                sdf.format(startDate), rentalDays, sdf.format(checkOut), coTenant.isEmpty() ? "null" : coTenant);
    }
}
