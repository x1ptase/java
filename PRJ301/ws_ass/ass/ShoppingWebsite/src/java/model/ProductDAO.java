package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author x1pta
 */
public class ProductDAO {

    private static final String VIEW_ALL_SQL="SELECT * FROM Products";
    private static final String FIND_BY_ID_SQL="SELECT * FROM Products WHERE ProductID=?";
    private static final String DELETE_SQL="DELETE FROM Products WHERE ProductID=?";
 
    public List<ProductDTO> viewAllProducts() throws Exception{
        List<ProductDTO> list=new ArrayList<>();
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(VIEW_ALL_SQL); 
            rs=ps.executeQuery();
                while(rs.next()){
                    list.add(new ProductDTO(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("SupplierID"),
                        rs.getInt("CategoryID"),
                        rs.getString("QuantityPerUnit"),
                        rs.getFloat("UnitPrice"),
                        rs.getString("ProductImage")
                    ));
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

    public ProductDTO findById(int productId) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            cnn=DBUtils.getConnection();
            ps=cnn.prepareStatement(FIND_BY_ID_SQL);
            ps.setInt(1, productId);
            rs=ps.executeQuery();
            if(rs.next()){
                return new ProductDTO(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getInt("SupplierID"),
                    rs.getInt("CategoryID"),
                    rs.getString("QuantityPerUnit"),
                    rs.getFloat("UnitPrice"),
                    rs.getString("ProductImage")
                );
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
        return null;
    }

    public boolean delete(int productID) throws Exception {
        Connection cnn=null;
        PreparedStatement ps=null;
        boolean check=false;
        
        try{
            cnn=DBUtils.getConnection();
            if(cnn != null){
                ps=cnn.prepareStatement(DELETE_SQL);
                ps.setInt(1, productID);
                check=ps.executeUpdate() > 0;
            }
        } catch(Exception ex){
            throw ex;
        } finally{
            if(ps != null){
                ps.close();
            }
            if(cnn != null){
               cnn.close();
            }
        }
        return check;
    }
}