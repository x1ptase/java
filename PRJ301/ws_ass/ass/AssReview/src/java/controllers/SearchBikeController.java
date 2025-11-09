package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.tbBikeDAO;
import models.tbBikeDTO;
import models.tbAccountDTO;

public class SearchBikeController extends HttpServlet {

    private static final String BIKE_LIST_PAGE = "bikeList.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/" + LOGIN_PAGE);
            return;
        }
        
        tbAccountDTO account = (tbAccountDTO) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/" + LOGIN_PAGE);
            return;
        }
        
        try {
            String keyword = request.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
            
            tbBikeDAO dao = new tbBikeDAO();
            List<tbBikeDTO> bikeList = dao.searchBikeByName(keyword.trim());
            
            request.setAttribute("bikeList", bikeList);
            request.setAttribute("keyword", keyword);
            
        } catch (Exception ex) {
            log("Error at SearchBikeController: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher(BIKE_LIST_PAGE).forward(request, response);
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

