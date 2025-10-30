package controller;

import dao.CarDAO;
import model.Car;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int carID = Integer.parseInt(request.getParameter("id"));
        CarDAO dao = new CarDAO();
        Car car = null;
        try {
            car = dao.getCarById(carID);
        } catch (Exception ex) {
            Logger.getLogger(CarDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("car", car);
        request.getRequestDispatcher("carDetails.jsp").forward(request, response);
    }
}