package models;

import java.sql.*;
import java.util.*;
import utils.DBUtils;

public class CarsDAO {
    
    private static final String VIEW_ALL_SQL="SELECT * FROM Cars";
    private static final String FIND_BY_ID="SELECT * FROM Cars WHERE CarID=?";
    private static final String CREATE_SQL="INSERT INTO Cars (CarID, CarName, Manufacturer, Price, ReleasedYear) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL="UPDATE Cars SET CarName=?, Manufacturer=?, Price=?, ReleasedYear=? WHERE CarID=?";
    private static final String DELETE_SQL="DELETE FROM Cars WHERE CarID=?";
    
    public List<CarsDTO> viewAllCars() throws Exception {
        List<CarsDTO> list=new ArrayList<>();
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(VIEW_ALL_SQL);
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

    public CarsDTO findById(int carID) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, carID);
            rs=ps.executeQuery();
            if(rs.next()){
                return new CarsDTO(
                    rs.getInt("CarID"),
                    rs.getString("CarName"),
                    rs.getString("Manufacturer"),
                    rs.getDouble("Price"),
                    rs.getInt("ReleasedYear")
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

    public void createCar(CarsDTO car) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(CREATE_SQL);
            ps.setInt(1, car.getCarID());
            ps.setString(2, car.getCarName());
            ps.setString(3, car.getManufacturer());
            ps.setDouble(4, car.getPrice());
            ps.setInt(5, car.getReleasedYear());
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

    public void updateCar(CarsDTO car) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(UPDATE_SQL);
            ps.setString(1, car.getCarName());
            ps.setString(2, car.getManufacturer());
            ps.setDouble(3, car.getPrice());
            ps.setInt(4, car.getReleasedYear());
            ps.setInt(5, car.getCarID());
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

    public void deleteCar(int carID) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(DELETE_SQL);
            ps.setInt(1, carID);
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
}