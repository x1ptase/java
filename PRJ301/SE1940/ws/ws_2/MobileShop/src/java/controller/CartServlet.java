package controller;

import dao.MobilesDAO;
import model.Cart;
import model.Mobiles;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;

public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
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
            Mobiles mobile = new MobilesDAO().getById(request.getParameter("mobileId"));
            int quantity = Integer.parseInt(request.getParameter("qty"));
            cart.addItem(mobile, quantity);
        } else if ("update".equals(action)) {
            cart.updateQuantity(request.getParameter("mobileId"),
                               Integer.parseInt(request.getParameter("qty")));
        } else if ("remove".equals(action)) {
            cart.removeItem(request.getParameter("mobileId"));
        }
        // Debug: in trạng thái Cart để kiểm tra session bị mất hay model có item không
        System.out.println("Session ID: " + session.getId());
        System.out.println("Cart size: " + cart.getItems().size());
        for (model.CartItem ci : cart.getItems()) {
            System.out.println("Item: " + ci.getMobile().getMobileId() + " - Số lượng: " + ci.getQuantity());
        }
        response.sendRedirect("cart");
    }
}