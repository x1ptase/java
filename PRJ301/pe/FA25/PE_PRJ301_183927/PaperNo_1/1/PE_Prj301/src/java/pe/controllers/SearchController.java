/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.model.UniversityDao;
import pe.model.UniversityDto;

/**
 *
 * @author x1pta
 */
public class SearchController extends HttpServlet {
    private static final String LOGIN_PAGE="login.jsp";
    private static final String SEARCH_PAGE="search.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect(request.getContextPath() + "/" + LOGIN_PAGE);
            return;
        }
        
        String searchCriteria=request.getParameter("txtSearch");
        UniversityDao dao=new UniversityDao();
        List<UniversityDto> list=null;
        
        try {
            if(searchCriteria != null){
                if(!searchCriteria.trim().isEmpty()){  
                    list=dao.searchByUniversity(searchCriteria);
                    request.setAttribute("searchResults", list);
                    request.setAttribute("searchCriteria", searchCriteria);
                } else {
                    request.setAttribute("searchResults", new java.util.ArrayList<UniversityDto>());
                    request.setAttribute("searchCriteria", "");
                }
            }
        } catch(Exception ex){
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
