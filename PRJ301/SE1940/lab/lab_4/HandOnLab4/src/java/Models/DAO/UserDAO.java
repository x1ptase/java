package Models.DAO;

import Models.DTO.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // <editor-fold defaultstate="collapsed" desc="getConnection Method">
    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=SampleDB";
            Connection cnn = DriverManager.getConnection(connectionString, "sa", "12345");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="login Method">
    public User login(String userName, String password) throws Exception {
        User user = null;
        Connection cnn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String lastName;
        boolean isAdmin;

        try {
            cnn = getConnection();
            String sql = "select LastName, isAdmin from Registration where [UserName]=? and [Password]=?";
            preStmt = cnn.prepareStatement(sql);
            preStmt.setString(1, userName);
            preStmt.setString(2, password);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                lastName = rs.getString(1);
                isAdmin = rs.getBoolean(2);
                user = new User(userName, password, lastName, isAdmin);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStmt != null) {
                preStmt.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return user;
    }
    // </editor-fold>

    public User getUserByUserName(String userName) throws Exception{
        User user=null;
        Connection cnn=null;
        PreparedStatement preStm=null;
        ResultSet rs=null;
        String lastName, password;
        boolean isAdmin;
        try{
            cnn=getConnection();
        
            String sql=
                    "select Password, LastName, isAdmin from Resgistration where [UserName]=?";
            preStm=cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            rs=preStm.executeQuery();
            
            while(rs.next()){
                password=rs.getString(1);
                lastName=rs.getString(2);
                isAdmin=rs.getBoolean(3);
                user=new User(userName, password, lastName, isAdmin);
            }
        } catch(Exception ex){
            throw ex;
        } finally{
            if(rs != null){
                rs.close();
            }
            if(preStm != null){
                preStm.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
        return user;
    }
    
    // <editor-fold defaultstate="collapsed" desc="searchUserByLastName Method">
    public List<User> searchUserByLastName(String searchValue) throws Exception {
        String userName, password, lastName;
        boolean isAdmin;
        Connection cnn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();

        try {
            cnn = getConnection();
            String sql = "select UserName, Password, LastName, isAdmin from Registration " +
                        "where LastName like ?";
            preStmt = cnn.prepareStatement(sql);
            String searchPattern = "%" + searchValue + "%";
            preStmt.setString(1, searchPattern);
            System.out.println("SQL Query: " + sql);
            System.out.println("Search Pattern: " + searchPattern);
            rs = preStmt.executeQuery();
            while (rs.next()) {
                userName = rs.getString(1);
                password = rs.getString(2);
                lastName = rs.getString(3);
                isAdmin = rs.getBoolean(4);
                System.out.println("Found user: " + userName + ", " + lastName);
                userList.add(new User(userName, password, lastName, isAdmin));
            }
            System.out.println("Total users found: " + userList.size());
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStmt != null) {
                preStmt.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return userList;
    }
    // </editor-fold>

    public boolean addUser(User user) throws Exception{
        PreparedStatement preStm=null;
        Connection cnn=null;
        
        try{
            cnn=getConnection();
            String sql="INSERT Registration VALUES(?, ?, ?, ?)";
            preStm=cnn.prepareStatement(sql);
            preStm.setString(1, user.getUserName());
            preStm.setString(2, user.getPassword());
            preStm.setString(4, user.getLastName());
            preStm.setBoolean(4, user.isIsAdmin());
            return preStm.executeUpdate() > 0;
        } catch(Exception ex){
            throw ex;
        } finally{
            if(preStm != null){
                preStm.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="deleteUser Method">
    public boolean deleteUser(String userName) throws Exception {
        PreparedStatement preStmt = null;
        Connection cnn = null;

        try {
            cnn = getConnection();
            String sql = "delete from Registration where UserName=?";
            preStmt = cnn.prepareStatement(sql);
            preStmt.setString(1, userName);
            return preStmt.executeUpdate() > 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStmt != null) {
                preStmt.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
    // </editor-fold>
}
