package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductDAO;
import model.ProductDTO;

public class UpdateProductController extends HttpServlet {

    private static final String VIEW_PRODUCT_CONTROLLER="ViewProductController";
    private static final String ERROR_PAGE="Error.jsp";
    private static final String UPDATE_PAGE="Update.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url;

        // GET: hiển thị form cập nhật
        if(!"POST".equalsIgnoreCase(request.getMethod())){
            try{
                int productID=parseIntParam(request, "productID");
                ProductDTO product=new ProductDAO().findById(productID);
                if(product == null){
                    request.setAttribute("msg", "Product not found: ID=" + productID);
                    url=ERROR_PAGE;
                } else{
                    request.setAttribute("PRODUCT_EDIT", product);
                    url=UPDATE_PAGE;
                }
            } catch(NumberFormatException nfe){
                request.setAttribute("msg", "DB invalid: " + nfe.getMessage());
                url=ERROR_PAGE;
            } catch(Exception ex){
                log("Error at UpdateProductController" + ex.getMessage());
                url=ERROR_PAGE;
            }
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        // POST: xử lý cập nhật
        try{
            int productID=parseIntParam(request, "txtProductID");
            String productName=trimToNull(request.getParameter("txtProductName"));
            int supplierID=parseIntParam(request, "txtSupplierID");
            int categoryID=parseIntParam(request, "txtCategoryID");
            String quantityPerUnit=trimToNull(request.getParameter("txtQuantityPerUnit"));
            float unitPrice=parseFloatParam(request, "txtUnitPrice");
            String productImage=normalizeImagePath(request, request.getParameter("txtProductImage"));

            ProductDTO updatedProduct=new ProductDTO(
                productID, productName, supplierID, categoryID, quantityPerUnit, unitPrice, productImage
            );

            boolean updated=new ProductDAO().updateProduct(updatedProduct);
            if(updated){
                request.setAttribute("msg", "Update product " + productID + " success");
                url=VIEW_PRODUCT_CONTROLLER;
            } else{
                request.setAttribute("msg", "Update product invalid. Pls check DB");
                url=UPDATE_PAGE;
            }
        } catch(NumberFormatException nfe){
            request.setAttribute("msg", "DB invalid: " + nfe.getMessage());
            url=UPDATE_PAGE;
        } catch(Exception ex){
            log("Error at UpdateProductController" + ex.getMessage());
            url=UPDATE_PAGE;
        }

        request.getRequestDispatcher(url).forward(request, response);
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

    private static String normalizeImagePath(HttpServletRequest request, String productImage) {
        if (productImage == null) return null;
        String value = productImage.trim();
        String ctx = request.getContextPath();
        String prefixCtx = ctx + "/resource/";
        if (value.startsWith(prefixCtx)) {
            return value.substring(prefixCtx.length());
        }
        if (value.startsWith("/resource/")) {
            return value.substring("/resource/".length());
        }
        if (value.startsWith("/")) {
            return value.substring(1);
        }
        return value;
    }

    private static int parseIntParam(HttpServletRequest request, String name) {
        return Integer.parseInt(request.getParameter(name));
    }

    private static float parseFloatParam(HttpServletRequest request, String name) {
        return Float.parseFloat(request.getParameter(name));
    }

    private static String trimToNull(String s) {
        if (s == null) return null;
        String v = s.trim();
        return v.isEmpty() ? null : v;
    }
}