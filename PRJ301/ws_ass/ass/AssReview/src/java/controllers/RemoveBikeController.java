package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.tbBikeDAO;
import models.tbAccountDTO;

public class RemoveBikeController extends HttpServlet {

    private static final String SEARCH_BIKE_CONTROLLER = "SearchBikeController";
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
            String bikeID = request.getParameter("bikeID");
            String keyword = request.getParameter("keyword");
            
            if (bikeID == null || bikeID.trim().isEmpty()) {
                // If no bike ID, redirect back to search with keyword
                if (keyword != null && !keyword.trim().isEmpty()) {
                    response.sendRedirect(SEARCH_BIKE_CONTROLLER + "?keyword=" + java.net.URLEncoder.encode(keyword, "UTF-8"));
                } else {
                    response.sendRedirect(SEARCH_BIKE_CONTROLLER);
                }
                return;
            }
            
            tbBikeDAO dao = new tbBikeDAO();
            boolean result = dao.deleteBike(bikeID.trim());
            
            if (result) {
                // Success - redirect back to search with keyword to refresh grid
                if (keyword != null && !keyword.trim().isEmpty()) {
                    response.sendRedirect(SEARCH_BIKE_CONTROLLER + "?keyword=" + java.net.URLEncoder.encode(keyword, "UTF-8"));
                } else {
                    response.sendRedirect(SEARCH_BIKE_CONTROLLER);
                }
            } else {
                // Failed - still redirect but could set error message
                if (keyword != null && !keyword.trim().isEmpty()) {
                    response.sendRedirect(SEARCH_BIKE_CONTROLLER + "?keyword=" + java.net.URLEncoder.encode(keyword, "UTF-8"));
                } else {
                    response.sendRedirect(SEARCH_BIKE_CONTROLLER);
                }
            }
            
        } catch (Exception ex) {
            log("Error at RemoveBikeController: " + ex.getMessage());
            ex.printStackTrace();
            // On error, redirect back to search
            String keyword = request.getParameter("keyword");
            if (keyword != null && !keyword.trim().isEmpty()) {
                response.sendRedirect(SEARCH_BIKE_CONTROLLER + "?keyword=" + java.net.URLEncoder.encode(keyword, "UTF-8"));
            } else {
                response.sendRedirect(SEARCH_BIKE_CONTROLLER);
            }
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

