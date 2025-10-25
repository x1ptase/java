package SampleSessionListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("COUNT", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ContextDestroyed is invoked...");
    }
    
}
