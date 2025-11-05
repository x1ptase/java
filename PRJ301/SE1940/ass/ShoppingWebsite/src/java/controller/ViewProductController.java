package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDTO;
import model.ProductDAO;
import model.ProductDTO;

public class ViewProductController extends HttpServlet {

    private static final String ERROR_PAGE="Error.jsp";
    private static final String LOGIN_PAGE="Login.jsp";
    private static final String VIEW_PAGE="View.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR_PAGE; 
        
        try{
            // check qtruy cap
            HttpSession session=request.getSession(false); // Dùng false để không tạo session mới
            AccountDTO account=(AccountDTO) (session != null ? session.getAttribute("account") : null);
            
            // no login & not admin
            if(account == null || !account.isType()){
                response.sendRedirect(request.getContextPath()+"/"+LOGIN_PAGE);
                return;
            }
            
            // get data
            ProductDAO dao=new ProductDAO();
            List<ProductDTO> productList=dao.viewAllProducts(); // throw ex so put in trycatch
            
            // put data into request & chuyen huong
            request.setAttribute("PRODUCT_LIST", productList);
            url=VIEW_PAGE;
                    
        } catch(Exception ex){
            log("Error at ViewProductController: " + ex.getMessage());
            url=ERROR_PAGE;
        } finally{
            if(!response.isCommitted()){
                request.getRequestDispatcher(url).forward(request, response);
            }
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