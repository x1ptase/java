package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lớp quản lý kết nối CSDL
 */
public class DBContext {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PartyDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Cannot load JDBC driver", e);
        }
    }

    /**
     * Mỗi lần gọi sẽ trả về một Connection mới.
     * Gọi xong nhớ đóng bằng try-with-resources hoặc conn.close().
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
