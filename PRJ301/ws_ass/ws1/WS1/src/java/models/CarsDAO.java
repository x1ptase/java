package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

public class CarsDAO {
    public List<CarsDTO> viewAllCars() throws Exception{
        List<CarsDTO> list=new ArrayList<>();
        String url="SELECT * FROM Cars";
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            cnn=DbUtils.getConnection();
            ps=cnn.prepareStatement(url);
            rs=ps.executeQuery();
            
            while(rs.next()){
                list.add(new CarsDTO(
                        rs.getInt("CarID"),
                        rs.getString("CarName"),
                        rs.getString("Manufacturer"),
                        rs.getDouble("Price"),
                        rs.getInt("ReleasedYear")
                ));
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
}
