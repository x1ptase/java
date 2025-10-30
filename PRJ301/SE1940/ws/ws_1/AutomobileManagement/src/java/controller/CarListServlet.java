package controller;

import dao.CarDAO;
import model.Car;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarListServlet extends HttpServlet {
    
    private static final String LIST_PAGE="carList.jsp";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CarDAO dao = new CarDAO();
        List<Car> cars = null;
        try {
            cars = dao.getAllCars();
        } catch (Exception ex) {
            Logger.getLogger(CarListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("cars", cars);
        request.getRequestDispatcher(LIST_PAGE).forward(request, response);
    }
}