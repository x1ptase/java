package controller;

import dao.ProductDAO;
import model.Product;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main Controller Servlet - handles all requests using MVC2 pattern
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    
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
     * Process all requests based on action parameter
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewList";
        }
        
        String url = "";
        
        try {
            switch (action) {
                case "viewList":
                    url = viewProductList(request, response);
                    break;
                case "viewDetail":
                    url = viewProductDetail(request, response);
                    break;
                case "addProduct":
                    url = addProduct(request, response);
                    break;
                case "createNew":
                    url = showAddProductForm(request, response);
                    break;
                default:
                    url = viewProductList(request, response);
                    break;
            }
        } catch (Exception e) {
           log("Error:" + e.getMessage());
           request.setAttribute("error", "Unexpected error: " + e.getMessage());
           url = "/ViewProductList.jsp";
        }
        
        if (url != null && !url.isEmpty()) {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    /**
     * View product list
     */
    private String viewProductList(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Product> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            return "/ViewProductList.jsp";
        } catch (Exception e) {
            request.setAttribute("error", "Error loading product list: " + e.getMessage());
            return "/ViewProductList.jsp";
        }
    }
    
    /**
     * View product detail
     */
    private String viewProductDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productIdStr = request.getParameter("productId");
            if (productIdStr != null && !productIdStr.isEmpty()) {
                int productId = Integer.parseInt(productIdStr);
                Product product = productDAO.getProductById(productId);
                if (product != null) {
                    request.setAttribute("product", product);
                    return "/DetailProduct.jsp";
                } else {
                    request.setAttribute("error", "Product not found");
                    return "/ViewProductList.jsp";
                }
            } else {
                request.setAttribute("error", "Product ID is required");
                return "/ViewProductList.jsp";
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid product ID format");
            return "/ViewProductList.jsp";
        } catch (Exception e) {
            request.setAttribute("error", "Error loading product details: " + e.getMessage());
            return "/ViewProductList.jsp";
        }
    }
    
    /**
     * Add new product
     */
    private String addProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productName = request.getParameter("productName");
            String unitPriceStr = request.getParameter("unitPrice");
            String quantityStr = request.getParameter("quantity");
            
            if (productName != null && unitPriceStr != null && quantityStr != null) {
                double unitPrice = Double.parseDouble(unitPriceStr);
                int quantity = Integer.parseInt(quantityStr);
                
                // Validate input
                if (productName.trim().isEmpty()) {
                    request.setAttribute("error", "Product name cannot be empty");
                    return "/ViewProductList.jsp";
                }
                if (unitPrice < 0) {
                    request.setAttribute("error", "Unit price cannot be negative");
                    return "/ViewProductList.jsp";
                }
                if (quantity < 0) {
                    request.setAttribute("error", "Quantity cannot be negative");
                    return "/ViewProductList.jsp";
                }
                
                Product product = new Product(productName.trim(), unitPrice, quantity);
                boolean success = productDAO.addProduct(product);
                
                if (success) {
                    request.setAttribute("message", "Product added successfully!");
                    return "/AddNewProduct.jsp";
                } else {
                    request.setAttribute("error", "Failed to add product");
                    return "/ViewProductList.jsp";
                }
            } else {
                request.setAttribute("error", "All fields are required");
                return "/ViewProductList.jsp";
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input format. Please check your input values.");
            return "/ViewProductList.jsp";
        } catch (Exception e) {
            request.setAttribute("error", "Error adding product: " + e.getMessage());
            return "/ViewProductList.jsp";
        }
    }
    
    /**
     * Show add product form
     */
    private String showAddProductForm(HttpServletRequest request, HttpServletResponse response) {
        return "/AddNewProduct.jsp";
    }

}
