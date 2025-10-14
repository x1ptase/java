package controller;

import dao.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteProductController", urlPatterns = {"/DeleteProductController"})
public class DeleteProductController extends HttpServlet {
    
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "";
        
        try {
            String productIdStr = request.getParameter("productId");
            
            if (productIdStr != null && !productIdStr.isEmpty()) {
                int productId = Integer.parseInt(productIdStr);
                boolean success = productDAO.deleteProduct(productId);
                
                if (success) {
                    request.setAttribute("message", "Product deleted successfully!");
                    url = "/ViewProductController?action=viewList";
                } else {
                    request.setAttribute("error", "Failed to delete product");
                    url = "/error.jsp";
                }
            } else {
                request.setAttribute("error", "Product ID is required");
                url = "/error.jsp";
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid product ID format");
            url = "/error.jsp";
        } catch (Exception e) {
            request.setAttribute("error", "Error deleting product: " + e.getMessage());
            url = "/error.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }
}