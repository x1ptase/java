package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

public class UsersDAO {
    
    private static final String CHECK_LOGIN="SELECT * FROM Users WHERE userId=? AND password=?";
    
    public UsersDTO checkLogin(String userId, String password) throws SQLException{
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(CHECK_LOGIN);
            ps.setString(1, userId);
            ps.setString(2, password);
            rs=ps.executeQuery();
            
            if(rs.next()){
                return new UsersDTO(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("fullName"),
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
