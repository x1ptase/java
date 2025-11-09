/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.model.AccountDto;
import pe.model.RoomForRentDao;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class DeleteController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String SEARCH_CONTROLLER = "SearchController";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Check if user is authenticated
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/" + LOGIN_PAGE);
            return;
        }
        
        String roomIdStr = request.getParameter("roomId");
        String searchCriteria = request.getParameter("searchCriteria");
        RoomForRentDao dao = new RoomForRentDao();
        
        try {
            if (roomIdStr != null && !roomIdStr.trim().isEmpty()) {
                int roomId = Integer.parseInt(roomIdStr);
                dao.deleteRoom(roomId);
            }
            
            // Redirect back to search with the same criteria to refresh the data grid
            if (searchCriteria != null && !searchCriteria.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/" + SEARCH_CONTROLLER + "?txtSearch=" + 
                        java.net.URLEncoder.encode(searchCriteria, "UTF-8"));
            } else {
                response.sendRedirect(request.getContextPath() + "/" + SEARCH_CONTROLLER);
            }
        } catch (SQLException | NumberFormatException ex) {
            log("Error at DeleteController: " + ex.toString());
            response.sendRedirect(request.getContextPath() + "/" + SEARCH_CONTROLLER);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

