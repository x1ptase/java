package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/main"})
public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String num1_raw=request.getParameter("num1");
        String num2_raw=request.getParameter("num2");
        String operation=request.getParameter("operation");
        double res=0;
        try{
            double n1=Double.parseDouble(num2_raw);
            double n2=Double.parseDouble(num2_raw);
            
            switch(operation){
                case "+":
                    res=n1+n2;
                    break;
                case "-":
                    res=n1-n2;
                    break;
                case "*":
                    res=n1*n2;
                    break;
                case "/":
                    if(n2 != 0){
                        res=n1/n2;
                    } else{
                        System.out.println("Enter n != 0");
                    }
                    break;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
