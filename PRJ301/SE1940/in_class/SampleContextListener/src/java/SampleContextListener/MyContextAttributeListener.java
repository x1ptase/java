/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package SampleContextListener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * Web application lifecycle listener.
 *
 * @author x1pta
 */
public class MyContextAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("Add is activated");
        String name=scae.getName();
        String oldValue=scae.getValue().toString();
        String newValue=scae.getServletContext().getAttribute(name).toString();
        System.out.println("Name:" + name + " - odd: " + oldValue + " -new: " + newValue);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("Remove is activated");
        String name=scae.getName();
        String oldValue=scae.getValue().toString();
        System.out.println("Name:" + name + " - odd: " + oldValue);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("Replace is acctivated");
        String name=scae.getName();
        String oldValue=scae.getValue().toString();
        String newValue=scae.getServletContext().getAttribute(name).toString();
        System.out.println("Name: " + name + " -odd: " + oldValue + " -new: " + newValue );
    }
}
