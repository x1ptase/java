/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductDTO;
import services.ProductService;

/**
 *
 * @author x1pta
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> ...9 lines */
    protected void processRequest(HttpServletRequest request,
                                HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductService productService = new ProductService();
        List<ProductDTO> products = productService.getAll();
        request.setAttribute("productList", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
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
        String action = request.getParameter("action");
        if (action == null) action = "";

        ProductService productService = new ProductService();
        try {
            switch (action) {
                case "add": {
                    String idStr = request.getParameter("id");
                    String name = request.getParameter("name");
                    String priceStr = request.getParameter("unitPrice");
                    String qtyStr = request.getParameter("quantity");
                    if (idStr != null && name != null && priceStr != null && qtyStr != null) {
                        ProductDTO p = new ProductDTO();
                        p.setId(Integer.parseInt(idStr));
                        p.setName(name);
                        p.setUnitPrice(Float.parseFloat(priceStr));
                        p.setQuantity(Integer.parseInt(qtyStr));
                        productService.add(p);
                    }
                    break;
                }
                case "update": {
                    String idStr = request.getParameter("id");
                    String name = request.getParameter("name");
                    String priceStr = request.getParameter("unitPrice");
                    String qtyStr = request.getParameter("quantity");
                    if (idStr != null && name != null && priceStr != null && qtyStr != null) {
                        ProductDTO p = new ProductDTO();
                        p.setId(Integer.parseInt(idStr));
                        p.setName(name);
                        p.setUnitPrice(Float.parseFloat(priceStr));
                        p.setQuantity(Integer.parseInt(qtyStr));
                        productService.update(p);
                    }
                    break;
                }
                case "delete": {
                    String idStr = request.getParameter("id");
                    if (idStr != null) {
                        int id = Integer.parseInt(idStr);
                        ProductDTO p = productService.searchById(id);
                        if (p != null) {
                            productService.delete(p);
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            productService.close();
        }

        response.sendRedirect(request.getContextPath() + "/ProductServlet");
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
