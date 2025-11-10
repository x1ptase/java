/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.model.UserDao;
import pe.model.UserDto;

/**
 *
 * @author x1pta
 */
public class LoginController extends HttpServlet {
    
    private static final String LOGIN_PAGE="login.jsp";
    private static final String WELCOME_PAGE="welcome.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID=request.getParameter("txtUserID");
        String password=request.getParameter("txtPassword");
        UserDao dao=new UserDao();
        UserDto user=null;
        
        try{
            user=dao.checkLogin(userID, password);
            if(user == null){
                request.setAttribute("msg", "Invalid user or password");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            }
            
            HttpSession session=request.getSession();
            session.setAttribute("user", user);
            request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
        } catch(Exception ex){
            log("Error at LoginController: " + ex.toString());
        } finally{
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}