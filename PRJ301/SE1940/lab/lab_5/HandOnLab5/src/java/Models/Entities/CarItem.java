/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Models.Entities;

/**
 *
 * @author x1pta
 */
public class CarItem {
    private String itemId;
    private String itemName;
    private int quantity;
    private double unitPrice;

    public CarItem() {
    }

    public CarItem(String itemId, String itemName, int quantity, double unitPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    private double getSubTotal(){
        return quantity*unitPrice;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f", itemId, itemName, quantity, unitPrice);
    }
    
    
}
