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
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class RoomForRentDao {
    //-----            your code here   --------------------------------
    private static final String SEARCH_BY_LOCATION_SQL = 
            "SELECT * FROM RoomForRent WHERE location LIKE ? AND status != -2";
    private static final String DELETE_ROOM_SQL = 
            "UPDATE RoomForRent SET status = -2 WHERE id = ?";
    
    public List<RoomForRentDto> searchByLocation(String searchCriteria) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RoomForRentDto> list = new ArrayList<>();
        
        try {
            cnn = DbUtils.getConnection();
            if (searchCriteria == null || searchCriteria.trim().isEmpty()) {
                return list; // Return empty list if no search criteria
            }
            
            ps = cnn.prepareStatement(SEARCH_BY_LOCATION_SQL);
            String searchPattern = "%" + searchCriteria.trim() + "%";
            ps.setString(1, searchPattern);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                RoomForRentDto room = new RoomForRentDto(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getFloat("price"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("postedDate"),
                        rs.getInt("status"),
                        rs.getString("username")
                );
                list.add(room);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return list;
    }
    
    public boolean deleteRoom(int roomId) throws SQLException {
        Connection cnn = null;
        PreparedStatement ps = null;
        boolean result = false;
        
        try {
            cnn = DbUtils.getConnection();
            ps = cnn.prepareStatement(DELETE_ROOM_SQL);
            ps.setInt(1, roomId);
            int rowsAffected = ps.executeUpdate();
            result = (rowsAffected > 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return result;
    }
}
