package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author x1pta
 */
public class AccountDAO {
    public AccountDTO checkLogin(String userName, String password) throws SQLException{
        String url="SELECT * FROM Account WHERE UserName=? AND Password=?";
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(url);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs=ps.executeQuery(); // send and save
            if(rs.next()){
                return new AccountDTO(
                        rs.getInt("accountID"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("fullName"),
                        rs.getBoolean("type")
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
