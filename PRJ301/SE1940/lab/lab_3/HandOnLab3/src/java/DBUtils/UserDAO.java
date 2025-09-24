package DBUtils;

import java.sql.*;
import java.util.*;

public class UserDAO {
    private Connection conn;

    public UserDAO() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SampleDB";
        conn = DriverManager.getConnection(url, "sa", "12345");
    }

    public boolean checkLogin(String username, String password) throws Exception {
        String sql = "SELECT * FROM Registration WHERE username=? AND password=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        try {
            return rs.next();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }

    public List<User> searchUser(String keyword) throws Exception {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Registration WHERE lastname LIKE ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(
                    rs.getInt("ID"), // Use actual column name, e.g., "ID"
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("lastname"),
                    rs.getBoolean("role")
                );
                list.add(u);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return list;
    }

    public void removeUser(int id) throws Exception {
        String sql = "DELETE FROM Registration WHERE ID=?"; // Use actual column name
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
        }
    }

    // Add method to close connection
    public void close() throws Exception {
        if (conn != null) conn.close();
    }
}