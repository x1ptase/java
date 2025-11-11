/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class MainController extends HttpServlet {

    private static final String WELCOME="login.jsp";
    
    private static final String LOGIN_ACTION="login";
    private static final String LOGOUT_ACTION="logout";
   
    private static final String LOGIN_CONTROLLER="LoginController";
    private static final String LOGOUT_CONTROLLER="LogoutController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= WELCOME;
        try {
            String action= request.getParameter("action");
            //-----            your code here   --------------------------------
            if(action == null){
                url=WELCOME;
            } else if(action.equals(LOGIN_ACTION)){ 
                url=LOGIN_CONTROLLER;
            } else if(action.equals(LOGOUT_ACTION)){
                url=LOGOUT_CONTROLLER;
            }
            //-----            your code here   --------------------------------
        } catch (Exception e) {
            log("error at MainController: "+ e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
