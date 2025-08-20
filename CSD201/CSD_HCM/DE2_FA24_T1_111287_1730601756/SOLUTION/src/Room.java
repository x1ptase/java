/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Room {

    private String code;
    private int status;
    private int size;
    private int price;

    public Room(String code, int status, int size, int price) {
        this.code = code;
        this.status = status;
        this.size = size;
        this.price = price;
    }

    public Room(int size, int price) {
        this.size = size;
        this.price = price;
    }

    public Room() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
     public int getStatus() {
        return status;
    }

    public void setStatus(int s) {
        this.status = s;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "(" + code + "," + status + "," + size + "," + price + ")";
    }
}
