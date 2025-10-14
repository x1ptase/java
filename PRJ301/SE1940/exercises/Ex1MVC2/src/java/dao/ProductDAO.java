package dao;

import model.Product;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Product operations
 */
public class ProductDAO {
    
    /**
     * Get all products from database
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        List<Product> products=new ArrayList<>();
        String sql="SELECT ProductID, ProductName, UnitPrice, Quantity FROM Products";
        
        try(Connection cnn=DBUtil.getConnection();
             PreparedStatement ps=cnn.prepareStatement(sql);
             ResultSet rs=ps.executeQuery()){
            
            while(rs.next()){
                Product product=new Product(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getDouble("UnitPrice"),
                    rs.getInt("Quantity")
                );
                products.add(product);
            }
        } catch(SQLException ex){
            System.out.println("Error getting all products: " + ex.getMessage());
        }
        
        return products;
    }
    
    /**
     * Get product by ID
     * @param productID Product ID
     * @return Product object or null if not found
     */
    public Product getProductById(int productID){
        String sql="SELECT ProductID, ProductName, UnitPrice, Quantity FROM Products WHERE ProductID=?";
        
        try(Connection cnn=DBUtil.getConnection();
             PreparedStatement ps=cnn.prepareStatement(sql)){
            
            ps.setInt(1, productID);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    return new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("UnitPrice"),
                        rs.getInt("Quantity")
                    );
                }
            }
        } catch(SQLException ex){
            System.out.println("Error getting product by ID: " + ex.getMessage());
        }
        
        return null;
    }
    
    /**
     * Add new product to database
     * @param product Product to add
     * @return true if successful, false otherwise
     */
    public boolean addProduct(Product product){
        String sql="INSERT INTO Products(ProductName, UnitPrice, Quantity) VALUES(?,?,?)";
        
        try(Connection cnn=DBUtil.getConnection();
             PreparedStatement ps=cnn.prepareStatement(sql)){
            
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getUnitPrice());
            ps.setInt(3, product.getQuantity());
            
            int rowsAffected=ps.executeUpdate();
            return rowsAffected > 0;
        } catch(SQLException ex){
            System.out.println("Error adding product: " + ex.getMessage());
            return false;
        }
    }
    
    /**
     * Update existing product
     * @param product Product to update
     * @return true if successful, false otherwise
     */
    public boolean updateProduct(Product product){
        String sql="UPDATE Products SET ProductName=?, UnitPrice=?, Quantity=? WHERE ProductID=?";
        
        try(Connection cnn=DBUtil.getConnection();
             PreparedStatement ps=cnn.prepareStatement(sql)){
            
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getUnitPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getProductID());
            
            int rowsAffected=ps.executeUpdate();
            return rowsAffected > 0;
        } catch(SQLException ex){
            System.out.println("Error updating product: " + ex.getMessage());
            return false;
        }
    }
    
    /**
     * Delete product by ID
     * @param productID Product ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteProduct(int productID){
        String sql="DELETE FROM Products WHERE ProductID=?";
        
        try(Connection cnn=DBUtil.getConnection();
             PreparedStatement ps=cnn.prepareStatement(sql)){
            
            ps.setInt(1, productID);
            int rowsAffected=ps.executeUpdate();
            return rowsAffected > 0;
        } catch(SQLException ex){
            System.out.println("Error deleting product: " + ex.getMessage());
            return false;
        }
    }
}
