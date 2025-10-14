package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database utility class for managing database connections
 */
public class DBUtil {
    private static final String DB_URL="jdbc:sqlserver://localhost:1433;databaseName=SaleDB";
    private static final String DB_USER="sa";
    private static final String DB_PASSWORD="12345";
    
    /**
     * Get database connection
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch(ClassNotFoundException ex){
            throw new SQLException("SQL Server JDBC Driver not found. Please check if sqljdbc4.jar is in the classpath.", ex);
        } catch(SQLException ex){
            throw new SQLException("Database connection failed. Please check your database configuration: " + ex.getMessage(), ex);
        }
    }
    
    /**
     * Close database connection
     * @param connection Connection to close
     */
    public static void closeConnection(Connection cnn){
        if(cnn != null){
            try{
                cnn.close();
            } catch(SQLException ex){
                System.out.println("Error closing connection: " + ex.getMessage());
            }
        }
    }
}
