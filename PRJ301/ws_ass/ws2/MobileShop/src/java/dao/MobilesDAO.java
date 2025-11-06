//package dao;
//
//import model.Mobiles;
//import java.sql.*;
//import java.util.*;
//import utils.DBUtils;
//
//public class MobilesDAO {
//    public List<Mobiles> getAll() {
//        List<Mobiles> list=new ArrayList<>();
//        String sql="SELECT * FROM Mobiles";
//        try(Connection conn=new DBUtils.
//             PreparedStatement ps=conn.prepareStatement(sql);
//             ResultSet rs=ps.executeQuery()) {
//            while(rs.next()){
//                Mobiles m=new Mobiles(
//                        rs.getString("mobileId"),
//                        rs.getString("description"),
//                        rs.getFloat("price"),
//                        rs.getString("mobileName"),
//                        rs.getInt("yearOfProduction"),
//                        rs.getInt("quantity"),
//                        rs.getBoolean("notSale")
//                );
//                list.add(m);
//            }
//        } catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return list;
//    }
//
//    public Mobiles getById(String mobileId) {
//        String sql="SELECT * FROM Mobiles WHERE mobileId = ?";
//        try (Connection conn = new DBContext().getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, mobileId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Mobiles(
//                    rs.getString("mobileId"),
//                    rs.getString("description"),
//                    rs.getFloat("price"),
//                    rs.getString("mobileName"),
//                    rs.getInt("yearOfProduction"),
//                    rs.getInt("quantity"),
//                    rs.getBoolean("notSale")
//                );
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public List<Mobiles> searchByPrice(float min, float max) {
//        List<Mobiles> list=new ArrayList<>();
//        String sql="SELECT * FROM Mobiles WHERE price BETWEEN ? AND ?";
//        try(Connection conn=new DBContext().getConnection();
//             PreparedStatement ps=conn.prepareStatement(sql)) {
//            ps.setFloat(1, min);
//            ps.setFloat(2, max);
//            ResultSet rs=ps.executeQuery();
//            while(rs.next()){
//                list.add(new Mobiles(
//                    rs.getString("mobileId"),
//                    rs.getString("description"),
//                    rs.getFloat("price"),
//                    rs.getString("mobileName"),
//                    rs.getInt("yearOfProduction"),
//                    rs.getInt("quantity"),
//                    rs.getBoolean("notSale")
//                ));
//            }
//        } catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return list;
//    }
//
//    public List<Mobiles> searchByNameOrId(String keyword){
//        List<Mobiles> list=new ArrayList<>();
//        String sql="SELECT * FROM Mobiles WHERE mobileId LIKE ? OR mobileName LIKE ?";
//        try(Connection conn=new DBContext().getConnection();
//                PreparedStatement ps=conn.prepareStatement(sql)){
//            String kw="%" + keyword + "%";
//            ps.setString(1, kw);
//            ps.setString(2, kw);
//            ResultSet rs=ps.executeQuery();
//            while(rs.next()){
//                list.add(new Mobiles(
//                    rs.getString("mobileId"),
//                    rs.getString("description"),
//                    rs.getFloat("price"),
//                    rs.getString("mobileName"),
//                    rs.getInt("yearOfProduction"),
//                    rs.getInt("quantity"),
//                    rs.getBoolean("notSale")
//                ));
//            }
//        } catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return list;
//    }
//
//    public void insert(Mobiles mobile) {
//        String sql = "INSERT INTO Mobiles (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try (Connection conn = new DBContext().getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, mobile.getMobileId());
//            ps.setString(2, mobile.getDescription());
//            ps.setFloat(3, mobile.getPrice());
//            ps.setString(4, mobile.getMobileName());
//            ps.setInt(5, mobile.getYearOfProduction());
//            ps.setInt(6, mobile.getQuantity());
//            ps.setBoolean(7, mobile.isNotSale());
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(Mobiles mobile) {
//        String sql = "UPDATE Mobiles SET description=?, price=?, mobileName=?, yearOfProduction=?, quantity=?, notSale=? WHERE mobileId=?";
//        try (Connection conn = new DBContext().getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, mobile.getDescription());
//            ps.setFloat(2, mobile.getPrice());
//            ps.setString(3, mobile.getMobileName());
//            ps.setInt(4, mobile.getYearOfProduction());
//            ps.setInt(5, mobile.getQuantity());
//            ps.setBoolean(6, mobile.isNotSale());
//            ps.setString(7, mobile.getMobileId());
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void delete(String mobileId) {
//        String sql="DELETE FROM Mobiles WHERE mobileId=?";
//        try (Connection conn = new DBContext().getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, mobileId);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}