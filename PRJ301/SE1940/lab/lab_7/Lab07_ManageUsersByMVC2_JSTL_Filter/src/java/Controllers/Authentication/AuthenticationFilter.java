package Controllers.Authentication;

import Models.DTO.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
    
    private static final String LOGIN_PAGE="Login.jsp";
    private final String CREATE_CONTROLLER="CreateController";

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        HttpSession session=req.getSession(false);

        String action=request.getParameter("action");
        if(action!=null && action.equalsIgnoreCase("Create")){
            request.getRequestDispatcher(CREATE_CONTROLLER).forward(request, response);
        }

        if(session != null){
            User user=(User)session.getAttribute("userLoggedIn");
            if(user != null){
                req.setAttribute("loggedByAdmin", user.isIsAdmin());
                chain.doFilter(request, response);
            }
        } else{
            resp.sendRedirect(LOGIN_PAGE);
        }
    }
    
    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
    }
}
