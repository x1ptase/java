/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package UserServlet;

import DBUtils.User;
import DBUtils.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x1pta
 */
public class LoginServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String userName=request.getParameter("txtUserName");
            String password=request.getParameter("txtPassword");
            try{
                UserDAO userDAO=new UserDAO();
                User user=userDAO.login(userName, password);
                if(user != null){
                    out.println("Welcome to " + user.getLastName() + " !!!</br>");
                    if(user.isIsAdmin() == true){
                        out.println("You are Logged successfully in with administrator role" + "</br>");
                        out.println("<a href='ProcessUserServlet?action=view'>View user list</a></br>");
                        out.println("<a href='ProcessUserServlet?action=add'>Add new user</a></br>");
                        out.println("<a href='ProcessUserServlet?action=update'>Update user</a></br>");
                        out.println("<a href='ProcessUserServlet?action=delete'>Delete user</a></br>");
                    } else{
                        out.println("You are Logged successfully in with normal role" + "</br>");
                        out.println("<a href='ProcessUserServlet?action=view'>View user list</a></br>");
                    }
                } else{
                    out.println("Login has failed</br>");
                    out.println("<a href='Login.html'>Back to login</a>");
                }
            } catch(Exception ex){
                out.println("Something went wrong. Error:" + ex.getMessage());
            }
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
