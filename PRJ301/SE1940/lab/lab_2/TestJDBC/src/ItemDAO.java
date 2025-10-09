import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO{
    public Connection getConnection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url="jdbc:sqlserver://localhost:1433;databaseName=SampleDB";
        Connection cnn=DriverManager.getConnection(url, "sa", "12345");
        return cnn;
    }
    
    public void printItems() throws Exception{
        PreparedStatement ps=null;
        Connection cnn=null;
        ResultSet rs=null;
        
        try{
            cnn=getConnection();
            String sql="SELECT ItemID, ItemName, Quantity FROM Items";
            ps=cnn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                System.out.format("%-10s %-15s %5d %n",
                        rs.getString("ItemID"), rs.getString("ItemName"),
                        rs.getInt("Quantity"));
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
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
    }
    
    public void AddNewItem(String itemID, String itemName, int quantity)
            throws Exception{
        PreparedStatement ps=null;
        Connection cnn=null;
        
        try{
            cnn=getConnection();
            String sql="INSERT Items(ItemID, ItemName, Quantity) VALUES (?,?,?,?)";
            ps=cnn.prepareStatement(sql);
            ps.setString(1, itemID);
            ps.setString(2, itemName);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally{
            if(ps != null){
                ps.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
    }
    
    public void RemoveItem(String itemID)
            throws Exception{
        PreparedStatement ps=null;
        Connection cnn=null;
        
        try{
            cnn=getConnection();
            String sql="DELETE Items WHERE ItemID=?";
            ps=cnn.prepareStatement(sql);
            ps.setString(1, itemID);
            ps.executeUpdate();
            System.out.println("Item has been removed");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally{
            if(ps != null){
                ps.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
    }
    
    public void UpdateItem(String itemID, String itemName, int quantity)
            throws Exception{
        PreparedStatement ps=null;
        Connection cnn=null;
        
        try{
            cnn=getConnection();
            String sql="Update Items SET ItemName=?, Quantity=?, WHERE ItemID=?";
            ps=cnn.prepareStatement(sql);
            ps.setString(1, itemName);
            ps.setInt(2, quantity);
            ps.setString(3, itemID);
            ps.executeUpdate();
            System.out.println("Item has been updated");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally{
            if(ps != null){
                ps.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
    }
}