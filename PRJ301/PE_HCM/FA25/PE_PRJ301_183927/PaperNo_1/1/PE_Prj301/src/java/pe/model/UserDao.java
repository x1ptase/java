/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class UserDao {
    //-----            your code here   --------------------------------
    private static final String CHECK_LOGIN_SQL="SELECT * FROM tblUsers WHERE userID=? AND password=?";
    
    public UserDto checkLogin(String userID, String password) throws SQLException{
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            cnn=DbUtils.getConnection();
            ps=cnn.prepareStatement(CHECK_LOGIN_SQL);
            ps.setString(1, userID);
            ps.setString(2, password);
            rs=ps.executeQuery();
            
            if(rs.next()){
                return new UserDto(
                        rs.getString("userID"),
                        rs.getString("fullName"),
                        rs.getString("password"),
                        rs.getString("roleID"),
                        rs.getInt("status")
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