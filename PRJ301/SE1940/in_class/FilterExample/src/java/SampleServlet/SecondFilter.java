/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package SampleServlet;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author x1pta
 */
public class SecondFilter implements Filter {
    private FilterConfig filterConfig = null; 
    public SecondFilter() {
    }   
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            System.out.println("Pass Second Filter - request");
            request.setAttribute("KEY1", "Second Filter");
            chain.doFilter(request, response);
            System.out.println("Pass Second Filter - response");
            request.setAttribute("KEY1", "Second Filter Again");
            PrintWriter out=response.getWriter();
            out.println("</br> Welcome to Filter in Java Web");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void destroy() {        
    }
    public void init(FilterConfig filterConfig) {         
    }
}
