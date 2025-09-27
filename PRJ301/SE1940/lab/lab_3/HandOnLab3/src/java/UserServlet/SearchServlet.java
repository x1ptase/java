package UserServlet;

import DBUtils.UserDAO;
import DBUtils.User;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class SearchServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword");
            System.out.println("Search keyword: " + keyword);
            UserDAO dao = new UserDAO();
            List<User> list = dao.searchUserByLastName(keyword);
            System.out.println("Search result count: " + (list != null ? list.size() : "null"));
            request.setAttribute("USER_LIST", list);
            request.setAttribute("SEARCH_VALUE", keyword);
            RequestDispatcher rd = request.getRequestDispatcher("ShowSearchResultServlet");
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Search error: " + e.getMessage());
            throw new ServletException(e);
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