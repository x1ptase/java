/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Models;

import ProcessBeans.UserBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author x1pta
 */
public class UserDAO {
    public static Connection getConnection() throws Exception{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString="jdbc:sqlserver://localhost:1433;databaseName=SampleDB";
            Connection cnn=DriverManager.getConnection(connectionString, "sa", "12345");
            return cnn;
        } catch(ClassNotFoundException | SQLException ex){
            throw ex;
        }
    }
    
    public UserBean login(String userName, String password) throws Exception{
        UserBean user=null;
        Connection cnn=null;
        PreparedStatement preStm=null;
        ResultSet rs=null;
        String lastName;
        boolean isAdmin;
        
        try{
            cnn=getConnection();
            String sql=
                    "SELECT LastName, isAdmin from Registration WHERE [UserName]=? AND [Password]=?";
            preStm=cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs=preStm.executeQuery();
            while(rs.next()){
                lastName=rs.getString(1);
                isAdmin=rs.getBoolean(2);
                user=new UserBean(userName, password, lastName, isAdmin);
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
}
