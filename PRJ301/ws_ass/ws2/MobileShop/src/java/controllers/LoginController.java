package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.UsersDAO;
import models.UsersDTO;

public class LoginController extends HttpServlet {

    private static final String LOGIN_PAGE="Login.jsp";
    private static final String STAFF_PAGE="Staff.jsp";
    private static final String USER_PAGE="User.jsp";
    private static final String WELCOME_PAGE="Welcome.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId=request.getParameter("txtUserID");
        String password=request.getParameter("txtPassword");
        UsersDAO dao=new UsersDAO();
        UsersDTO user=null;
        try {
            user=dao.checkLogin(userId, password);
            if(user == null){
                request.setAttribute("msg", "Invalid username or password");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            }
            // login success
            HttpSession session=request.getSession();
            session.setAttribute("user", user);
            
            if(user.getRole() == 1) {
                request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
            } else{
                request.getRequestDispatcher(USER_PAGE).forward(request, response);
            }
        } catch(Exception ex){
            log("Error at LoginController: " + ex.getMessage());
        } finally{
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
