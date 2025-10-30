package controller;

import dao.CarDAO;
import model.Car;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CarCreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chỉ show form nhập mới car
        request.getRequestDispatcher("carForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Car car = new Car(
                Integer.parseInt(request.getParameter("carID")),
                request.getParameter("carName"),
                request.getParameter("manufacturer"),
                Double.parseDouble(request.getParameter("price")),
                Integer.parseInt(request.getParameter("releasedYear"))
            );
            CarDAO dao = new CarDAO();
            dao.insertCar(car);
            response.sendRedirect("CarListServlet");
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}