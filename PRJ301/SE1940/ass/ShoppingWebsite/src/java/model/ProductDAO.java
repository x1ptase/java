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

    // --- CÁC HẰNG SỐ SQL ---
    private static final String VIEW_ALL_PRODUCTS_SQL = 
        "SELECT ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage FROM Products";
    private static final String DELETE_SQL = "DELETE FROM Products WHERE ProductID = ?";
    private static final String CREATE_SQL = 
        "INSERT INTO Products (ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = 
        "UPDATE Products SET ProductName=?, SupplierID=?, CategoryID=?, QuantityPerUnit=?, UnitPrice=?, ProductImage=? " +
        "WHERE ProductID=?";

    // --- 1. CHỨC NĂNG VIEW (Đã sửa lỗi SQL) ---
    public List<ProductDTO> viewAllProducts() throws Exception{
        List<ProductDTO> list=new ArrayList<>();
        Connection cnn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            cnn=DBUtils.getConnection();
            if(cnn != null){
                // Dùng PreparedStatement cho câu lệnh SELECT đơn giản
                ps=cnn.prepareStatement(VIEW_ALL_PRODUCTS_SQL); 
                rs=ps.executeQuery();
                
                while(rs.next()){
                    int productID=rs.getInt("ProductID");
                    String productName=rs.getString("ProductName");
                    int supplierID=rs.getInt("SupplierID");
                    int categoryID=rs.getInt("CategoryID");
                    String quantityPerUnit=rs.getString("QuantityPerUnit");
                    float unitPrice=rs.getFloat("UnitPrice");
                    String productImage=rs.getString("ProductImage");

                    list.add(new ProductDTO(productID, productName, supplierID, 
                            categoryID, quantityPerUnit, unitPrice, productImage));
                }
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

    // --- 2. CHỨC NĂNG CREATE ---
    public boolean createProduct(ProductDTO product) throws Exception {
        Connection cnn = null;
        PreparedStatement ps = null;
        boolean check = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                ps = cnn.prepareStatement(CREATE_SQL);
                // Ghi chú: Nếu ProductID là IDENTITY, bạn nên bỏ setInt(1, ...)
                ps.setInt(1, product.getProductID()); 
                ps.setString(2, product.getProductName());
                ps.setInt(3, product.getSupplierID());
                ps.setInt(4, product.getCategoryID());
                ps.setString(5, product.getQuantityPerUnit());
                ps.setFloat(6, product.getUnitPrice());
                ps.setString(7, product.getProductImage());
                
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (ps != null) ps.close();
            if (cnn != null) cnn.close();
        }
        return check;
    }
    
    // --- 3. CHỨC NĂNG UPDATE ---
    public boolean updateProduct(ProductDTO product) throws Exception {
        Connection cnn = null;
        PreparedStatement ps = null;
        boolean check = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                ps = cnn.prepareStatement(UPDATE_SQL);
                ps.setString(1, product.getProductName());
                ps.setInt(2, product.getSupplierID());
                ps.setInt(3, product.getCategoryID());
                ps.setString(4, product.getQuantityPerUnit());
                ps.setFloat(5, product.getUnitPrice());
                ps.setString(6, product.getProductImage());
                ps.setInt(7, product.getProductID()); // Dùng cho mệnh đề WHERE
                
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (ps != null) ps.close();
            if (cnn != null) cnn.close();
        }
        return check;
    }

    // --- 4. CHỨC NĂNG DELETE ---
    public boolean deleteProduct(int productID) throws Exception {
        Connection cnn = null;
        PreparedStatement ps = null;
        boolean check = false;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                ps = cnn.prepareStatement(DELETE_SQL);
                ps.setInt(1, productID);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (ps != null) ps.close();
            if (cnn != null) cnn.close();
        }
        return check;
    }
}