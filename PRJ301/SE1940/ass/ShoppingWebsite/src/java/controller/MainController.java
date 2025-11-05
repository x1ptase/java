package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {
    
    private static final String ERROR_PAGE="Error.jsp";
    private static final String LOGIN_PAGE="Login.jsp";
    
    private static final String LOGIN_ACTION="Login";
    private static final String LOGOUT_ACTION="Logout";
    
    private static final String VIEW_PRODUCT_ACTION="ViewProduct";
    private static final String VIEW_DETAILS_ACTION="ViewDetails"; // view 1 product
    private static final String CREATE_PAGE_ACTION = "CreatePage"; // Key: Chuyển đến form tạo mới (GET)
    private static final String CREATE_PRODUCT_ACTION = "CreateProduct"; // Key: Gửi form tạo mới (POST)
    private static final String UPDATE_PRODUCT_ACTION = "UpdateProduct"; // Key: Chỉnh sửa/Cập nhật (POST)
    private static final String UPDATE_PAGE_ACTION = "UpdatePage"; // Key: Hiển thị form update (GET)
    private static final String DELETE_PRODUCT_ACTION = "DeleteProduct"; // Key: Xóa (POST)

    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String VIEW_PRODUCT_CONTROLLER = "ViewProductController";
    private static final String CREATE_PRODUCT_CONTROLLER = "CreateProductController"; // Dùng cho cả GET (form) & POST (xử lý)
    private static final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";
    private static final String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    private static final String VIEW_DETAILS_CONTROLLER = "ViewDetailsController";
    private static final String VIEW_PIZZA_LIST_CONTROLLER = "ViewPizzaListController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR_PAGE;
        
        try{
            String action=request.getParameter("action"); 
            if(action == null){
                url=LOGIN_PAGE;
            } else if(action.equals(LOGIN_ACTION)){ 
                url=LOGIN_CONTROLLER;
            } else if(action.equals(LOGOUT_ACTION)){
                url=LOGOUT_CONTROLLER;
            } else if(action.equals(VIEW_PRODUCT_ACTION)){
                url=VIEW_PRODUCT_CONTROLLER;
            } else if(action.equals(VIEW_DETAILS_ACTION)) {
                url=VIEW_DETAILS_CONTROLLER;
            } else if (action.equals(CREATE_PAGE_ACTION)) {
                // Xử lý GET (Hiển thị form tạo mới)
                url = CREATE_PRODUCT_CONTROLLER; 
            } else if (action.equals(CREATE_PRODUCT_ACTION)) {
                // Xử lý POST (Xử lý dữ liệu tạo mới)
                url = CREATE_PRODUCT_CONTROLLER; 
            } else if (action.equals(UPDATE_PAGE_ACTION)) {
                // Hiển thị form cập nhật
                url = UPDATE_PRODUCT_CONTROLLER;
            } else if (action.equals(UPDATE_PRODUCT_ACTION)) {
                // Xử lý POST (Chỉnh sửa)
                url = UPDATE_PRODUCT_CONTROLLER;
            } else if (action.equals(DELETE_PRODUCT_ACTION)) {
                // Xử lý POST (Xóa)
                url = DELETE_PRODUCT_CONTROLLER;
            } else if ("ViewAllPizzas".equals(action)) {
                url = VIEW_PIZZA_LIST_CONTROLLER;
            } else{
                url=ERROR_PAGE;
            }
            
        } catch(Exception ex){
            log("Error at MainController: " + ex.toString());
            request.setAttribute("msg", "Error syntax");
            url=ERROR_PAGE;
        } finally{
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