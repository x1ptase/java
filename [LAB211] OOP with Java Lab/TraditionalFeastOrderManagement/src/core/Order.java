package core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order{
    private String orderID; // mã đơn hàng (tự động tạo)
    private String custCode; // mã khách hàng
    private String menuID; // mã thực đơn
    private int numTable; // số bàn đặt
    private String price;  // giá tiền
    private Date eventDate; // ngày tổ chức
    private double totalCost; // tổng chi phí

    public Order(){
    }
    // Ctors
    public Order(String orderID, String custCode, String menuID, int tableCount, String price, Date eventDate, double totalCost){
        this.orderID=orderID;
        this.custCode=custCode;
        this.menuID=menuID;
        this.numTable=tableCount;
        this.price=price;
        this.eventDate=eventDate;
        this.totalCost=totalCost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // Getters
    public String getOrderID() {
        return orderID;
    }

    public String getCustCode() {
        return custCode;
    }

    public String getMenuID() {
        return menuID;
    }

    public int getNumTable() {
        return numTable;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // Setters
    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object obj) {
        Order o=(Order)obj;
        return this.getOrderID().equals(o.orderID);
    }
    
    
    
    // toString() - Hiển thị thông tin đơn hàng
    @Override
    public String toString(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return String.format("ID: %s | Event date: %s | Customer ID: %s | Set Menu: %s | Price: %s | Tables: %d | Cost: %,.0f",
                orderID, dateFormat.format(eventDate), custCode, menuID, price, numTable, totalCost);
    }
}
