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
 * Update Product Controller Servlet - handles updating existing products
 */
@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {
    
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
            action = "showForm";
        }
        
        String url = "";
        try {
            switch (action) {
                case "showForm":
                    url = showUpdateProductForm(request, response);
                    break;
                case "updateProduct":
                    url = updateProduct(request, response);
                    break;
                default:
                    url = showUpdateProductForm(request, response);
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
     * Show the update product form
     */
    private String showUpdateProductForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productIdStr = request.getParameter("productId");
            if (productIdStr != null && !productIdStr.isEmpty()) {
                int productId = Integer.parseInt(productIdStr);
                Product product = productDAO.getProductById(productId);
                if (product != null) {
                    request.setAttribute("product", product);
                    return "/UpdateProduct.jsp";
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
            request.setAttribute("error", "Error loading product for update: " + ex.getMessage());
            return "/ViewProductList.jsp";
        }
    }
    
    /**
     * Update product in the database
     */
    private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productIdStr = request.getParameter("productId");
            String productName = request.getParameter("productName");
            String unitPriceStr = request.getParameter("unitPrice");
            String quantityStr = request.getParameter("quantity");
            
            // Validate input
            if (productIdStr == null || productIdStr.trim().isEmpty()) {
                request.setAttribute("error", "Product ID is required");
                return "/ViewProductList.jsp";
            }
            
            if (productName == null || productName.trim().isEmpty()) {
                request.setAttribute("error", "Product name is required");
                request.setAttribute("productId", productIdStr);
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/UpdateProduct.jsp";
            }
            
            if (unitPriceStr == null || unitPriceStr.trim().isEmpty()) {
                request.setAttribute("error", "Unit price is required");
                request.setAttribute("productId", productIdStr);
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/UpdateProduct.jsp";
            }
            
            if (quantityStr == null || quantityStr.trim().isEmpty()) {
                request.setAttribute("error", "Quantity is required");
                request.setAttribute("productId", productIdStr);
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/UpdateProduct.jsp";
            }
            
            // Parse values
            int productId = Integer.parseInt(productIdStr);
            double unitPrice = Double.parseDouble(unitPriceStr);
            int quantity = Integer.parseInt(quantityStr);
            
            if (unitPrice < 0) {
                request.setAttribute("error", "Unit price must be a positive number");
                request.setAttribute("productId", productIdStr);
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/UpdateProduct.jsp";
            }
            
            if (quantity < 0) {
                request.setAttribute("error", "Quantity must be a positive number");
                request.setAttribute("productId", productIdStr);
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/UpdateProduct.jsp";
            }
            
            // Create updated product
            Product updatedProduct = new Product(productId, productName.trim(), unitPrice, quantity);
            
            // Update product in database
            System.out.println("Attempting to update product: " + updatedProduct.toString());
            boolean success = productDAO.updateProduct(updatedProduct);
            System.out.println("Update product result: " + success);
            
            if (success) {
                request.setAttribute("success", "Product updated successfully!");
                // Redirect to product list after successful update
                return "/MainController?action=viewList";
            } else {
                request.setAttribute("error", "Failed to update product. Please try again.");
                request.setAttribute("productId", productIdStr);
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/UpdateProduct.jsp";
            }
            
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Invalid number format");
            return "/ViewProductList.jsp";
        } catch (Exception ex) {
            request.setAttribute("error", "Error updating product: " + ex.getMessage());
            return "/ViewProductList.jsp";
        }
    }
}
