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
//public class StaffServlet extends HttpServlet {
//    
//    private static final String STAFF_PAGE="Staff.jsp";
//    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if ("edit".equals(action)) {
//            String mobileId = request.getParameter("mobileId");
//            Mobiles mobile = null;
//            if (mobileId != null) {
//                mobile = new MobilesDAO().getById(mobileId);
//            }
//            request.setAttribute("editMobile", mobile);
//        }
//        String keyword = request.getParameter("keyword");
//        if (keyword != null) keyword = keyword.trim();
//        List<Mobiles> mobiles = (keyword == null || keyword.isEmpty())
//            ? new MobilesDAO().getAll()
//            : new MobilesDAO().searchByNameOrId(keyword);
//        request.setAttribute("mobiles", mobiles);
//        request.getRequestDispatcher(STAFF_PAGE).forward(request, response);
//    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        MobilesDAO dao = new MobilesDAO();
//        if ("delete".equals(action)) {
//            dao.delete(request.getParameter("mobileId"));
//        } else if ("update".equals(action)) {
//            Mobiles mobile = new Mobiles(
//                request.getParameter("mobileId"),
//                request.getParameter("description"),
//                Float.parseFloat(request.getParameter("price")),
//                request.getParameter("mobileName"),
//                Integer.parseInt(request.getParameter("yearOfProduction")),
//                Integer.parseInt(request.getParameter("quantity")),
//                "on".equals(request.getParameter("notSale"))
//            );
//            dao.update(mobile);
//        } else if ("create".equals(action)) {
//            Mobiles mobile = new Mobiles(
//                request.getParameter("mobileId"),
//                request.getParameter("description"),
//                Float.parseFloat(request.getParameter("price")),
//                request.getParameter("mobileName"),
//                Integer.parseInt(request.getParameter("yearOfProduction")),
//                Integer.parseInt(request.getParameter("quantity")),
//                "on".equals(request.getParameter("notSale"))
//            );
//            dao.insert(mobile);
//        }
//        response.sendRedirect("staff");
//    }
//}