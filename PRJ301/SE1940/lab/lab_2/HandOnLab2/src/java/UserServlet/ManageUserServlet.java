/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package UserServlet;

import DBUtils.User;
import DBUtils.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x1pta
 */
public class ManageUserServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String userName, password, lastName;
        boolean isAdmin=true;
        String action;
        action=request.getParameter("action").toLowerCase();
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
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
                User user=new User(userName, password, lastName, isAdmin);
                if(action.equals("add")){
                    out.println("<h1>Users Management - Add new user" + "</h1>");
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
            }
            out.println("<a href='Login.html'>Back to login</a>");
            out.println("</body>");
            out.println("</html>");
        } catch(Exception ex){
            out.println("Error:" + ex.getMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}