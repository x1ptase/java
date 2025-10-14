package controller;

import dao.ProductDAO;
import model.Product;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewProductController", urlPatterns = {"/ViewProductController"})
public class ViewProductController extends HttpServlet {
    
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action == null){
            action="viewList";
        }
        
        String url="";
        try{
            switch(action){
                case "viewList":
                    url=viewProductList(request, response);
                    break;
                case "viewDetail":
                    url=viewProductDetail(request, response);
                    break;
                default:
                    url=viewProductList(request, response);
                    break;
            }
        } catch(Exception ex){
            request.setAttribute("error", "Error: " + ex.getMessage());
        } finally{
            RequestDispatcher rd=request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }
    
    private String viewProductList(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Product> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            return "/viewProductList.jsp";
        } catch (Exception e) {
            request.setAttribute("error", "Error loading product list: " + e.getMessage());
            return "/error.jsp";
        }
    }
    
    private String viewProductDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String productIdStr = request.getParameter("productId");
            if (productIdStr != null && !productIdStr.isEmpty()) {
                int productId = Integer.parseInt(productIdStr);
                Product product = productDAO.getProductById(productId);
                if (product != null) {
                    request.setAttribute("product", product);
                    return "/detailProduct.jsp";
                } else {
                    request.setAttribute("error", "Product not found");
                    return "/error.jsp";
                }
            } else {
                request.setAttribute("error", "Product ID is required");
                return "/error.jsp";
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid product ID format");
            return "/error.jsp";
        } catch (Exception e) {
            request.setAttribute("error", "Error loading product details: " + e.getMessage());
            return "/error.jsp";
        }
    }
}