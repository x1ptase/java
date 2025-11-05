package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDTO;
import model.ProductDAO;
import model.ProductDTO;

public class ViewDetailsController extends HttpServlet {

    private static final String ERROR_PAGE="Error.jsp";
    private static final String LOGIN_PAGE="Login.jsp";
    private static final String DETAILS_PAGE="ViewDetails.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url=ERROR_PAGE;
        
        try{
            HttpSession session=request.getSession(false);
            AccountDTO account=(AccountDTO) (session != null ? session.getAttribute("account") : null);
            if(account == null){
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            }

            String idRaw = request.getParameter("productID");
            int productId = Integer.parseInt(idRaw);

            ProductDAO dao = new ProductDAO();
            ProductDTO product = dao.findById(productId);
            if (product == null) {
                request.setAttribute("msg", "Product not found: ID=" + productId);
                url = ERROR_PAGE;
            } else {
                request.setAttribute("PRODUCT_DETAIL", product);
                url = DETAILS_PAGE;
            }
        } catch (Exception ex) {
            log("Error at ViewDetailsController: " + ex.getMessage());
            request.setAttribute("msg", "Unexpected error while loading product details.");
        } finally {
            if (!response.isCommitted()) {
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


