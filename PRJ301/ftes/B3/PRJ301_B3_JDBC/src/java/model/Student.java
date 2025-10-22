/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author x1pta
 */
public class Student {
    private String studentID, fullname, gender, dob, email, phone;

    public Student() {
    }

    public Student(String studentID, String fullname, String gender, String dob, String email, String phone) {
        this.studentID = studentID;
        this.fullname = fullname;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" + "studentID=" + studentID + ", fullname=" + fullname + ", gender=" + gender + ", dob=" + dob + ", email=" + email + ", phone=" + phone + '}';
    }
    
    

}