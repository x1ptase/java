package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductDAO;
import model.ProductDTO;

public class CreateProductController extends HttpServlet {

    private static final String VIEW_PRODUCT_CONTROLLER="ViewProductController";
    private static final String CREATE_PAGE="Create.jsp";
    private static final String ERROR_PAGE="Error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url;

        if(!"POST".equalsIgnoreCase(request.getMethod())){
            request.getRequestDispatcher(CREATE_PAGE).forward(request, response);
            return;
        }

        try {
            // Lấy dữ liệu từ form
            int productID=parseIntParam(request, "txtProductID");
            String productName=trimToNull(request.getParameter("txtProductName"));
            int supplierID=parseIntParam(request, "txtSupplierID");
            int categoryID=parseIntParam(request, "txtCategoryID");
            String quantityPerUnit=trimToNull(request.getParameter("txtQuantityPerUnit"));
            float unitPrice=parseFloatParam(request, "txtUnitPrice");
            String productImage=normalizeImagePath(request, request.getParameter("txtProductImage"));

            ProductDTO newProduct=new ProductDTO(
                productID, productName, supplierID, categoryID, quantityPerUnit, unitPrice, productImage
            );

            boolean created=new ProductDAO().createProduct(newProduct);

            if(created){
                request.setAttribute("msg", "Create new product " + productName + " success");
                url=VIEW_PRODUCT_CONTROLLER;
            } else{
                request.setAttribute("msg", "Crate new product invalid. Pls check DB");
                url=CREATE_PAGE;
            }
        } catch(NumberFormatException nfe) {
            request.setAttribute("msg", "DB invalid: " + nfe.getMessage());
            url=CREATE_PAGE;
        } catch(Exception ex){
            log("Error at CreateProductController" + ex.getMessage());
            url=CREATE_PAGE;
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

    
    // chuan hoa path
    private static String normalizeImagePath(HttpServletRequest request, String productImage) {
        if(productImage == null) return null;
        String value=productImage.trim();
        String ctx=request.getContextPath();
        String prefixCtx=ctx + "/resource/";
        if(value.startsWith(prefixCtx)){
            return value.substring(prefixCtx.length());
        }
        if(value.startsWith("/resource/")){
            return value.substring("/resource/".length());
        }
        if(value.startsWith("/")){
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
        if(s == null) return null;
        String v=s.trim();
        return v.isEmpty() ? null : v;
    }
}