package UserServlet;

import DBUtils.UserDAO;
import DBUtils.User;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword");
            UserDAO dao = new UserDAO();
            try {
                List<User> list = dao.searchUser(keyword);
                request.setAttribute("USER_LIST", list);
                request.setAttribute("SEARCH_VALUE", keyword);
                RequestDispatcher rd = request.getRequestDispatcher("ShowSearchResultServlet");
                rd.forward(request, response);
            } finally {
                dao.close();
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}