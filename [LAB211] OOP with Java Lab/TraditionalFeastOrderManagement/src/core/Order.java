package core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order{
    private String orderID; // mã đơn hàng (tự động tạo)
    private String custCode; // mã khách hàng
    private String menuID; // mã thực đơn
    private int tableCount; // số bàn đặt
    private Date eventDate; // ngày tổ chức
    private double totalCost; // tổng chi phí

    public Order(){
    }
    // Constructor
    public Order(String orderID, String custCode, String menuID, int tableCount, Date eventDate, double totalCost){
        this.orderID=orderID;
        this.custCode=custCode;
        this.menuID=menuID;
        this.tableCount=tableCount;
        this.eventDate=eventDate;
        this.totalCost=totalCost;
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

    public int getTableCount() {
        return tableCount;
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

    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    // toString() - Hiển thị thông tin đơn hàng
    @Override
    public String toString(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        return String.format("ID: %s | Customer ID: %s | Menu ID: %s | Tables: %d | Event Date: %s | Total Cost: %.2f VND",
                orderID, custCode, menuID, tableCount, dateFormat.format(eventDate), totalCost);
    }
}
