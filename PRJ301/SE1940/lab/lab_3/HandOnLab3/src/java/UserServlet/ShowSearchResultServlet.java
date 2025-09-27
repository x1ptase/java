package UserServlet;

import DBUtils.User;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowSearchResultServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<User> list = (List<User>) request.getAttribute("USER_LIST");
        String keyword = (String) request.getAttribute("SEARCH_VALUE");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Search Result</title></head>");
        out.println("<body>");
        out.println("<h1>Search Result</h1>");
        out.println("Your search value is: " + (keyword != null ? keyword : ""));

        if (list == null || list.isEmpty()) {
            out.println("<h3>No record found!</h3>");
        } else {
            out.println("<table border='1'>");
            out.println("<tr><th>No.</th><th>Username</th><th>Password</th><th>Lastname</th><th>Role</th></tr>");
            int no = 1;
            for (User u : list) {
                out.println("<tr>");
                out.println("<td>" + (no++) + "</td>");
                out.println("<td>" + u.getUsername() + "</td>");
                out.println("<td>" + u.getPassword() + "</td>");
                out.println("<td>" + u.getLastname() + "</td>");
                out.println("<td>" + u.isIsAdmin() + "</td>");
                out.println("<td><a href='RemoveServlet?ID=" + u.getUsername() + "'>Delete</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
        out.println("<br><a href='Search.html'>Back</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
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