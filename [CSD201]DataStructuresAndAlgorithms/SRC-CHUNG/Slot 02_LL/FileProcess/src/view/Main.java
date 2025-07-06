/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHAM KHAC VINH
 */
public class Main {

    public static void main(String[] args) {
        String fileName = "hocvietFile.txt";
        Student student1 = new Student(1, "Nguyen A");
        Student student2 = new Student(2, "Nguyen B");
        Student student3 = new Student(3, "Nguyen C");
        Student student4 = new Student(4, "Nguyen D");
        
        ArrayList<Student> listStudent = new ArrayList<>();
        listStudent.add(student1);
        listStudent.add(student2);
        listStudent.add(student3);
        listStudent.add(student4);
//        
//        writeFile(fileName, listStudent);
        
//        String[] mangChuoi = {"A", "B","C" ,"D"};
        
//        writeFile(fileName, mangChuoi);

            //readFromFile("hocvietFile.txt");
            writeFile("data.txt", listStudent);
    }
    
    public static void writeFile(String fileName, ArrayList<Student> listStudents){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            //lap tu gia tri dau tien den gia tri cuoi cung cua mang chuoi
            for (Student listStudent : listStudents) {
                bufferedWriter.write(listStudent.toString());
                bufferedWriter.newLine();
            }
            
            

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void readFromFile(String fileName) {
        File file = new File(fileName);
         ArrayList<Student> listStudents = new ArrayList<>();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.err.println("IO Exception phan file");
            }
        }
        Student student;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String lineString = "";
            while (true) {
                //doc du lieu cua 1 dong
                lineString = bufferedReader.readLine();
                if (lineString == null) {
                    break;
                }
                String[] txt = lineString.split("[||]+");
                //dong nay la doc duoc du lieu cua ID
                int id = Integer.parseInt(txt[0].trim());
                //dong nay la doc du lieu cua Name
                String name = txt[1].trim();
                
                //tao ra doi tuong student voi ID va name doc duoc cua dong` do
                student = new Student(id, name);
                
                //add doi tuong student do vao list
                listStudents.add(student);
                
            }
            
            for (Student student1 : listStudents) {
                System.out.println(student1.toString());
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.err.println("File Not Found Exception in function " + "readFromFile");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("IO Exception cua ham buffereader trong readFromFile");
        }

    }
    
    
}
