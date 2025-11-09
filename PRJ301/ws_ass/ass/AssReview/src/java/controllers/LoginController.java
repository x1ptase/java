package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.tbAccountDAO;
import models.tbAccountDTO;

public class LoginController extends HttpServlet {
    
    private static final String LOGIN_PAGE="login.jsp";
    private static final String BIKE_LIST_PAGE="bikeList.jsp";
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID=request.getParameter("txtUserID");
        String password=request.getParameter("txtPassword");
        tbAccountDAO dao=new tbAccountDAO();
        tbAccountDTO account=null;
        
        try{
            account=dao.checkLogin(userID, password); 
            if(account == null){
                request.setAttribute("msg", "Incorrect UserID or Password");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            }
            
            HttpSession session=request.getSession();
            session.setAttribute("account", account);
            request.getRequestDispatcher(BIKE_LIST_PAGE).forward(request, response);
        } catch(Exception ex){
            log("Error at LoginController: " + ex.getMessage());
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
