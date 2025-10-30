package dao;

import dal.DBContext;
import model.Mobiles;
import java.sql.*;
import java.util.*;

public class MobilesDAO {
    public List<Mobiles> getAll() {
        List<Mobiles> list = new ArrayList<>();
        String sql = "SELECT * FROM Mobile";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Mobiles m = new Mobiles(
                        rs.getString("mobileId"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getString("mobileName"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getBoolean("notSale")
                );
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}