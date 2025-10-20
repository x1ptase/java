/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author x1pta
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {
    private final String loginPage = "Login.jsp";
    private static final String searchController = "SearchController";
    private static final String userDetailsController = "UserDetailsController";
    private static final String createController = "CreateController";
    private static final String deleteController = "DeleteController";
    private static final String updateController = "UpdateController";

    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = null;
        try {
            HttpSession session = request.getSession();
            boolean isLoggedIn = (session.getAttribute("userLoggedIn") != null);

            if (action.equalsIgnoreCase("Create")) {
                url = createController;
            } else if (isLoggedIn) {
                if (action.equalsIgnoreCase("Delete")) {
                    url = deleteController;
                } else if (action.equalsIgnoreCase("Update")) {
                    url = updateController;
                } else if (action.equalsIgnoreCase("Search")) {
                    url = searchController;
                } else if (action.equalsIgnoreCase("Details")) {
                    url = userDetailsController;
                }
            } else {
                url = loginPage;
            }
        } finally {
            if (url != null) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
        }
    } //end processRequest
        

   
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
