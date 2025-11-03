package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductDAO;
import model.ProductDTO;

public class CreateProductController extends HttpServlet {

    private static final String VIEW_PRODUCT_CONTROLLER = "ViewProductController";
    private static final String CREATE_PAGE = "Create.jsp";
    private static final String ERROR_PAGE = "Error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        
        // Chỉ xử lý POST request từ form
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                // 1. Lấy dữ liệu từ form (Cần ProductID nếu nó không phải IDENTITY)
                int productID = Integer.parseInt(request.getParameter("txtProductID")); // Cần thiết nếu không dùng IDENTITY
                String productName = request.getParameter("txtProductName");
                int supplierID = Integer.parseInt(request.getParameter("txtSupplierID"));
                int categoryID = Integer.parseInt(request.getParameter("txtCategoryID"));
                String quantityPerUnit = request.getParameter("txtQuantityPerUnit");
                float unitPrice = Float.parseFloat(request.getParameter("txtUnitPrice"));
                String productImage = request.getParameter("txtProductImage");
                
                // 2. Tạo DTO và gọi DAO
                ProductDTO newProduct = new ProductDTO(
                    productID, productName, supplierID, categoryID, quantityPerUnit, unitPrice, productImage
                );
                
                ProductDAO dao = new ProductDAO();
                boolean check = dao.createProduct(newProduct);

                // 3. Xử lý kết quả
                if (check) {
                    request.setAttribute("msg", "Tạo sản phẩm " + productName + " thành công!");
                    url = VIEW_PRODUCT_CONTROLLER; // Chuyển đến trang xem danh sách
                } else {
                    request.setAttribute("msg", "Tạo sản phẩm thất bại. Vui lòng kiểm tra dữ liệu.");
                    url = CREATE_PAGE; // Quay lại form
                }

            } catch (SQLException ex) {
                log("Database Error at CreateProductController: " + ex.getMessage());
                request.setAttribute("msg", "Lỗi CSDL: Không thể tạo sản phẩm. Mã ID có thể bị trùng.");
            } catch (Exception ex) {
                log("General Error at CreateProductController: " + ex.getMessage());
                request.setAttribute("msg", "Lỗi nhập liệu hoặc hệ thống không mong muốn.");
                url = CREATE_PAGE; // Quay lại form để người dùng sửa lỗi
            }
        } else {
            url = CREATE_PAGE; // Nếu là GET, chỉ hiển thị form
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