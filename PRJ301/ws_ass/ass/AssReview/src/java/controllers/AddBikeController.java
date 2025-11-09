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

    private static final String ADD_BIKE_PAGE = "addBike.jsp";
    private static final String BIKE_LIST_PAGE = "bikeList.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/" + LOGIN_PAGE);
            return;
        }
        
        tbAccountDTO account = (tbAccountDTO) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/" + LOGIN_PAGE);
            return;
        }
        
        try {
            String bikeID = request.getParameter("txtBikeID");
            String bikeName = request.getParameter("txtBikeName");
            String quantityStr = request.getParameter("txtQuantity");
            String model = request.getParameter("txtModel");
            
            // Validate input
            if (bikeID == null || bikeID.trim().isEmpty() ||
                bikeName == null || bikeName.trim().isEmpty() ||
                quantityStr == null || quantityStr.trim().isEmpty() ||
                model == null || model.trim().isEmpty()) {
                request.setAttribute("error", "All fields are required!");
                request.getRequestDispatcher(ADD_BIKE_PAGE).forward(request, response);
                return;
            }
            
            int quantity;
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity < 0) {
                    request.setAttribute("error", "Quantity must be a positive number!");
                    request.getRequestDispatcher(ADD_BIKE_PAGE).forward(request, response);
                    return;
                }
            } catch (NumberFormatException ex) {
                request.setAttribute("error", "Quantity must be a valid number!");
                request.getRequestDispatcher(ADD_BIKE_PAGE).forward(request, response);
                return;
            }
            
            tbBikeDAO dao = new tbBikeDAO();
            
            // Check if bike ID already exists
            if (dao.checkBikeIDExists(bikeID.trim())) {
                request.setAttribute("error", "Bike ID already exists!");
                request.getRequestDispatcher(ADD_BIKE_PAGE).forward(request, response);
                return;
            }
            
            // Create bike DTO and insert
            tbBikeDTO bike = new tbBikeDTO(
                bikeID.trim(),
                bikeName.trim(),
                quantity,
                model.trim()
            );
            
            boolean result = dao.insertBike(bike);
            
            if (result) {
                request.setAttribute("success", "Bike added successfully!");
            } else {
                request.setAttribute("error", "Failed to add bike. Please try again!");
            }
            
        } catch (Exception ex) {
            log("Error at AddBikeController: " + ex.getMessage());
            ex.printStackTrace();
            request.setAttribute("error", "An error occurred: " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(ADD_BIKE_PAGE).forward(request, response);
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

