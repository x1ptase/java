package controller;

import dao.ProductDAO;
import model.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {
    
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
            String productName = request.getParameter("productName");
            String unitPriceStr = request.getParameter("unitPrice");
            String quantityStr = request.getParameter("quantity");
            
            if (productIdStr != null && productName != null && unitPriceStr != null && quantityStr != null) {
                int productId = Integer.parseInt(productIdStr);
                double unitPrice = Double.parseDouble(unitPriceStr);
                int quantity = Integer.parseInt(quantityStr);
                
                // Validate input
                if (productName.trim().isEmpty()) {
                    request.setAttribute("error", "Product name cannot be empty");
                    url = "/error.jsp";
                } else if (unitPrice < 0) {
                    request.setAttribute("error", "Unit price cannot be negative");
                    url = "/error.jsp";
                } else if (quantity < 0) {
                    request.setAttribute("error", "Quantity cannot be negative");
                    url = "/error.jsp";
                } else {
                    Product product = new Product(productId, productName.trim(), unitPrice, quantity);
                    boolean success = productDAO.updateProduct(product);
                    
                    if (success) {
                        request.setAttribute("message", "Product updated successfully!");
                        request.setAttribute("product", product);
                        url = "/detailProduct.jsp";
                    } else {
                        request.setAttribute("error", "Failed to update product");
                        url = "/error.jsp";
                    }
                }
            } else {
                request.setAttribute("error", "All fields are required");
                url = "/error.jsp";
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input format. Please check your input values.");
            url = "/error.jsp";
        } catch (Exception e) {
            request.setAttribute("error", "Error updating product: " + e.getMessage());
            url = "/error.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }
}