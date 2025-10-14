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

/**
 * Main Controller Servlet - handles all requests using MVC2 pattern
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    
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
     * Process all requests based on action parameter
     */
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
           log("Error:" + ex.getMessage());
           request.setAttribute("error", "Unexpected error: " + ex.getMessage());
           url="/ViewProductList.jsp";
        } finally{
            RequestDispatcher rd=request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }
    
    /**
     * View product list
     */
    private String viewProductList(HttpServletRequest request, HttpServletResponse response){
        try{
            List<Product> products=productDAO.getAllProducts();
            request.setAttribute("products", products);
            return "/ViewProductList.jsp";
        } catch(Exception ex){
            request.setAttribute("error", "Error loading product list: " + ex.getMessage());
            return "/ViewProductList.jsp";
        }
    }
    
    /**
     * View product detail
     */
    private String viewProductDetail(HttpServletRequest request, HttpServletResponse response){
        try{
            String productIdStr=request.getParameter("productId");
            if(productIdStr != null && !productIdStr.isEmpty()){
                int productId=Integer.parseInt(productIdStr);
                Product product=productDAO.getProductById(productId);
                if(product != null){
                    request.setAttribute("product", product);
                    return "/DetailProduct.jsp";
                } else{
                    request.setAttribute("error", "Product not found");
                    return "/ViewProductList.jsp";
                }
            } else{
                request.setAttribute("error", "Product ID is required");
                return "/ViewProductList.jsp";
            }
        } catch(NumberFormatException ex){
            request.setAttribute("error", "Invalid product ID format");
            return "/ViewProductList.jsp";
        } catch(Exception ex){
            request.setAttribute("error", "Error loading product details: " + ex.getMessage());
            return "/ViewProductList.jsp";
        }
    }
}
