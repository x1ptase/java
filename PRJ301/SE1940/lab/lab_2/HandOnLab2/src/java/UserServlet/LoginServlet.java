package UserServlet;

import DBUtils.User;
import DBUtils.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x1pta
 */
@WebServlet(name="LoginServlet", urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
        try{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            String userName=request.getParameter("txtUsername");
            String password=request.getParameter("txtPassword");
            
            try{
                UserDAO userDAO=new UserDAO();
                User user=userDAO.login(userName, password);
                if(user != null){
                    out.println("Welcome " + user.getLastName() + " !!!</br>");
                    if(user.isIsAdmin()){
                        out.println("You are logged successfully in with administrator role.</br>");
                        out.println("<a href='ProcessUserServlet?action=view'>View user list</a></br>");
                        out.println("<a href='ProcessUserServlet?action=add'>Add new user</a></br>");
                        out.println("<a href='ProcessUserServlet?action=update'>Update user</a></br>");
                        out.println("<a href='ProcessUserServlet?action=delete'>Delete user</a></br>");
                    } else{
                        out.println("You are logged successfully in with normal role.</br>");
                        out.println("<a href='ProcessUserServlet?action=view'>View user list</a></br>");
                    }
                } else{
                    out.println("Login has failed.</br>");
                    out.println("<a href='Login.html'>Back to login</a>");
                }
            } catch(Exception ex){
                out.println("Something went wrong. Error :" + ex.getMessage());
            }
            out.println("</body>");
            out.println("</html>");
        }
        finally{
            out.close();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try{
            processRequest(request, response);
        } catch(Exception e){
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try{
            processRequest(request, response);
        } catch(Exception e){
            throw new ServletException(e);
        }
    }
}
