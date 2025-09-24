package UserServlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProcessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
        rd.forward(request, response);
    }
}
