
package UserServlet;


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
@WebServlet(name="ProcessUserServlet", urlPatterns={"/ProcessUserServlet"})
public class ProcessUserServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String action=request.getParameter("action").toLowerCase();
        
        try{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Manage Users</title>");
            out.println("</head>");
            out.println("<body>");
            
            if(action.equals("view")){
                response.sendRedirect("ManageUserServlet?action=view");
            } else if(action.equals("add")){
                response.sendRedirect("AddUser.html");
            } else if(action.equals("update")){
                response.sendRedirect("UpdateUser.html");
            } else if(action.equals("delete")){
                response.sendRedirect("DeleteUser.html");
            }
            out.println("</body");
            out.println("</html");
        } catch(Exception ex){
            out.println("\n");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
