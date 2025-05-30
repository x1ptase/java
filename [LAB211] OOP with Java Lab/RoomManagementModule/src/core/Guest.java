package core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Guest implements Serializable {
    private String nationalID;
    private String fullName;
    private Date birthdate;
    private String gender;
    private String phoneNumber;
    private String roomID;
    private int rentalDays; // Đổi từ String thành int
    private Date startDate;
    private String coTenant;

    public Guest() {
    }

    public Guest(String nationalID, String fullName, Date birthdate, String gender,
                 String phoneNumber, String roomID, int rentalDays, Date startDate, String coTenant) {
        this.nationalID = nationalID;
        this.fullName = fullName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.roomID = roomID;
        this.rentalDays = rentalDays; // int
        this.startDate = startDate;
        this.coTenant = coTenant;
    }

    // Getters - Setters
    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

    public int getRentalDays() { // Đổi từ String thành int
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) { // Đổi từ String thành int
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
        Guest g = (Guest) obj;
        return this.nationalID.equals(g.nationalID);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, rentalDays); // rentalDays là int
        Date checkOut = cal.getTime();
        return String.format(
                "Full name: %s\nPhone number: %s\nBirth date: %s\nGender: %s\nRental room: %s\nCheck in: %s\nRental days: %d\nCheck out: %s\nCo-tenant: %s",
                fullName, phoneNumber, sdf.format(birthdate), gender, roomID,
                sdf.format(startDate), rentalDays, sdf.format(checkOut), coTenant.isEmpty() ? "null" : coTenant); // %d cho rentalDays
    }
}