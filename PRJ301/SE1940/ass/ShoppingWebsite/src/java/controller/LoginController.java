package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDAO;
import model.AccountDTO;

public class LoginController extends HttpServlet {
    
    private static final String LOGIN_PAGE="Login.jsp";
    private static final String SEARCH_PAGE="Search.jsp";
    private static final String VIEW_PAGE="View.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url=LOGIN_PAGE;
        String userName=request.getParameter("txtUsername");
        String password=request.getParameter("txtPassword");
        AccountDAO dao=new AccountDAO();
        AccountDTO account=null;
        
        try{
            account=dao.checkLogin(userName, password); 
            if(account == null){
                request.setAttribute("msg", "Username or password invalid");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return; // stop
            }
            // login success
            HttpSession session=request.getSession();
            session.setAttribute("account", account);
            
            if(account.isType()){
                response.sendRedirect(VIEW_PAGE); // admin
            } else{
                response.sendRedirect(SEARCH_PAGE); // user
            }
        } catch(Exception ex){
            log("Error at LoginController: " + ex.getMessage());
        }
    }
}
