package DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO{
    protected Connection conn;
    public User log;
        public Connection getConnection() throws ClassNotFoundException, SQLException{
            try{    
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url="jdbc:sqlserver://localhost:1433;databaseName=SampleDB";
                String username="sa"; 
                String password="12345";
                conn=DriverManager.getConnection(url, username, password);
            return conn;
            } catch(ClassNotFoundException | SQLException ex){
                throw ex;
            }
        }
   public User login(String userName, String password) throws Exception{
        User user=null;
        Connection cnn=null;
        PreparedStatement preStm=null;
        ResultSet rs=null;
        String lastName;
        boolean isAdmin;
        try{
            cnn=getConnection();
            String sql=
                    "select LastName, isAdmin from Registration where [UserName]=? and [Password]=?";
            preStm=cnn.prepareCall(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs=preStm.executeQuery();
            while(rs.next()){
                lastName=rs.getString(1);
                isAdmin=rs.getBoolean(2);
                user=new User(userName, password, lastName, isAdmin);
            } // end while
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
    } // end login
    
    public List<User> getUserList()throws Exception{
        String userName, password, lastName;
        boolean isAdmin;
        Connection cnn=null;
        PreparedStatement preStm=null;
        ResultSet rs=null;
        List<User> userList=new ArrayList();
        try{
            cnn=getConnection();
            String sql=
                    "select Username, Password, LastName, isAdmin from Registration";
            preStm=cnn.prepareStatement(sql);
            rs=preStm.executeQuery();
            while(rs.next()){
                userName=rs.getString(1);
                password=rs.getString(2);
                lastName=rs.getString(3);
                isAdmin=rs.getBoolean(4);
                User user=new User(userName, password, lastName, isAdmin);
                userList.add(user);
            } // end while
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
        if(userList.isEmpty()){
            return null;
        }
        return userList;
    } // end getUserList
   
    public boolean addUser(User user) throws Exception{
        PreparedStatement preStm=null;
        Connection cnn=null;
        try{
            cnn=getConnection();
            String sql="INSERT INTO [dbo].[Registration]\n" +
"           ([UserName]\n" +
"           ,[Password]\n" +
"           ,[LastName]\n" +
"           ,[isAdmin])\n" +
"     VALUES (?, ?, ?, ?)";
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
    } // end addUser
    
    public boolean updateUser(User user) throws Exception{
        PreparedStatement preStm=null;
        Connection cnn=null;
        try{
            cnn=getConnection();
            String sql="Update Registration Set Password=?, LastName=?, isAdmin=? Where UserName=?";
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
    } // end updateUser
    
    public boolean deleteUser(String userName) throws Exception{
        PreparedStatement preStm=null;
        Connection cnn=null;
        try{
            cnn=getConnection();
            String sql="delete Registration Where UserName=?";
            preStm=cnn.prepareStatement(sql);
            preStm.setString(1, userName);
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
    } // end deleteUser
} // end class