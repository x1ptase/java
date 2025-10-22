/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.Student;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author x1pta
 */
public class StudentDAO extends DBContext{
    
    // Task 1: Anh muon lay du lieu tu database len hien thi
    
    // B1. Tao 1 cau lenh SQL thuc hien chuc nang ma anh mong muon
    private final String GET_STUDENT_SQL="SELECT * FROM Student";
    private final String REMOVE_STUDENT_BY_ID_SQL="DELETE FROM [dbo].[Student] WHERE StudentID=?";
    
    
    
    
    // B2. Tao ham de tien hanh chay cau lenh SQL vua tao va lay ket qua
    public List<Student> getListStudent(){
        List<Student> res=new ArrayList<>(); // thung kq
        try{
            PreparedStatement preStm=cnn.prepareStatement(GET_STUDENT_SQL);
            ResultSet rs=preStm.executeQuery(); // tra ve tep kq luu vao rs
            
            while(rs.next()){ // duyet qua tung hang
                Student std=new Student(rs.getString("StudentID"),
                                            rs.getString("Fullname"),
                                            rs.getString("Gender"),
                                            rs.getString("DOB"),
                                            rs.getString("Email"),
                                            rs.getString("Phone"));
                res.add(std);
            }
        } catch(Exception ex){
            return null;
        }
        return res;
    }
    
    public boolean removeStudentByID(String studentID){
        try{
            PreparedStatement preStm=cnn.prepareStatement(REMOVE_STUDENT_BY_ID_SQL);
            preStm.setString(1, studentID); 
            int n=preStm.executeUpdate(); // tra ve so dong thay doi kieu integer
            if(n != 0){
                return true;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
