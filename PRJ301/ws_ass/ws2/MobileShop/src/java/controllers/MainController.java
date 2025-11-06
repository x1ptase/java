package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {
    
    private static final String ERROR_PAGE="Error.jsp";
    private static final String LOGIN_PAGE="Login.jsp";
    
    private static final String LOGIN_ACTION="Login";
    private static final String LOGOUT_ACTION="Logout";
    
    private static final String VIEW_MOBILES_ACTION="ViewMobile";
    private static final String CREATE_MOBILES_ACTION="CreateMobile";
    private static final String UPDATE_MOBILES_ACTION="UpdateMobile";
    private static final String DELETE_MOBILES_ACTION="DeleteMobile";
    
    private static final String LOGIN_CONTROLLER="LoginController";
    private static final String LOGOUT_CONTROLLER="LogoutController";
    
    private static final String VIEW_CONTROLLER="ViewController";
    private static final String CREATE_MOBILES_CONTROLLER="CreateMobilesController";
    private static final String UPDATE_MOBILES_CONTROLLER="UpdateMobilesController";
    private static final String DELETE_MOBILES_CONTROLLER="DeleteMobilesController";

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
            } else if(action.equals(VIEW_MOBILES_ACTION)){
                url=VIEW_CONTROLLER;
            } else if(action.equals(CREATE_MOBILES_ACTION)){
                url=CREATE_MOBILES_CONTROLLER;
            } else if(action.equals(UPDATE_MOBILES_ACTION)){
                url=UPDATE_MOBILES_CONTROLLER;
            } else if(action.equals(DELETE_MOBILES_ACTION)){
                url=DELETE_MOBILES_CONTROLLER;
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
