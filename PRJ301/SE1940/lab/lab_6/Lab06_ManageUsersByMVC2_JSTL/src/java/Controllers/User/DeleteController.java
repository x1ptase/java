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
import javax.servlet.http.HttpSession;

@WebServlet(name = "DeleteController", urlPatterns = {"/DeleteController"})
public class DeleteController extends HttpServlet {
    private final String userController="UserController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = userController;
        String message = null;
        try {
            UserDAO userDao = new UserDAO();
            HttpSession session = request.getSession();
            User userLoggedIn = (User) session.getAttribute("userLoggedIn");
            String userName = request.getParameter("userName");
            String searchValue = request.getParameter("txtSearchValue");

            if (userName.equals(userLoggedIn.getUserName())) {
                message = "<b style='color: red'>This user logged in, can not delete.</b>";
            } else {
                if (!userName.isEmpty()) {
                    if (userDao.deleteUser(userName) == true) {
                        message = "<b style='color: green'>The user has bean deleted successfully.</b>";
                    } else {
                        message = "<b style='color: red'>Something went wrong.</b>";
                    }
                }
            }

            url = userController + "?action=Search&txtSearchValue=" + searchValue;

        } catch (Exception ex) {
            log(ex.getMessage());
        } finally {
            request.setAttribute("message", message);
            RequestDispatcher rd=request.getRequestDispatcher(url);
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