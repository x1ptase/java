package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.model.UserDao;
import pe.model.UserDto;

public class LoginController extends HttpServlet {

    private static final String LOGIN_PAGE="login.jsp";
    private static final String WELCOME_PAGE="welcome.jsp";
    private static final String SEARCH_PAGE="search.jsp";
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName=request.getParameter("txtUsername");
        String password=request.getParameter("txtPassword");
        UserDao dao=new UserDao();
        UserDto user=null;
        
        try{
            user=dao.checkLogin(userName, password);
            if(user == null){
                request.setAttribute("msg", "Invalid user or password");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            }
            HttpSession session=request.getSession();
            session.setAttribute("user", user);
            if(user.getRoleID().equals("AD")){
                request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
            } else if(user.getRoleID().equals("MB")){
                request.getRequestDispatcher(SEARCH_PAGE).forward(request, response);
            }
        } catch(Exception ex){
            log("Error at LoginController: " + ex.getMessage());
        } finally{
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
