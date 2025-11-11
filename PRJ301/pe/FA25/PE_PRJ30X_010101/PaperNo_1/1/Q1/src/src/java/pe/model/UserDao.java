/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class UserDao {
    //-----            your code here   --------------------------------
    public UserDto checkLogin(String userID, String password) throws Exception{
        String url="SELECT * FROM tblUsers WHERE userID=? AND password=?";
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            cnn=DbUtils.getConnection();
            ps=cnn.prepareStatement(url);
            ps.setString(1, userID);
            ps.setString(2, password);
            rs=ps.executeQuery();
            
            if(rs.next()){
                return new UserDto(
                        rs.getString("userID"),
                        rs.getString("fullName"),
                        rs.getString("roleID"),
                        rs.getString("password")
                );
            }
        } catch(Exception ex){
            ex.printStackTrace();
        } finally{
            if(cnn != null){
                cnn.close();
            }
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }
        return null;
    }
}
