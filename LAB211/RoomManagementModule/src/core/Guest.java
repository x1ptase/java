package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Guest implements Serializable{
    private String guestID;
    private String guestName;
    private Date doB;
    private boolean gender;
    private String phone;
    private String DesiredRID;
    private int rentalDate; // so ngay thue
    private Date startDate;// ngay bat dau 
    private ArrayList<String> coTenant=new ArrayList<>();

    public Guest(){
    }
    
    public Guest(String ID){
        this.guestID=ID;
    }

    public Guest(String guestID, String guestName, Date doB, boolean gender, String phone, String DesiredRID, int rentalDate, Date startDate, ArrayList<String> coTenant) {
        this.guestID = guestID;
        this.guestName = guestName;
        this.doB = doB;
        this.gender = gender;
        this.phone = phone;
        this.DesiredRID = DesiredRID;
        this.rentalDate = rentalDate;
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

    public String getDesiredRID() {
        return DesiredRID;
    }

    public void setDesiredRID(String DesiredRID) {
        this.DesiredRID = DesiredRID;
    }

    public int getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(int rentalDate) {
        this.rentalDate = rentalDate;
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
    public boolean equals(Object obj) {
        Guest g=(Guest)obj;
        return this.getGuestID().equalsIgnoreCase(g.getGuestID());
    }
} // guest