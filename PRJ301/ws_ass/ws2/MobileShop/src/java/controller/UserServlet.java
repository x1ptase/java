//package controller;
//
//import dao.MobilesDAO;
//import model.Mobiles;
//
//import javax.servlet.http.*;
//import javax.servlet.*;
//import java.io.IOException;
//import java.util.List;
//
//public class UserServlet extends HttpServlet {
//    
//    private static final String USER_PAGE="User.jsp";
//    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String min = request.getParameter("min");
//        String max = request.getParameter("max");
//        List<Mobiles> mobiles = (min == null || max == null || min.isEmpty() || max.isEmpty())
//            ? new dao.MobilesDAO().getAll()
//            : new dao.MobilesDAO().searchByPrice(Float.parseFloat(min), Float.parseFloat(max));
//        request.setAttribute("mobiles", mobiles);
//        request.getRequestDispatcher(USER_PAGE).forward(request, response);
//    }
//}