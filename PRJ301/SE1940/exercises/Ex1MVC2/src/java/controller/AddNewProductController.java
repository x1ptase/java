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
 * Add New Product Controller Servlet - handles adding new products to the system
 */
@WebServlet(name = "AddNewProductController", urlPatterns = {"/AddNewProductController"})
public class AddNewProductController extends HttpServlet {
    
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        productDAO=new ProductDAO();
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
                    url = showAddProductForm(request, response);
                    break;
                case "addProduct":
                    url = addNewProduct(request, response);
                    break;
                default:
                    url = showAddProductForm(request, response);
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
     * Show the add product form
     */
    private String showAddProductForm(HttpServletRequest request, HttpServletResponse response) {
        return "/AddNewProduct.jsp";
    }
    
    /**
     * Add new product to the database
     */
    private String addNewProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Get form parameters
            String productName = request.getParameter("productName");
            String unitPriceStr = request.getParameter("unitPrice");
            String quantityStr = request.getParameter("quantity");
            
            // Validate input
            if (productName == null || productName.trim().isEmpty()) {
                request.setAttribute("error", "Product name is required");
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/AddNewProduct.jsp";
            }
            
            if (unitPriceStr == null || unitPriceStr.trim().isEmpty()) {
                request.setAttribute("error", "Unit price is required");
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/AddNewProduct.jsp";
            }
            
            if (quantityStr == null || quantityStr.trim().isEmpty()) {
                request.setAttribute("error", "Quantity is required");
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/AddNewProduct.jsp";
            }
            
            // Parse numeric values
            double unitPrice;
            int quantity;
            
            try {
                unitPrice = Double.parseDouble(unitPriceStr);
                if (unitPrice < 0) {
                    request.setAttribute("error", "Unit price must be a positive number");
                    request.setAttribute("productName", productName);
                    request.setAttribute("unitPrice", unitPriceStr);
                    request.setAttribute("quantity", quantityStr);
                    return "/AddNewProduct.jsp";
                }
            } catch (NumberFormatException ex) {
                request.setAttribute("error", "Unit price must be a valid number");
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/AddNewProduct.jsp";
            }
            
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity < 0) {
                    request.setAttribute("error", "Quantity must be a positive number");
                    request.setAttribute("productName", productName);
                    request.setAttribute("unitPrice", unitPriceStr);
                    request.setAttribute("quantity", quantityStr);
                    return "/AddNewProduct.jsp";
                }
            } catch (NumberFormatException ex) {
                request.setAttribute("error", "Quantity must be a valid number");
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/AddNewProduct.jsp";
            }
            
            // Test database connection first
            System.out.println("Testing database connection...");
            boolean connectionOK = productDAO.testConnection();
            if (!connectionOK) {
                request.setAttribute("error", "Database connection failed. Please check your database configuration.");
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/AddNewProduct.jsp";
            }
            
            // Create new product
            Product newProduct = new Product(productName.trim(), unitPrice, quantity);
            
            // Add product to database
            System.out.println("Attempting to add product: " + newProduct.toString());
            boolean success = productDAO.addProduct(newProduct);
            System.out.println("Add product result: " + success);
            
            if (success) {
                request.setAttribute("success", "Product added successfully!");
                // Redirect to product list after successful addition
                return "/MainController?action=viewList";
            } else {
                String dbError = productDAO.getLastErrorMessage();
                String msg = (dbError != null && !dbError.isEmpty())
                        ? ("Failed to add product: " + dbError)
                        : "Failed to add product. Please check database connection and try again.";
                request.setAttribute("error", msg);
                request.setAttribute("productName", productName);
                request.setAttribute("unitPrice", unitPriceStr);
                request.setAttribute("quantity", quantityStr);
                return "/AddNewProduct.jsp";
            }
            
        } catch (Exception ex) {
            request.setAttribute("error", "Error adding product: " + ex.getMessage());
            return "/AddNewProduct.jsp";
        }
    }
}
