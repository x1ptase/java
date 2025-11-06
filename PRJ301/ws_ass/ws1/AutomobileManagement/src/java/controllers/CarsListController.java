package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.CarsDAO;
import models.CarsDTO;
public class CarsListController extends HttpServlet {

    private static final String LIST_PAGE="CarsList.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            CarsDAO dao=new CarsDAO();
            List<CarsDTO> cars=dao.viewAllCars();
            request.setAttribute("cars", cars);
            request.getRequestDispatcher(LIST_PAGE).forward(request, response);
        } catch (Exception ex) {
            log("Error at CarsListController: " + ex.getMessage());
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
