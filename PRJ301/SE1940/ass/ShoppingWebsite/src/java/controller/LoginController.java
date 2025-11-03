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
    private static final String USER_LIST_CONTROLLER="ViewPizzaListController";
    private static final String VIEW_ADMIN_CONTROLLER="ViewProductController";

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
                response.sendRedirect(request.getContextPath()+"/"+VIEW_ADMIN_CONTROLLER); // admin -> controller loads list
            } else{
                response.sendRedirect(request.getContextPath()+"/"+USER_LIST_CONTROLLER); // user -> shopping list
            }
        } catch(Exception ex){
            log("Error at LoginController: " + ex.getMessage());
        }
    }
}
