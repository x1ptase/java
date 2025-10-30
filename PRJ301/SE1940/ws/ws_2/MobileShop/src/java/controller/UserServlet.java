package controller;

import dao.MobilesDAO;
import model.Mobiles;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[DEBUG] UserServlet doGet đã được gọi!");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        List<Mobiles> mobiles = (min == null || max == null || min.isEmpty() || max.isEmpty())
            ? new dao.MobilesDAO().getAll()
            : new dao.MobilesDAO().searchByPrice(Float.parseFloat(min), Float.parseFloat(max));
        System.out.println("[DEBUG] mobiles.size()=" + mobiles.size());
        for (model.Mobiles m : mobiles) {
            System.out.println("-- " + m.getMobileId() + " | " + m.getMobileName());
        }
        request.setAttribute("mobiles", mobiles);
        request.getRequestDispatcher("user.jsp").forward(request, response);
    }
}