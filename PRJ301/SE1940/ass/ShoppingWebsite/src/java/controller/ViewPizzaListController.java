package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDTO;
import model.ProductDAO;
import model.ProductDTO;

public class ViewPizzaListController extends HttpServlet {

    private static final String LOGIN_PAGE="Login.jsp";
    private static final String VIEW_PIZZA_LIST_PAGE="ViewPizzaList.jsp";

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HttpSession session=request.getSession(false);
            AccountDTO account=(AccountDTO) (session != null ? session.getAttribute("account") : null);
            if(account == null){
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            }

            ProductDAO dao=new ProductDAO();
            List<ProductDTO> list=dao.viewAllProducts();
            request.setAttribute("PRODUCT_LIST", list);
            request.getRequestDispatcher(VIEW_PIZZA_LIST_PAGE).forward(request, response);
        } catch(Exception ex){
            log("Error at ViewPizzaListController: " + ex.getMessage());
        }
    }
}


