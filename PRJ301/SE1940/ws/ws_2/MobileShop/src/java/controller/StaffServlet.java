package controller;

import dao.MobilesDAO;
import model.Mobiles;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.util.List;

public class StaffServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Mobiles> mobiles = (keyword == null || keyword.isEmpty())
            ? new MobilesDAO().getAll()
            : new MobilesDAO().searchByNameOrId(keyword);
        request.setAttribute("mobiles", mobiles);
        request.getRequestDispatcher("staff.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        MobilesDAO dao = new MobilesDAO();
        if ("delete".equals(action)) {
            dao.delete(request.getParameter("mobileId"));
        } else if ("update".equals(action)) {
            Mobiles mobile = new Mobiles(
                request.getParameter("mobileId"),
                request.getParameter("description"),
                Float.parseFloat(request.getParameter("price")),
                request.getParameter("mobileName"),
                Integer.parseInt(request.getParameter("yearOfProduction")),
                Integer.parseInt(request.getParameter("quantity")),
                "on".equals(request.getParameter("notSale"))
            );
            dao.update(mobile);
        } else if ("create".equals(action)) {
            Mobiles mobile = new Mobiles(
                request.getParameter("mobileId"),
                request.getParameter("description"),
                Float.parseFloat(request.getParameter("price")),
                request.getParameter("mobileName"),
                Integer.parseInt(request.getParameter("yearOfProduction")),
                Integer.parseInt(request.getParameter("quantity")),
                "on".equals(request.getParameter("notSale"))
            );
            dao.insert(mobile);
        }
        response.sendRedirect("staff");
    }
}