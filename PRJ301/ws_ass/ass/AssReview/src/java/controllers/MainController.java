package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final String ERROR_PAGE="Error.jsp";
    private static final String LOGIN_PAGE="Login.jsp";
    
    private static final String LOGIN_ACTION="Login";
    private static final String LOGOUT_ACTION="Logout";

    private static final String LOGIN_CONTROLLER="LoginController";
    private static final String LOGOUT_CONTROLLER="LogoutController";
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR_PAGE;
        
        try{
            String action=request.getParameter("action"); 
            if(action == null){
                url=LOGIN_PAGE;
            } else if(action.equals(LOGIN_ACTION)){ 
                url=LOGIN_CONTROLLER;
            } else if(action.equals(LOGOUT_ACTION)){
                url=LOGOUT_CONTROLLER;
            } else{
                url=ERROR_PAGE;
            }
            
        } catch(Exception ex){
            log("Error at MainController: " + ex.getMessage());
        } finally{
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