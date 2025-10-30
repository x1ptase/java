package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    protected Connection cnn;

    public DBContext() {
        try {
            String url="jdbc:sqlserver://localhost:1433;databaseName=MyStock";
            String username="sa";
            String pass="12345";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn=DriverManager.getConnection(url,username,pass);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void closeConnection() {
        try{
            if(cnn != null && !cnn.isClosed()){
                cnn.close();
                System.out.println("Connection closed successfully.");
            }
        } catch(SQLException ex){
            System.out.println("Error closing connection: " + ex.getMessage());
        }
    }
    
    // Bổ sung method tiện dụng cho DAO
    public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=MyStock;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String pass = "12345";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, username, pass);
    }

//    public static void main(String[] args) {
//        DBContext db=new DBContext();
//    }
}