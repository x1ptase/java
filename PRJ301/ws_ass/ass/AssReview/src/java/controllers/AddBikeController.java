package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.tbBikeDAO;
import models.tbBikeDTO;
import models.tbAccountDTO;

public class AddBikeController extends HttpServlet {

    private static final String ADD_BIKE_PAGE="addBike.jsp";
    private static final String LOGIN_PAGE="login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Check authentication
        if(!checkAuth(request, response)) return;
        
        try{
            // Get and validate parameters
            String bikeID=getParam(request, "txtBikeID");
            String bikeName=getParam(request, "txtBikeName");
            String quantityStr=getParam(request, "txtQuantity");
            String model=getParam(request, "txtModel");
            
            if(bikeID == null || bikeName == null || quantityStr == null || model == null){
                forwardError(request, response, "All fields are required!");
                return;
            }
            
            // Validate quantity
            int quantity;
            try{
                quantity=Integer.parseInt(quantityStr);
                if(quantity < 0){
                    forwardError(request, response, "Quantity must be a positive number!");
                    return;
                }
            } catch(NumberFormatException ex){
                forwardError(request, response, "Quantity must be a valid number!");
                return;
            }
            
            // Check duplicate ID and insert
            tbBikeDAO dao=new tbBikeDAO();
            if(dao.checkBikeIDExists(bikeID)){
                forwardError(request, response, "Bike ID already exists!");
                return;
            }
            
            boolean result=dao.insertBike(new tbBikeDTO(bikeID, bikeName, quantity, model));
            request.setAttribute(result ? "success" : "error", 
                result ? "Bike added successfully!" : "Failed to add bike. Please try again!");
            
        } catch(Exception ex){
            log("Error at AddBikeController: " + ex.getMessage());
            ex.printStackTrace();
            forwardError(request, response, "An error occurred: " + ex.getMessage());
        } finally{
            request.getRequestDispatcher(ADD_BIKE_PAGE).forward(request, response);
        }
    }
    
    private boolean checkAuth(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("account") == null){
            response.sendRedirect(request.getContextPath() + "/" + LOGIN_PAGE);
            return false;
        }
        return true;
    }
    
    private String getParam(HttpServletRequest request, String paramName){
        String value = request.getParameter(paramName);
        return (value != null && !value.trim().isEmpty()) ? value.trim() : null;
    }
    
    private void forwardError(HttpServletRequest request, HttpServletResponse response, String errorMsg) 
            throws ServletException, IOException{
        request.setAttribute("error", errorMsg);
        request.getRequestDispatcher(ADD_BIKE_PAGE).forward(request, response);
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

