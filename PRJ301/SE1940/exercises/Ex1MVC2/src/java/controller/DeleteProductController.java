package controller;

import dao.ProductDAO;
import model.Product;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Delete Product Controller Servlet - handles deleting products
 */
@WebServlet(name = "DeleteProductController", urlPatterns = {"/DeleteProductController"})
public class DeleteProductController extends HttpServlet {
    
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Process requests based on action parameter
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "deleteProduct";
        }
        
        String url = "";
        try {
            switch (action) {
                case "deleteProduct":
                    url = deleteProduct(request, response);
                    break;
                case "confirmDelete":
                    url = showConfirmDeleteForm(request, response);
                    break;
                default:
                    url = deleteProduct(request, response);
                    break;
            }
        } catch (Exception ex) {
            log("Error: " + ex.getMessage());
            request.setAttribute("error", "Unexpected error: " + ex.getMessage());
            url = "/ViewProductList.jsp";
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }
    
    /**
     * Show confirmation form for product deletion
     */
    private String showConfirmDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productIdStr = request.getParameter("productId");
            if (productIdStr != null && !productIdStr.isEmpty()) {
                int productId = Integer.parseInt(productIdStr);
                // Get product details for confirmation
                Product product = productDAO.getProductById(productId);
                if (product != null) {
                    request.setAttribute("product", product);
                    return "/DeleteProduct.jsp";
                } else {
                    request.setAttribute("error", "Product not found");
                    return "/ViewProductList.jsp";
                }
            } else {
                request.setAttribute("error", "Product ID is required");
                return "/ViewProductList.jsp";
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Invalid product ID format");
            return "/ViewProductList.jsp";
        } catch (Exception ex) {
            request.setAttribute("error", "Error loading product for deletion: " + ex.getMessage());
            return "/ViewProductList.jsp";
        }
    }
    
    /**
     * Delete product from the database
     */
    private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productIdStr = request.getParameter("productId");
            if (productIdStr != null && !productIdStr.isEmpty()) {
                int productId = Integer.parseInt(productIdStr);
                
                System.out.println("Attempting to delete product with ID: " + productId);
                boolean success = productDAO.deleteProduct(productId);
                System.out.println("Delete product result: " + success);
                
                if (success) {
                    request.setAttribute("success", "Product deleted successfully!");
                } else {
                    request.setAttribute("error", "Failed to delete product. Please try again.");
                }
            } else {
                request.setAttribute("error", "Product ID is required");
            }
            
            return "/MainController?action=viewList";
            
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Invalid product ID format");
            return "/MainController?action=viewList";
        } catch (Exception ex) {
            request.setAttribute("error", "Error deleting product: " + ex.getMessage());
            return "/MainController?action=viewList";
        }
    }
}
