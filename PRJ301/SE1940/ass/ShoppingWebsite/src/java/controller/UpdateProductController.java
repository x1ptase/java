package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductDAO;
import model.ProductDTO;

public class UpdateProductController extends HttpServlet {

    private static final String VIEW_PRODUCT_CONTROLLER = "ViewProductController";
    private static final String ERROR_PAGE = "Error.jsp";
    private static final String UPDATE_PAGE = "Update.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        
        // Xử lý POST request từ form chỉnh sửa
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                // 1. Lấy dữ liệu từ form (bao gồm cả ID để biết sửa cái nào)
                int productID = Integer.parseInt(request.getParameter("txtProductID")); 
                String productName = request.getParameter("txtProductName");
                int supplierID = Integer.parseInt(request.getParameter("txtSupplierID"));
                int categoryID = Integer.parseInt(request.getParameter("txtCategoryID"));
                String quantityPerUnit = request.getParameter("txtQuantityPerUnit");
                float unitPrice = Float.parseFloat(request.getParameter("txtUnitPrice"));
                String productImage = request.getParameter("txtProductImage");
                // Normalize image path to relative under resource/
                if (productImage != null) {
                    productImage = productImage.trim();
                    String ctx = request.getContextPath();
                    String prefixCtx = ctx + "/resource/";
                    if (productImage.startsWith(prefixCtx)) {
                        productImage = productImage.substring(prefixCtx.length());
                    } else if (productImage.startsWith("/resource/")) {
                        productImage = productImage.substring("/resource/".length());
                    } else if (productImage.startsWith("/")) {
                        productImage = productImage.substring(1);
                    }
                }
                
                // 2. Tạo DTO và gọi DAO
                ProductDTO updatedProduct = new ProductDTO(
                    productID, productName, supplierID, categoryID, quantityPerUnit, unitPrice, productImage
                );
                
                ProductDAO dao = new ProductDAO();
                boolean check = dao.updateProduct(updatedProduct);

                // 3. Xử lý kết quả
                if (check) {
                    request.setAttribute("msg", "Cập nhật sản phẩm ID " + productID + " thành công!");
                    url = VIEW_PRODUCT_CONTROLLER; // Chuyển đến trang xem danh sách
                } else {
                    request.setAttribute("msg", "Cập nhật thất bại. Sản phẩm ID " + productID + " không tồn tại.");
                    url = VIEW_PRODUCT_CONTROLLER; // Quay lại trang xem danh sách
                }

            } catch (SQLException ex) {
                log("Database Error at UpdateProductController: " + ex.getMessage());
                request.setAttribute("msg", "Lỗi CSDL khi cập nhật sản phẩm. Mã NCC/DM không hợp lệ.");
                // Tùy chọn: Chuyển hướng về lại trang Update form (Update.jsp) nếu bạn có nó
            } catch (Exception ex) {
                log("General Error at UpdateProductController: " + ex.getMessage());
                request.setAttribute("msg", "Lỗi nhập liệu hoặc hệ thống không mong muốn.");
            }
        } else { // GET: Hiển thị form cập nhật
            try {
                int productID = Integer.parseInt(request.getParameter("productID"));
                ProductDAO dao = new ProductDAO();
                ProductDTO product = dao.findById(productID);
                if (product == null) {
                    request.setAttribute("msg", "Product not found: ID=" + productID);
                    url = ERROR_PAGE;
                } else {
                    request.setAttribute("PRODUCT_EDIT", product);
                    url = UPDATE_PAGE;
                }
            } catch (Exception ex) {
                log("Error at UpdateProductController (GET): " + ex.getMessage());
                request.setAttribute("msg", "Unexpected error while loading product to edit.");
            }
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
}