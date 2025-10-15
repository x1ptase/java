/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.UserBean;

/**
 *
 * @author x1pta
 */
public class UserDAO {
    private static List<UserBean> userList=new ArrayList();
    public UserDAO(){
        UserBean david=new UserBean("U001", "111", "David");
        UserBean tom=new UserBean("U002", "222", "Tom");
        UserBean mark=new UserBean("U003", "333", "Mark");
        Collections.addAll(userList, david, tom, mark);
    }
    
    public UserBean checkLogin(String userName, String password){
        UserBean user=null;
        user=userList.stream().
                filter(u->u.getUserName().equals(userName) &&
                        u.getPassword().equals(password))
                .findAny().orElse(null);
        return user;
    }
}
