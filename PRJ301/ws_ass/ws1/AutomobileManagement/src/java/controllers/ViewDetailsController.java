package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.CarsDAO;
import models.CarsDTO;

public class ViewDetailsController extends HttpServlet {

    private static final String ERROR_PAGE="Error.jsp";
    private static final String DETAILS_PAGE="ViewDetails.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR_PAGE;
        
        try{
            String idRaw=request.getParameter("carID");
            int carID=Integer.parseInt(idRaw);
            
            CarsDAO dao=new CarsDAO();
            CarsDTO cars=dao.findById(carID);
            if(cars == null){
                request.setAttribute("msg", "CarID not found: ID=" + carID);
                url=ERROR_PAGE;
            } else{
                request.setAttribute("car", cars);
                url=DETAILS_PAGE;
            }
        } catch(Exception ex){
            log("Error at ViewDetailsController: " + ex.getMessage());
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
