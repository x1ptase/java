package Controllers.Authentication;

import Models.DAO.Services.UserService;
import Models.DTO.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    
    private final String LOGIN_PAGE="Login.jsp";
    private final String USER_CONTROLLER="UserController";
    private final String SEARCH_PAGE="Search.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=LOGIN_PAGE;
        String userName=request.getParameter("txtUserName");
        String password=request.getParameter("txtPassword");
        
        try{
            UserService userService=new UserService();
            User user=userService.checkAccount(userName, password);
            
            if(user != null){
                HttpSession session=request.getSession();
                session.setAttribute("userLoggedIn", user);
                if(user.isIsAdmin()){
                    url=SEARCH_PAGE;
                } else{
                    url=USER_CONTROLLER + "?action=Details&&UserName=" + userName;
                }
            } else{
                request.setAttribute("message", "The user name or password is invalid");
            }
        } catch(Exception ex){
            log(ex.getMessage());
        } finally{
            request.getRequestDispatcher(url).forward(request, response);
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
