package controller;

import dao.MobileDAO;
import model.Cart;
import model.Mobile;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        if ("add".equals(action)) {
            Mobile mobile = new MobileDAO().getById(request.getParameter("mobileId"));
            int quantity = Integer.parseInt(request.getParameter("qty"));
            cart.addItem(mobile, quantity);
        } else if ("update".equals(action)) {
            cart.updateQuantity(request.getParameter("mobileId"),
                               Integer.parseInt(request.getParameter("qty")));
        } else if ("remove".equals(action)) {
            cart.removeItem(request.getParameter("mobileId"));
        }
        response.sendRedirect("cart");
    }
}