package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "RandomController", urlPatterns = {"/random"})
public class RandomController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RandomController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RandomController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String number_raw=request.getParameter("number");
        String message="";
        boolean flag=false;
        try{
            int number=Integer.parseInt(number_raw);
            if(number >= 1 && number <= 5){
                int randomNumber=(int)(Math.random()*5)+1;
                if(randomNumber == number){
                    message="You are lucky member !!";
                    // dong goi
                    flag=true;
                } else{
                    message="You are unlucky number";
                }
            } else{
                message="Please enter number 1 to 5";
            }
            request.setAttribute("message", message);
            if(flag){
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else{
                request.setAttribute("message", message);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch(Exception e){
            e.getMessage();
        }
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
