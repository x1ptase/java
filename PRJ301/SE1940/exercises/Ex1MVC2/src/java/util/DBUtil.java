package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database utility class for managing database connections
 */
public class DBUtil {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=SaleDB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "12345";
    
    /**
     * Get database connection
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found. Please check if sqljdbc4.jar is in the classpath.", e);
        } catch (SQLException e) {
            throw new SQLException("Database connection failed. Please check your database configuration: " + e.getMessage(), e);
        }
    }
    
    /**
     * Close database connection
     * @param connection Connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
