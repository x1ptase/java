package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.MobilesDAO;
import models.UsersDTO;

public class DeleteMobilesController extends HttpServlet {

    private static final String VIEW_CONTROLLER="ViewController";
    private static final String ERROR_PAGE="Error.jsp";
    private static final String LOGIN_PAGE="Login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR_PAGE;
        
        HttpSession session=request.getSession(false);
        if(session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }
        
        UsersDTO user=(UsersDTO) session.getAttribute("user");
        if(user.getRole() != 1){
            request.setAttribute("msg", "nly admin can delete mobiles");
            url=VIEW_CONTROLLER;
        } else{
            try{
                String mobilesId=request.getParameter("mobilesId");
                
                // call dao
                MobilesDAO dao=new MobilesDAO();
                dao.delete(mobilesId);
                
                request.setAttribute("msg", "Delete mobile ID " + mobilesId + " successfully");
                
                // forward to view page to show result
                url=VIEW_CONTROLLER;
            } catch(Exception ex) {
                log("Error at DeleteMobilesController: " + ex.getMessage());
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
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
