package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class MobilesDAO {
    private static final String VIEW_ALL_SQL="SELECT * FROM Mobiles";
    
    public List<MobilesDTO> viewAllMobiles() throws SQLException {
        List<MobilesDTO> list=new ArrayList<>();
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(VIEW_ALL_SQL);
            rs=ps.executeQuery();
            while(rs.next()){
                list.add(new MobilesDTO(
                        rs.getString("mobileId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("mobileName"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getBoolean("notSale")
                ));
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
        return list;
    }
}
