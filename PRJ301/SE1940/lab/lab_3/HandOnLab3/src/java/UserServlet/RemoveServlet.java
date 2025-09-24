package UserServlet;

import DBUtils.UserDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RemoveServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("ID")); // Use actual column name
            UserDAO dao = new UserDAO();
            try {
                dao.removeUser(id);
            } finally {
                dao.close();
            }

            RequestDispatcher rd = request.getRequestDispatcher("Search.html");
            rd.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}