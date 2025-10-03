/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Cart;

import Models.DAO.BookDAO;
import Models.Entities.Book;
import Models.Entities.CartItem;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author x1pta
 */
public class AddCartController extends HttpServlet {
    private final String bookController="BookCOntroller";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=null;
        String message, bookId;
        Book seletedBook=null;
        CartItem item=null;
        HashMap<String, CartItem> itemsInCart=null;
        try{
            BookDAO bookDAO=new BookDAO();
            HttpSession shoppingCart=request.getSession();
            itemsInCart=(HashMap<String, CartItem>) shoppingCart.getAttribute("Cart");
            bookId=request.getParameter("BookId");
            seletedBook=bookDAO.getBookById(bookId);
            if(itemsInCart == null){
                itemsInCart=new HashMap<String, CartItem>();
                shoppingCart.setAttribute("Cart", itemsInCart);
            }
            item=itemsInCart.get(seletedBook.getId());
            if(item == null){
                item=new CartItem(seletedBook.getId(),
                    seletedBook.getTitle(), 1, seletedBook.getUnitPrice());
                itemsInCart.put(item.getItemName(), item);
                message="The book " + item.getItemName() + 
                        "has been added to cart successfully.";
            } else{
                item.setQuantity(item.getQuantity() + 1);
                message="The cart has been updated successfully.";
            }
            url=bookController + "?action=ViewBookList";
            request.setAttribute("Message", "<h4>" + message + "<h4>");
       } catch(Exception ex){
            log("AddCartController has error:" + ex.getMessage());
        } finally{
            RequestDispatcher rd=request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
