package controller;

import dao.CarDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CarDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int carID = Integer.parseInt(request.getParameter("id"));
        CarDAO dao = new CarDAO();
        try {
            dao.deleteCar(carID);
            response.sendRedirect("CarListServlet");
        } catch(Exception ex){
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}