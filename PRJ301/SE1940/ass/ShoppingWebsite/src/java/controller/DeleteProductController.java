package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductDAO;

public class DeleteProductController extends HttpServlet {

    private static final String VIEW_PRODUCT_CONTROLLER = "ViewProductController";
    private static final String ERROR_PAGE = "Error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        
        try {
            // 1. Lấy ProductID từ request
            int productID = Integer.parseInt(request.getParameter("productID")); // Được gửi từ form POST/GET
            
            // 2. Gọi DAO
            ProductDAO dao = new ProductDAO();
            boolean check = dao.deleteProduct(productID);

            // 3. Xử lý kết quả
            if (check) {
                request.setAttribute("msg", "Xóa sản phẩm ID " + productID + " thành công.");
            } else {
                request.setAttribute("msg", "Xóa sản phẩm thất bại. ID " + productID + " không tồn tại.");
            }
            
            // Luôn chuyển về trang xem danh sách để người dùng thấy kết quả
            url = VIEW_PRODUCT_CONTROLLER; 

        } catch (SQLException ex) {
            log("Database Error at DeleteProductController: " + ex.getMessage());
            request.setAttribute("msg", "Lỗi CSDL: Không thể xóa sản phẩm. Có thể nó đang được tham chiếu trong OrderDetails.");
        } catch (Exception ex) {
            log("General Error at DeleteProductController: " + ex.getMessage());
            request.setAttribute("msg", "Lỗi hệ thống không mong muốn khi xóa sản phẩm.");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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