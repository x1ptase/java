/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Controllers.Cart;

import Models.Entities.CartItem;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x1pta
 */
public class CartUtil {
    public HashMap<String, CartItem> getCartFromCookie(Cookie cookieCart){
        HashMap<String, CartItem> cart=null;
        String[] arrItemDetail;
        String itemId, itemName;
        int quantity;
        double unitPrice;
        CartItem item;
        Base64.Decoder base64Decoder=Base64.getDecoder();
        cart=new HashMap();
        String encodedString=new String(base64Decoder.
                decode(cookieCart.getValue().getBytes()));
        String[] itemsList=encodedString.split("\\|");
        for(String strItem : itemsList){
            arrItemDetail=strItem.split(",");
            itemId=arrItemDetail[0].trim();
            itemName=arrItemDetail[1].trim();
            quantity=Integer.parseInt(arrItemDetail[2].trim());
            unitPrice=Double.parseDouble(arrItemDetail[3]);
            item=new CartItem(itemId, itemName, quantity, unitPrice);
            cart.put(itemId, item);
        }
        return cart;
    }
    
    public Cookie getCookieByName(HttpServletRequest request, String cookieName){
        Cookie[] arrCookies=request.getCookies();
        if(arrCookies != null){
            for(Cookie cookie : arrCookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
        }
        return null;
    }
    
    public void saveCartToCookie(HttpServletRequest request,
            HttpServletResponse response, String strItemsInCart){
        String cookieName="Cart";
        Cookie cookieCart=getCookieByName(request, cookieName);
        if(cookieCart != null){
            cookieCart.setValue(strItemsInCart);
        } else{
            cookieCart=new Cookie(cookieName, strItemsInCart);
        }
        cookieCart.setMaxAge(30);
        response.addCookie(cookieCart);
    }
    
    public String convertCartToString(List<CartItem> itemsList){
        StringBuilder strItemsInCart=new StringBuilder();
        for(CartItem item : itemsList){
            strItemsInCart.append(item + "|");
        }
        Base64.Encoder base64Encoder=Base64.getEncoder();
        String encodeString=base64Encoder.
                encodeToString(strItemsInCart.toString().getBytes());
        return encodeString;
    }
}
