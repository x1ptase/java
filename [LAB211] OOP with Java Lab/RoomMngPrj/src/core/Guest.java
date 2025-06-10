
package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Guest implements Serializable{
    private String ID;
    private String name;
    private Date birthDate;
    private boolean gender;
    private String phone;
    private String DesiredRID;//Desired room ID
    private int rentalDate; // so ngay thue
    private Date startDate;// ngay bat dau thue
    private ArrayList<String> coTenant = new ArrayList<>();

    public Guest() {
    }

    public Guest(String ID) {
        this.ID = ID;
    }

    public Guest(String ID, String name, Date birthDate, boolean gender, String phone, String DesiredRID, int rentalDate, Date startDate, ArrayList<String> coTenant) {
        this.ID = ID;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.DesiredRID = DesiredRID;
        this.rentalDate = rentalDate;
        this.startDate = startDate;
        this.coTenant = coTenant;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
        Guest g = (Guest) obj;
        return this.getID().equalsIgnoreCase(g.getID());
    }

}
