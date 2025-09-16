import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO{
    public Connection getConnection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url="jdbc:sqlserver://localhost:1433;database=SampleDB";
        Connection connection=DriverManager.getConnection(url, "sa", "1");
        return connection;
    } // end Connection
    //---------------------------------------------------------------------
    public void printItems() throws Exception{
        PreparedStatement ps=null;
        Connection connection=null;
        ResultSet rs=null;
        
        try{
            connection=getConnection();
            String sql="select ItemID, ItemName, Quantity from Items";
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                // System.out.format("%-10d %-15s %5d\n",
                //         rs.getString(1), rs.getString(2),
                //         rs.getInt(3));
                System.out.format("%-10s %-15s %5d\n",
                        rs.getString("ItemID"), rs.getString("ItemName"),
                        rs.getInt("quantity"));
            } // end while
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally{
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    } // end printItems
    //-----------------------------------------------------------------
    public void AddNewItem(String itemID, String itemName, int quantity)
            throws Exception {
        PreparedStatement ps=null;
        Connection connection=null;

        try{
            connection=getConnection();
            String sql="insert Items (ItemID, ItemName, Quantity) values (?, ?, ?)";
            ps=connection.prepareStatement(sql);
            ps.setString(1, itemID);
            ps.setString(2, itemName);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            System.out.println("Item has been added.");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally{
            if(ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    } // end AddNewItem
    //--------------------------------------------------------
    public void RemoveItem(String itemID)
            throws Exception{
        PreparedStatement ps=null;
        Connection connection=null;

        try{
            connection=getConnection();
            String sql="delete Items where ItemID = ?";
            ps=connection.prepareStatement(sql);
            ps.setString(1, itemID);
            ps.executeUpdate();
            System.out.println("Item has been removed.");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally{
            if(ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    } // end RemoveItem
    //----------------------------------------------------------
    public void UpdateItem(String itemID, String itemName, int quantity)
            throws Exception {
        PreparedStatement ps=null;
        Connection connection=null;

        try{
            connection=getConnection();
            String sql="update Items set ItemName=?, Quantity=? where ItemID=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1, itemName);
            ps.setInt(2, quantity);
            ps.setString(3, itemID);
            ps.executeUpdate();
            System.out.println("Item has been updated.");
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        } finally{
            if(ps != null){
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    } // end UpdateItem
} // end class