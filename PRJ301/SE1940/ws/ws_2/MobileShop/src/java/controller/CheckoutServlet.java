package controller;

import model.Cart;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            request.setAttribute("errorMsg", "Giỏ hàng trống, không thể thanh toán!");
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
            return;
        }
        // Ở đây, bạn có thể lưu đơn hàng vào cơ sở dữ liệu nếu muốn.
        session.removeAttribute("cart"); // clear cart
        request.getRequestDispatcher("checkout-success.jsp").forward(request, response);
    }
}
