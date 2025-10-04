/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.CartDTO;
import sample.product.ProductDTO;

/**
 *
 * @author x1pta
 */
public class AddToCartController extends HttpServlet {
    private static final String SUCCESS="viewProduct.jsp";
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
        
        try{
            String cmbProduct=request.getParameter("cmbName");
            String tmp[]=cmbProduct.split("-");
            String id=tmp[0];
            String name=tmp[1];
            double price=Double.parseDouble(tmp[2]);
            int quantity=Integer.parseInt(request.getParameter("cmbQuantity"));
            HttpSession session=request.getSession();
            CartDTO cart=(CartDTO) session.getAttribute("CART");
            if(cart == null){
                cart=new CartDTO();
            }
            boolean check=cart.add(new ProductDTO(id, name, price, quantity));
            if(check){
                session.setAttribute("CART", cart);
                request.setAttribute("MESSAGE", "You added " + tmp[1] + ". quantity: " + quantity);
                url=SUCCESS;
            }
        } catch(Exception ex){
            log("Error at AddToCartController:" + ex.toString());
            request.setAttribute("ERROR", "Error occurred while adding to cart: " + ex.getMessage());
            url = SUCCESS; // Set default URL in case of error
        } finally{
            if(url != null){
                RequestDispatcher rd=request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                // Fallback if URL is still null
                response.sendRedirect("viewProduct.jsp");
            }
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
