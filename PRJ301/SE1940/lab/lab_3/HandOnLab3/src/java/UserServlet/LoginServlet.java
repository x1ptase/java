package UserServlet;

import DBUtils.UserDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String user = request.getParameter("username");
            String pass = request.getParameter("password");

            UserDAO dao = new UserDAO();
            if (dao.checkLogin(user, pass)) {
                RequestDispatcher rd = request.getRequestDispatcher("Search.html");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("Invalid.html");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
