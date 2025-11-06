package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.CarsDAO;
import models.CarsDTO;

public class CreateCarsController extends HttpServlet {

    private static final String ERROR_PAGE="Error.jsp";
    private static final String LIST_PAGE="CarsList.jsp";
    
    private static final String LIST_CONTROLLER="CarsListController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            CarsDTO car=new CarsDTO(
                    Integer.parseInt(request.getParameter("carID")),
                    request.getParameter("carName"),
                    request.getParameter("manufacturer"),
                    Double.parseDouble(request.getParameter("price")),
                    Integer.parseInt(request.getParameter("releasedYear"))
                );
            CarsDAO dao=new CarsDAO();
            dao.createCar(car);
            request.getRequestDispatcher(LIST_CONTROLLER).forward(request, response);
        } catch(Exception ex) {
            log("Error at CreateCarsController: " + ex.getMessage());
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
