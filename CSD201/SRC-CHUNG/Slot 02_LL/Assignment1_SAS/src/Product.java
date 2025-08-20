/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHAM KHAC VINH
 */
public class Product {

    protected String pcode;
    protected String pro_name;
    protected int quantity;
    protected int saled;
    protected double price;

    public Product(String pcode, String pro_name, int quantity, int saled, double price) {
        this.pcode = pcode;
        this.pro_name = pro_name;
        this.quantity = quantity;
        this.saled = saled;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %-10s | %-10s | %-10s | %.1f ", pcode,
                pro_name, quantity, saled,price, getValue());
    }
    public double getValue() {
        return price * saled;
    }
    
    
    
    
}
