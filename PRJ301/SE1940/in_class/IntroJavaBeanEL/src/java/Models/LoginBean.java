/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author x1pta
 */
public class LoginBean implements Serializable {
    private String name;
    private String password;

    public LoginBean() {
    }

    public LoginBean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Name:%s, Password:%s", name, password);
    }
    
    
}
