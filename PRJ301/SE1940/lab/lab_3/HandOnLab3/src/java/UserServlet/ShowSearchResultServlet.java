package UserServlet;

import DBUtils.User;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowSearchResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        out.println("<h3>Your search value is: " + (keyword != null ? keyword : "") + "</h3>");

        if (list == null || list.isEmpty()) {
            out.println("<h3>No record found!</h3>");
        } else {
            out.println("<table border='1'>");
            out.println("<tr><th>No.</th><th>Username</th><th>Password</th><th>Lastname</th><th>Role</th><th>Action</th></tr>");
            int no = 1;
            for (User u : list) {
                out.println("<tr>");
                out.println("<td>" + (no++) + "</td>");
                out.println("<td>" + u.getUsername() + "</td>");
                out.println("<td>" + u.getPassword() + "</td>");
                out.println("<td>" + u.getLastname() + "</td>");
                out.println("<td>" + u.isRole() + "</td>");
                out.println("<td><a href='RemoveServlet?ID=" + u.getId() + "'>Delete</a></td>"); // Use actual column name
                out.println("</tr>");
            }
            out.println("</table>");
        }
        out.println("<br><a href='Search.html'>Back</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}