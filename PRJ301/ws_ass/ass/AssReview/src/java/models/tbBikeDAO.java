package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class tbBikeDAO {
    
    public List<tbBikeDTO> searchBikeByName(String keyword) throws SQLException {
        List<tbBikeDTO> list = new ArrayList<>();
        String sql = "SELECT id, name, quantity, model FROM tbBike WHERE LOWER(name) LIKE ?";
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cnn = DBUtils.getConnection();
            ps = cnn.prepareStatement(sql);
            // Case-insensitive search with partial match
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                tbBikeDTO bike = new tbBikeDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getString("model")
                );
                list.add(bike);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cnn != null) cnn.close();
        }
        
        return list;
    }
    
    public boolean insertBike(tbBikeDTO bike) throws SQLException {
        String sql = "INSERT INTO tbBike (id, name, quantity, model) VALUES (?, ?, ?, ?)";
        Connection cnn = null;
        PreparedStatement ps = null;
        
        try {
            cnn = DBUtils.getConnection();
            ps = cnn.prepareStatement(sql);
            ps.setString(1, bike.getId());
            ps.setString(2, bike.getName());
            ps.setInt(3, bike.getQuantity());
            ps.setString(4, bike.getModel());
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (cnn != null) cnn.close();
        }
        
        return false;
    }
    
    public boolean checkBikeIDExists(String bikeID) throws SQLException {
        String sql = "SELECT id FROM tbBike WHERE id = ?";
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cnn = DBUtils.getConnection();
            ps = cnn.prepareStatement(sql);
            ps.setString(1, bikeID);
            rs = ps.executeQuery();
            
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cnn != null) cnn.close();
        }
        
        return false;
    }
    
    public boolean deleteBike(String bikeID) throws SQLException {
        String sql = "DELETE FROM tbBike WHERE id = ?";
        Connection cnn = null;
        PreparedStatement ps = null;
        
        try {
            cnn = DBUtils.getConnection();
            ps = cnn.prepareStatement(sql);
            ps.setString(1, bikeID);
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (cnn != null) cnn.close();
        }
        
        return false;
    }
}

