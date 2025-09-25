/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.Entities.User;

/**
 *
 * @author x1pta
 */
public class LoginDAO {
    public boolean validate(User user){
        return user.getName().equals("admin") &&
                user.getPassword().equals("123");
    }
}
