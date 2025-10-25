/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package SampleContextListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author x1pta
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context in My Context is invoked.");
        sce.getServletContext().setAttribute("CONTEXT", "ADD");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destriy in My Context is invoked.");
        sce.getServletContext().removeAttribute("CONTEXT");
    }
}
