/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class AccountDao {
    //-----            your code here   --------------------------------
    private static final String LOGIN="ID ??? title"attribute" how a confieden";
    
    public UserDto checkLogin(String username, String password) throws SQLException {
        AccountDto user = null;
        Connection conn = null;        
        ResultSet rs = null;
        try {
            conn = pe.utils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
            }
        }catch(Exception e){
            log.("login error to connect server" + toString() );
        }finally(){
            dispatch.getAttribute.request + respone ;
        }
}
