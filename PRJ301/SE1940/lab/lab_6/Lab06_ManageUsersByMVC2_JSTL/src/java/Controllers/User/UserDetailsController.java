/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Models.DAO.UserDAO;
import Models.DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UserDetailsController", urlPatterns = {"/UserDetailsController"})
public class UserDetailsController extends HttpServlet {
    private final String userDetailsPage="UserDetails.jsp";
    private final String userController = "UserController";
    protected void processRequest (HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = userDetailsPage;
        User user;
        try {
            String userName = request.getParameter("userName");
            if (userName == null || userName.isEmpty()) {
                userName = request.getParameter("txtUserName");
            }
            
            UserDAO dao = new UserDAO();
            if (userName != null && !userName.isEmpty()) {
                user = dao.getUserByUserName(userName);
                if (user != null) {
                    request.setAttribute("userDetails", user);
                }
                else {
                    url = userController + "?action=Search";
                }
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
