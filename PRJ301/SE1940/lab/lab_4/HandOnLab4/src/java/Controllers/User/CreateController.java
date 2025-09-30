/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Models.DAO.UserDAO;
import Models.DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x1pta
 */
public class CreateController extends HttpServlet {
    private final String displayMessagePage="DisplayMessage.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName, password, lastName;
        boolean isAdmin=true;
        String message="Something went wrong.";
        String url=displayMessagePage;
        
        try{
            userName=request.getParameter("txtUserName");
            password=request.getParameter("txtPassword");
            lastName=request.getParameter("txtLastName");
            String admin=request.getParameter("chkIsAdmin");
            if(admin == null){
                isAdmin=false;
            }
            if(!userName.isEmpty()){
                User user=new User(userName, password, lastName, isAdmin);
                UserDAO userDAO=new UserDAO();
                if(userDAO.addUser(user) == true){
                    message="The user '" + userName + "' has been added successfully";
                }
            }
        } catch(Exception ex){
            log(ex.getMessage());
        } finally{
            request.setAttribute("action", "Create User");
            request.setAttribute("page", "Login.html");
            request.setAttribute("message", message);
            RequestDispatcher rd=request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }
}
