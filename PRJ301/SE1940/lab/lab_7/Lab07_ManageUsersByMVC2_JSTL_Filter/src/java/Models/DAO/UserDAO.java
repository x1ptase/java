package Models.DAO;

import Models.DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource; 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException; 

public abstract class UserDAO implements IUserDAO {
    public static Connection getConnection() throws Exception {
        Connection cnn;
        try {
            Context currentContext=new InitialContext();
            Context tomcatContext=(Context) currentContext.lookup("java:comp/env");
            DataSource ds=(DataSource) tomcatContext.lookup("SampleDB");
            cnn=ds.getConnection();
            return cnn;
            
        } catch(SQLException | NamingException ex){
            throw ex; 
        }
    }
    
    public User login(String userName, String password) throws Exception {
        User user=null;
        Connection cnn=null;
        PreparedStatement preStm=null;
        ResultSet rs=null;
        String lastName;
        boolean isAdmin;

        try{
            cnn=getConnection();
            String sql= 
                "SELECT LastName, isAdmin FROM Registration WHERE [UserName]=? AND [Password]=?";
            preStm=cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs=preStm.executeQuery();

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
            if(preStm != null){
                preStm.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
        return user; 
    }
    
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
                    "SELECT Password, LastName, isAdmin FROM Resgistration WHERE [UserName]=?";
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
    
    public List<User> searchUserByLastName(String searchValue) throws Exception {
        String userName, password, lastName;
        boolean isAdmin;
        Connection cnn=null;
        PreparedStatement preStmt=null;
        ResultSet rs=null;
        List<User> userList=new ArrayList<>();

        try {
            cnn=getConnection();
            String sql="SELECT UserName, Password, LastName, isAdmin FROM Registration " +
                        "WHERE LastName LIKE ?";
            preStmt=cnn.prepareStatement(sql);
            preStmt.setString(1, "%" + searchValue + "%");
            rs=preStmt.executeQuery();
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
            if(preStmt != null){
                preStmt.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
        if(userList.isEmpty()){
            return null;
        }
        return userList;
    }
    
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
    
    public boolean deleteUser(String userName) throws Exception {
        PreparedStatement preStmt=null;
        Connection cnn=null;

        try {
            cnn=getConnection();
            String sql="DELETE FROM Registration WHERE UserName=?";
            preStmt=cnn.prepareStatement(sql);
            preStmt.setString(1, userName);
            return preStmt.executeUpdate() > 0;
        } catch(Exception ex){
            throw ex;
        } finally{
            if(preStmt != null){
                preStmt.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
    }
    
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
}