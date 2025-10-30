/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author x1pta
 */
public class Users {
    private String userId;
    private String password;
    private String fullName;
    private int role;

    public Users() {
    }

    public Users(String userId, String password, String fullName, int role) {
        this.userId = userId;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("UserID:%s, Password:%s, FullName:%s, Role:%d",
                userId, password, fullName, role);
    }
    
    
}
