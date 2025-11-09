package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

public class tbAccountDAO {
    public tbAccountDTO checkLogin(String userID, String password) throws SQLException{
        String url="SELECT * FROM tbAccount WHERE userID=? AND password=?";
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(url);
            ps.setString(1, userID);
            ps.setString(2, password);
            rs=ps.executeQuery();
            if(rs.next()){
                return new tbAccountDTO(
                        rs.getString("userID"),
                        rs.getString("fullName"),
                        rs.getString("password")
                );
            }
        } catch(Exception ex){
            ex.printStackTrace();
        } finally{
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(cnn!=null) cnn.close();
        }
        return null;
    }
}