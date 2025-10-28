package Controllers.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet {
    
    private static final String SEARCH_CONTROLLER="SearchController";
    private static final String USER_DETAILS_CONTROLLER="UserDetailsController";
    private static final String CREATE_CONTROLLER="CreateController";
    private static final String DELETE_CONTROLLER="DeleteController";
    private static final String UPDATE_CONTROLLER="UpdateController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action=request.getParameter("action");
        String url=null;
        
        try{
            if(action.equalsIgnoreCase("Create")){
                url=CREATE_CONTROLLER;
            } else if(action.equalsIgnoreCase("Details")){
                url=USER_DETAILS_CONTROLLER;
            } else if(action.equalsIgnoreCase("Update")){
                url=UPDATE_CONTROLLER;
            }
            boolean isAdmin=(boolean)request.getAttribute("loggedByAdmin");
            if(isAdmin){
                if(action.equalsIgnoreCase("Delete")){
                    url=DELETE_CONTROLLER;
                } else if(action.equalsIgnoreCase("Search")){
                    url=SEARCH_CONTROLLER;
                }
            }
        } finally{
            request.getRequestDispatcher(url).forward(request, response);
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
