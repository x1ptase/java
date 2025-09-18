package UserServlet;

import DBUtils.User;
import DBUtils.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x1pta
 */
@WebServlet(name="ManageUserServlet", urlPatterns={"/ManageUserServlet"})
public class ManageUserServlet extends HttpServlet{
    protected void processRequest (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, Exception{
        String userName, password = null, lastName = null;
        boolean isAdmin=true;
        String action;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        action=request.getParameter("action").toLowerCase();
        
        try{
            out.println("<!DOCTYPE html");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Users Management</title>");
            out.println("</head>");
            out.println("<body>");
            
            userName=request.getParameter("txtUserName");
            UserDAO userDAO=new UserDAO();
            if(action.equals("view")){
                out.println("<h1>View User List" + "</h1>");
                List<User> userList=userDAO.getUserList();
                for(User user : userList){
                    out.println(user + "</br>");
                }
            } else if(action.equals("delete")){
                out.println("<h1>Users Management - Delete user" + "</h1>");
                if(userDAO.deleteUser(userName) == true){
                    out.println("User has been deleted successfully</br>");
                } else{
                    out.println("<h3>Something went wrong!</h3></br>");
                }
            } else{
                password=request.getParameter("txtPassword");
                lastName=request.getParameter("txtLastName");
                String admin=request.getParameter("chkIsAdmin");
                if(admin == null){
                    isAdmin=false;
                }
            }
            User user=new User(userName, password, lastName, isAdmin);
            if(action.equals("add")){
                out.println("<h1>UserManagement - Add new user" + "</h1>");
                if(userDAO.addUser(user) == true){
                    out.println("User has been added successfully</br>");
                } else{
                    out.println("<h3>Something went wrong!</h3></br>");
                }
            } else if(action.equals("update")){
                out.println("<h1>Users Management - Update user" + "</h1>");
                if(userDAO.updateUser(user) == true){
                    out.println("User has been updated successfully</br>");
                } else{
                    out.println("<h3>Something went wrong!</h3></br>");
                }
            }
            out.println("<a href='Login.html'>Back to login</a>");
            out.println("</body>");
            out.println("</html>");
        } catch(Exception ex){
            out.println("Error:" + ex.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
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

} // end class
