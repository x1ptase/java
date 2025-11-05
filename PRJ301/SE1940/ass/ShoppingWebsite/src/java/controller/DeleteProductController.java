package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductDAO;

public class DeleteProductController extends HttpServlet {

    private static final String VIEW_PRODUCT_CONTROLLER="ViewProductController";
    private static final String ERROR_PAGE="Error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR_PAGE;
        
        try{
            // get ProductID from request
            int productID=Integer.parseInt(request.getParameter("productID"));
            
            // call dao
            ProductDAO dao=new ProductDAO();
            boolean check=dao.delete(productID);
            
            if(check){
                request.setAttribute("msg", "Delete product ID " + productID + " successfully");
            } else{
                request.setAttribute("msg", "Delete product failed. ID " + productID + " not exist");
            }
            
            // fw ve trang view de user xem kq
            url=VIEW_PRODUCT_CONTROLLER; 

        } catch(Exception ex) {
            log("Error at DeleteProductController: " + ex.getMessage());
        } finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
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
}