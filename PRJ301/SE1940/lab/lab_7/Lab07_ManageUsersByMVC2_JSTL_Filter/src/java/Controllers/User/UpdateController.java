package Controllers.User;

import Models.DAO.Services.UserService;
import Models.DTO.User;
import Models.DTO.UserError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x1pta
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/update"})
public class UpdateController extends HttpServlet {

    private final String USER_CONTROLLER="UserController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName, password, lastName;
        boolean isAdmin=true, isError=false;
        String message=null;
        String url=USER_CONTROLLER;
        
        try {
            UserService userService=new UserService();
            userName=request.getParameter("txtUserName");
            password=request.getParameter("txtPassword");
            lastName=request.getParameter("txtLastName");
            String admin=request.getParameter("chkIsAdmin");
            UserError userError=new UserError();
            
            // check userName
            if (userName.matches("U\\d{3}") == false){
                userError.setUserNameError("The UserName must be formatted Uxxx, x is digits.");
                isError=true;
            }

            // check password
            if(password.matches(".{3,15}") == false){
                userError.setPasswordError("The Password must be 3 to 15 characters.");
                isError=true;
            } 

            // check lastName
            if(lastName.matches(".{5,50}") == false){
                userError.setLastNameError("The LastName must be 5 to 50 characters.");
                isError=true;
            }

            if(isError == false){
                if(admin == null){
                    isAdmin=false;
                }

                User user=new User(userName, password, lastName, isAdmin);

                if(userService.updateUser(user) == true){
                    message="<b style='color: green'>The user has been updated successfully.</b>";
                } else{
                    message="<b style='color: red'>Something went wrong.</b>";
                }
            } else{
                request.setAttribute("ErrorDetails", userError);
            }

            url=USER_CONTROLLER + "?action=Details&userName=" + userName;
        } catch(Exception ex){
            log(ex.getMessage());
        } finally{
            request.setAttribute("message", message);
            request.getRequestDispatcher(url).forward(request, response);
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
