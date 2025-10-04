/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package sample.product;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author x1pta
 */
public class CartDTO {
    private Map<String, ProductDTO> cart;

    public CartDTO() {
    }

    public CartDTO(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }
    
    public boolean add(ProductDTO product){
        boolean check=false;
        try{
            if(this.cart == null){
                this.cart=new HashMap<>();
            }
            if(this.cart.containsKey(product.getId())){
                int currentQuantity=this.cart.get(product.getId()).getQuantity();
                product.setQuantity(currentQuantity + product.getQuantity());
            }
            this.cart.put(product.getId(), product);
            check=true; // Set to true when successfully added
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return check;
    }
    
    public boolean change(String id, ProductDTO product){
        boolean check=false;
        try{
            if(this.cart != null){
                if(this.cart.containsKey(id)){
                    this.cart.replace(id, product);
                    check=true;
                }
            }
        } catch(Exception ex){
            
        }
        return check;
    }
    
    public boolean remove(String id){
        boolean check=false;
        try{
            if(this.cart != null){
                if(this.cart.containsKey(id)){
                    this.cart.remove(id);
                    check=true;
                }
            }
        } catch(Exception ex){
            
        }
        return check;
    }
}
