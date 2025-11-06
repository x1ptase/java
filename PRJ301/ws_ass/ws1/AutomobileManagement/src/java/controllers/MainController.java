package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private final static String ERROR_PAGE="Error.jsp";
    private static final String LIST_PAGE="CarsList.jsp";
    
    private static final String VIEW_DETAILS_ACTION="ViewDetails"; // view 1 car
    private static final String CREATE_ACTION="CreateCars";
    private static final String UPDATE_ACTION="UpdateCars";
    
    
    private static final String VIEW_DETAILS_CONTROLLER="/ViewDetailsController";
    private static final String CREATE_CARS_CONTROLLER="/CreateCarsController";
    private static final String UPDATE_CARS_CONTROLLER="/UpdateCarsController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR_PAGE;
        
        try{
            String action=request.getParameter("action");
            if(action == null){
                url=LIST_PAGE;
            } else if(action.equals(VIEW_DETAILS_ACTION)){
                url=VIEW_DETAILS_CONTROLLER;
            } else if(action.equals(CREATE_ACTION)){
                url=CREATE_CARS_CONTROLLER;
            } else if(action.equals(UPDATE_ACTION)){
                url=UPDATE_CARS_CONTROLLER;
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
