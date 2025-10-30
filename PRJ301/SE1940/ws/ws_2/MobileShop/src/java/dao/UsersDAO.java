package dao;

import dal.DBContext;
import model.Users;
import java.sql.*;

public class UsersDAO {
    public Users checkLogin(String userId, int password) {
        String sql = "SELECT userId, password, fullName, role FROM Users WHERE userId=? AND password=?";
        try (
                Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setInt(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Users(
                        rs.getString("userId"),
                        rs.getInt("password"),
                        rs.getString("fullName"),
                        rs.getInt("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}