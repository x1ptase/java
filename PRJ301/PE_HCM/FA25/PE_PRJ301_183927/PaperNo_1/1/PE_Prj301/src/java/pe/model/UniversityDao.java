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
public class UniversityDao {
    //-----            your code here   --------------------------------
    private static final String VIEW_ALL_SQL="SELECT * FROM tblUniversity"; 
    private static final String SEARCH_BY_UNI_SQL="SELECT * FROM tbUniversity WHERE shortName LIKE ?";
    
    public List<UniversityDto> searchByUniversity(String searchCriteria) throws SQLException, Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<UniversityDto> list=new ArrayList<>();
        
        try{
            cnn=DbUtils.getConnection();
            if(searchCriteria == null || searchCriteria.trim().isEmpty()){
                return list;
            }
            
            ps=cnn.prepareStatement(SEARCH_BY_UNI_SQL);
            String searchPattern="%" + searchCriteria.trim() + "%";
            ps.setString(1, searchPattern);
            rs=ps.executeQuery();
            
            while(rs.next()) {
                UniversityDto uni=new UniversityDto(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("shortName"),
                        rs.getString("description"),
                        rs.getInt("foundedYear"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("region"),
                        rs.getString("type"),
                        rs.getInt("totalStudent"),
                        rs.getInt("totalFaculties"),
                        rs.getInt("isDraft")
                );
                list.add(uni);
            }
        } catch(Exception ex){
            throw ex;
        } finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
        return list;
    }
}
