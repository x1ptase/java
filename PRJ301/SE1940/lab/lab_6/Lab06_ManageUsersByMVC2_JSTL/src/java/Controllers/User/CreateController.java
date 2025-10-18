/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Models.DAO.UserDAO;
import Models.DTO.User;
import Models.DTO.UserError;
import java.io.IOException;
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
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {
    private final String createUserPage="CreateUser.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName, password, lastName;
        boolean isAdmin=true, isError=false;
        String message=null;
        String url=createUserPage;
        
        try {
            UserDAO userDao = new UserDAO();
            userName = request.getParameter("txtUserName");
            password = request.getParameter("txtPassword");
            lastName = request.getParameter("txtLastName");
            String admin = request.getParameter("chkIsAdmin");
            UserError userError = new UserError();
        
            if (userName.matches("U\\\\d{3}") == false) {
                userError.setUserNameError("The UserName must be formatted Uxxx, x is digits.");
                isError = true;
            } //end check username

            if (password.matches("(.){3,15}") == false) {
                userError.setPasswordError("The Password must be 3 to 15 characters.");
                isError = true;
            } //end check Password

            if (lastName.matches("(.){5,50}") == false) {
                userError.setLastNameError("The LastName must be 5 to 50 characters.");
                isError = true;
            } //end check Password

            if (userDao.getUserByUserName(userName) != null) {
                userError.setDuplicateUserName("The UserName already exists.");
                isError = true;
            }

            if (isError == false) {
                if (admin == null) {
                    isAdmin = false;
                }

                User user = new User(userName, password, lastName, isAdmin);
                if (userDao.addUser(user) == true) {
                    message = "<b style='color: green'>The user has been created successfully</b>";
                }
                else {
                    message = "<b style='color: red'>Something went wrong.</b>";
                }
            } //end isError
            else {
                request.setAttribute("ErrorDetails", userError);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        } finally {
            request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
