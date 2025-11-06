package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.MobilesDAO;
import models.MobilesDTO;
import models.UsersDTO;

public class CreateMobilesController extends HttpServlet {

    private static final String ERROR_PAGE="Error.jsp";
    private static final String LOGIN_PAGE="Login.jsp";
    
    private static final String VIEW_CONTROLLER="ViewController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }
        
        UsersDTO user = (UsersDTO) session.getAttribute("user");
        if (user.getRole() != 1) {
            request.setAttribute("msg", "Access denied. Only admin can create mobiles.");
            request.getRequestDispatcher(VIEW_CONTROLLER).forward(request, response);
            return;
        }
        
         try{
            MobilesDTO mobiles=new MobilesDTO(
                    request.getParameter("mobileId"),
                    request.getParameter("description"),
                    Float.parseFloat(request.getParameter("price")),
                    request.getParameter("mobileName"),
                    Integer.parseInt(request.getParameter("yearOfProduction")),
                    Integer.parseInt(request.getParameter("quantity")),
                    "on".equals(request.getParameter("notSale"))
                );
            MobilesDAO dao=new MobilesDAO();
            dao.createMobiles(mobiles);
            request.getRequestDispatcher(VIEW_CONTROLLER).forward(request, response);
        } catch(Exception ex) {
            log("Error at CreateMobilesController: " + ex.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
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
