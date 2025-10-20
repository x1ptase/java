package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        String username=request.getParameter("txtUsername");
        String password=request.getParameter("txtPassword");
        String USERNAME="admin";
        String PASSWORD="12345";
        
        if(USERNAME.equals(username) && PASSWORD.equals(password)){
            String nameAdmin="X1";
            request.setAttribute("name", nameAdmin);
            RequestDispatcher rd=request.getRequestDispatcher("view.jsp");
            rd.forward(request, response);
            
        } else{
            String message="Login Failed!!";
            request.setAttribute("msg", message);
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
