/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PersonDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import model.Person;

/**
 *
 * @author x1pta
 */
@WebServlet(name="ListServlet", urlPatterns={"/list"})
public class ListServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        // Lấy data
        PersonDAO personDAO=new PersonDAO();
        ArrayList<Person> persons;
        try{
            persons=personDAO.getAllPersons();
            request.setAttribute("persons", persons);
        
            RequestDispatcher dispatcher=request.getRequestDispatcher("list.jsp");
            dispatcher.forward(request, response);
        } catch(SQLException ex){
            Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
