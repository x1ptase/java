/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import containt.MessageContaint;
import containt.NameAttributeContaint;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author x1pta
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/process"})
public class RegisterController extends HttpServlet {
    // param name
    private final String FULL_NAME="txtFullname";
    private final String USERNAME="txtUsername";        
    private final String PASSWORD="txtPassword";
    private final String EMAIL="txtEmail";

    // link
    private final String PROFILE_PAGE="profile.jsp";
    private final String REGISTER_PAGE="register.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname=request.getParameter(FULL_NAME);
        String username=request.getParameter(USERNAME);
        String password=request.getParameter(PASSWORD);
        String email=request.getParameter(EMAIL);

        if(fullname.isEmpty() || username.isEmpty()
                || password.isEmpty() || email.isEmpty()){
            request.setAttribute(NameAttributeContaint.ERROR_ATRIBUTE, MessageContaint.ERROR_REGISTER);
            request.getRequestDispatcher(REGISTER_PAGE).forward(request, response);
        } else{
            User usr=new User(fullname, username, password, email);
            request.setAttribute(NameAttributeContaint.USER_ATTRIBUTE, usr);
            request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
        }   
    }
}
