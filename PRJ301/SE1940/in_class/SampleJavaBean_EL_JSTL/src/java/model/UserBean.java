/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

import dao.UserDAO;
import java.io.Serializable;

/**
 *
 * @author x1pta
 */
public class UserBean implements Serializable{
    private String userName;
    private String password;
    private String fullName;

    public UserBean() {
    }

    public UserBean(String userName, String password, String fullName) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return String.format("UserName:%s, Password:%s, FullName:%s",
                userName, password, fullName);
    }
    
    public UserBean login(){
        UserDAO userDAO=new UserDAO();
        return userDAO.checkLogin(userName, password);
    }
}
