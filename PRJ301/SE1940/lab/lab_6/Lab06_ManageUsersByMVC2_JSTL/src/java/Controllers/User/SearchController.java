/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Models.DAO.UserDAO;
import Models.DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x1pta
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {
    private final String searchPage="Search.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url, searchValue;
        PrintWriter out=response.getWriter();
        url=searchPage;
        
        try {
            searchValue = request.getParameter("txtSearchValue");
            if (!searchValue.isEmpty()) {
                UserDAO userDao = new UserDAO();
                List<User> userList = userDao.searchUserByLastName(searchValue);
                request.setAttribute("SearchResult", userList);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
