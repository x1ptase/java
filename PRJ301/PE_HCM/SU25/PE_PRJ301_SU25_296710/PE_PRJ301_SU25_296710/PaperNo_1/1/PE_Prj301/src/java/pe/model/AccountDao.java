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
public class AccountDao {
    //-----            your code here   --------------------------------
    private static final String CHECK_LOGIN_SQL="SELECT * FROM Account WHERE username=? AND password=?";
    
    public AccountDto checkLogin(String username, String password) throws SQLException{
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            cnn=DbUtils.getConnection();
            ps=cnn.prepareStatement(CHECK_LOGIN_SQL);
            ps.setString(1, username);
            ps.setString(2, password);
            rs=ps.executeQuery();
            if(rs.next()){
                return new AccountDto(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullName"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getInt("status"),
                        rs.getInt("role")
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
