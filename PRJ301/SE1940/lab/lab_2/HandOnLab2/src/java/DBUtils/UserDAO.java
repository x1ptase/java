/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author x1pta
 */
public class UserDAO {
    // <editor-fold defaultstate="collapsed" desc="getConnection Method">
    public Connection getConnection() throws Exception{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url=
                    "jdbc:sqlserver://localhost:1433;databaseName=SampleDB";
            Connection cnn=DriverManager.getConnection(url, "sa", "12345");
            return cnn;
        } catch(ClassNotFoundException | SQLException ex){
            throw ex;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="login Method">
    public User login(String userName, String password) throws Exception {
        User user=null;
        Connection cnn=null;
        PreparedStatement preStmt=null;
        ResultSet rs=null;
        String lastName;
        boolean isAdmin;

        try{
            cnn=getConnection();
            String sql="SELECT LastName, isAdmin FROM Registration WHERE [UserName]=? AND [Password]=?";
            preStmt=cnn.prepareStatement(sql);
            preStmt.setString(1, userName);
            preStmt.setString(2, password);
            rs=preStmt.executeQuery();
            while(rs.next()){
                lastName=rs.getString(1);
                isAdmin=rs.getBoolean(2);
                user=new User(userName, password, lastName, isAdmin);
            }
        } catch(Exception ex){
            throw ex;
        } finally{
            if(rs != null){
                rs.close();
            }
            if(preStmt != null){
                preStmt.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
        return user;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getUserList method">
    public List<User> getUserList() throws Exception{
        String userName, password, lastName;
        boolean isAdmin;
        Connection cnn=null;
        PreparedStatement preStm=null;
        ResultSet rs=null;
        List<User> userList=new ArrayList();
        
        try{
            cnn=getConnection();
            String sql=
                    "SELECT UserName, Password, LastName, isAdmin FROM Registration";
            preStm=cnn.prepareStatement(sql);
            rs=preStm.executeQuery();
            while(rs.next()){
                userName=rs.getString(1);
                password=rs.getString(2);
                lastName=rs.getString(3);
                isAdmin=rs.getBoolean(4);
                User user=new User(userName, password, lastName, isAdmin);
                userList.add(user);
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
        return userList;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="addUser Method">
    public boolean addUser(User user) throws Exception{
        PreparedStatement preStm=null;
        Connection cnn=null;
        
        try{
            cnn=getConnection();
            String sql="INSERT Registration VALUES(?,?,?,?)";
            preStm=cnn.prepareStatement(sql);
            preStm.setString(1, user.getUserName());
            preStm.setString(2, user.getPassword());
            preStm.setString(3, user.getLastName());
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="updateUser Method">
    public boolean updateUser(User user) throws Exception{
        PreparedStatement preStm=null;
        Connection cnn=null;
        
        try{
            cnn=getConnection();
            String sql=
                    "UPDATE Registration SET Password=?, LastName=?, isAdmin=? WHERE UserName=?";
            preStm=cnn.prepareStatement(sql);
            preStm.setString(1, user.getPassword());
            preStm.setString(2, user.getLastName());
            preStm.setBoolean(3, user.isIsAdmin());
            preStm.setString(4, user.getUserName());
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="deleteUser Method">
    public boolean deleteUser(String userName) throws Exception {
        PreparedStatement preStmt=null;
        Connection cnn=null;

        try{
            cnn=getConnection();
            String sql="DELETE FROM Registration WHERE UserName=?";
            preStmt=cnn.prepareStatement(sql);
            preStmt.setString(1, userName);
            return preStmt.executeUpdate() > 0;
        } catch(Exception ex){
            throw ex;
        } finally {
            if(preStmt != null){
                preStmt.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
    }
    // </editor-fold>
}
