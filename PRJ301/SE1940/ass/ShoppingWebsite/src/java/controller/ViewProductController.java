package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AccountDTO;
import model.ProductDAO;
import model.ProductDTO;

public class ViewProductController extends HttpServlet {

    private static final String ERROR_PAGE="Error.jsp";
    private static final String LOGIN_PAGE="Login.jsp";
    private static final String VIEW_PAGE="View.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR_PAGE; // Mặc định là trang lỗi
        
        try{
            // check qtruy cap
            HttpSession session=request.getSession(false); // Dùng false để không tạo session mới
            AccountDTO account=(AccountDTO) (session != null ? session.getAttribute("account") : null);
            
            // Nếu không đăng nhập hoặc không phải Admin
            if(account == null || !account.isType()){
                response.sendRedirect(LOGIN_PAGE);
                return;
            }
            
            // get data
            ProductDAO dao=new ProductDAO();
            // LƯU Ý: viewAllProducts() ném ra Exception, nên DAO cần được đặt trong try-catch
            List<ProductDTO> productList=dao.viewAllProducts(); 
            
            // put data into request & chuyen huong
            request.setAttribute("PRODUCT_LIST", productList);
            url=VIEW_PAGE; // Chỉ chuyển về VIEW_PAGE khi thành công
                    
        } catch(Exception ex){
            log("Error at ViewProductController: " + ex.getMessage());
            // SỬA LỖI: Đặt thông báo lỗi vào request scope
            request.setAttribute("msg", "Lỗi tải dữ liệu: " + ex.getMessage()); 
            url = ERROR_PAGE; // Giữ nguyên ERROR_PAGE
        } finally{
            // Chỉ forward khi chưa có redirect (vì sendRedirect đã đóng response)
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