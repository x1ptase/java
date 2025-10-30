package controller;

import dao.UsersDAO;
import model.Users;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    
    private static final String LOGIN_CONTROLLER="Login.jsp";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        request.getRequestDispatcher(LOGIN_CONTROLLER).forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        Users user = new UsersDAO().checkLogin(userId, password);
        if (user == null) {
            request.setAttribute("errorMsg", "Đăng nhập thất bại");
            request.getRequestDispatcher(LOGIN_CONTROLLER).forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (user.getRole() == 1) {
            response.sendRedirect("staff");
        } else {
            response.sendRedirect("user");
        }
    }
}