
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.MobilesDAO;
import models.MobilesDTO;

public class ViewController extends HttpServlet {

   private static final String WELCOME_PAGE="Welcome.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            MobilesDAO dao=new MobilesDAO();
            List<MobilesDTO> mobiles=dao.viewAllMobiles();
            request.setAttribute("mobiles", mobiles);
            request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
        } catch(Exception ex){
            log("Error at ViewController: " + ex.getMessage());
        }
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
