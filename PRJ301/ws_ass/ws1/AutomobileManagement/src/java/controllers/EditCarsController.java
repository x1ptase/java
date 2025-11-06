package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.CarsDAO;
import models.CarsDTO;

public class EditCarsController extends HttpServlet {

    private static final String UPDATE_PAGE="UpdateCars.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try{
            int carID=Integer.parseInt(request.getParameter("id"));
            CarsDAO dao=new CarsDAO();
            CarsDTO car=dao.findById(carID);
            request.setAttribute("car", car);
            request.getRequestDispatcher(UPDATE_PAGE).forward(request, response);
        } catch (Exception ex){
            log("Error at EditCarsController: " + ex.getMessage());
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
