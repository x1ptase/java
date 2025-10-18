package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LearnServlet", urlPatterns = {"/learn"})
public class LearnServlet extends HttpServlet {
    /**
     * BT1: Enter name is admin -> Hello administrator!
     *                  !admin -> Not administrator!
     * 
     * BT2: From dang nhap -> Username - Password
     *      Yes --> Welcome Administrator
     *      No --> ???
     * 
     * BT3: tinh cong tru
     * Flow: Enter 2 number -> Choose phep tinh -> Xu li result -> Return
     */
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String number1=request.getParameter("num1");
        String number2=request.getParameter("num2");
        String operation=request.getParameter("operation");
        
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        try{
            int num1=Integer.parseInt(number1);
            int num2=Integer.parseInt(number2);

            out.print("Result: ");
            if(operation.equals("add")){
                out.print(num1+num2);
            } else{
                out.print(num1-num2);
            }
        } catch(Exception ex){
            out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
