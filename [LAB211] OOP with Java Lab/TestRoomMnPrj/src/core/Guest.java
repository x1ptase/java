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
    
}
