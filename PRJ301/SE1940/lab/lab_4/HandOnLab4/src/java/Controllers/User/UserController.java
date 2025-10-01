/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author x1pta
 */
public class UserController extends HttpServlet {
    private final String loginPage="Login.html";
    // Controllers
    private static final String searchController="SearchController";
    private static final String userDetailsController="UserDetailsController";
    private static final String createController="CreateController";
    private static final String deleteController="DeleteController";
    private static final String updateController="UpdateController";
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
        String action=request.getParameter("action");
        String url=null;
        
        try{
            HttpSession session=request.getSession();
            boolean isLoggedIn=(session.getAttribute("userLoggedIn") != null);
            if(action.equalsIgnoreCase("create")){
                url=createController;
            } else if(isLoggedIn){
                if(action.equalsIgnoreCase("Delete")){
                    url=deleteController;
                } else if(action.equalsIgnoreCase("Update")){
                    url=updateController;
                } else if(action.equalsIgnoreCase("Search")){
                    url=searchController;
                } else if(action.equalsIgnoreCase("Details")){
                    url=userDetailsController;
                } else if(action.equalsIgnoreCase("UpdateAdmin")){
                    url=updateController;
                }
            } else{
                url=loginPage;
            }
        } finally{
            RequestDispatcher rd=request.getRequestDispatcher(url);
            rd.forward(request, response);
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
