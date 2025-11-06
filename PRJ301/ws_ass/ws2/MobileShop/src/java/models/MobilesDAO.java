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
    private static final String FIND_BY_ID="SELECT * FROM Mobiles WHERE mobileId=?";
    private static final String CREATE_SQL="INSERT INTO Mobiles (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL="UPDATE Mobiles SET description=?, price=?, mobileName=?, yearOfProduction=?, quantity=?, notSale=? WHERE mobileId=?";
    private static final String DELETE_SQL="DELETE FROM Mobiles WHERE mobileId=?";
    private static final String SEARCH_SQL="SELECT * FROM Mobiles WHERE mobileId LIKE ? OR mobileName LIKE ?";
    
    public List<MobilesDTO> viewAllMobiles() throws SQLException, Exception {
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
            throw ex;
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
    
    public MobilesDTO findById(String mobilesId) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(FIND_BY_ID);
            ps.setString(1, mobilesId);
            rs=ps.executeQuery();
            if(rs.next()) {
                return new MobilesDTO(
                    rs.getString("mobileId"),
                    rs.getString("description"),
                    rs.getFloat("price"),
                    rs.getString("mobileName"),
                    rs.getInt("yearOfProduction"),
                    rs.getInt("quantity"),
                    rs.getBoolean("notSale")
                );
            }
        } catch(Exception ex){
            throw ex;
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
    
    public void createMobiles(MobilesDTO mobiles) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(CREATE_SQL);
            
            ps.setString(1, mobiles.getMobileId());
            ps.setString(2, mobiles.getDescription());
            ps.setFloat(3, mobiles.getPrice());
            ps.setString(4, mobiles.getMobileName());
            ps.setInt(5, mobiles.getYearOfProduction());
            ps.setInt(6, mobiles.getQuantity());
            ps.setBoolean(7, mobiles.isNotSale());
            ps.executeUpdate();
        } catch(Exception ex){
            throw ex;
        } finally{
            if(cnn != null){
                cnn.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }
    
    public void update(MobilesDTO mobiles) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(UPDATE_SQL);
            ps.setString(1, mobiles.getDescription());
            ps.setFloat(2, mobiles.getPrice());
            ps.setString(3, mobiles.getMobileName());
            ps.setInt(4, mobiles.getYearOfProduction());
            ps.setInt(5, mobiles.getQuantity());
            ps.setBoolean(6, mobiles.isNotSale());
            ps.setString(7, mobiles.getMobileId());
            ps.executeUpdate();
        } catch(Exception ex){
            throw ex;
        } finally{
            if(cnn != null){
                cnn.close();
            }
            if(ps != null){
                ps.close();
            }
        }
         
    }

    public void delete(String mobileId) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(DELETE_SQL);
            ps.setString(1, mobileId);
            ps.executeUpdate();
        } catch(Exception ex){
            throw ex;
        } finally{
            if(cnn != null){
                cnn.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }

    public List<MobilesDTO> searchByNameOrId(String keyword) throws Exception{
        List<MobilesDTO> list=new ArrayList<>();
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(SEARCH_SQL);
            String kw="%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
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
            throw ex;
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
