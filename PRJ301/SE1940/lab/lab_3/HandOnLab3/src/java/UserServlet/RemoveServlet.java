package UserServlet;

import DBUtils.UserDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RemoveServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("ID");
            UserDAO dao = new UserDAO();
            dao.deleteUser(username);

            RequestDispatcher rd = request.getRequestDispatcher("Search.html");
            rd.forward(request, response);
        } catch (Exception e) {
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