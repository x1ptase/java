/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.model.AccountDto;
import pe.model.RoomForRentDao;
import pe.model.RoomForRentDto;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class SearchController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String SEARCH_PAGE = "search.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Check if user is authenticated
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/" + LOGIN_PAGE);
            return;
        }
        
        String searchCriteria = request.getParameter("txtSearch");
        RoomForRentDao dao = new RoomForRentDao();
        List<RoomForRentDto> list = null;
        
        try {
            // Only process search if txtSearch parameter exists (user clicked Search button)
            if (searchCriteria != null) {
                if (!searchCriteria.trim().isEmpty()) {
                    // Search with criteria
                    list = dao.searchByLocation(searchCriteria);
                    request.setAttribute("searchResults", list);
                    request.setAttribute("searchCriteria", searchCriteria);
                } else {
                    // If search button clicked but no data entered, show empty list (no results)
                    request.setAttribute("searchResults", new java.util.ArrayList<RoomForRentDto>());
                    request.setAttribute("searchCriteria", "");
                }
            }
            // If txtSearch parameter is null, don't set searchResults (table won't be displayed)
        } catch (SQLException ex) {
            log("Error at SearchController: " + ex.toString());
        }
        
        request.getRequestDispatcher(SEARCH_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

