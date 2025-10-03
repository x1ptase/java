/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Models.Entities;

/**
 *
 * @author x1pta
 */
public class Book {
    private String id;
    private String title;
    private double unitPrice;

    public Book() {
    }

    public Book(String id, String title, double unitPrice) {
        this.id = id;
        this.title = title;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %.2f", id, title, unitPrice);
    }
}
