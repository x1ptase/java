package SampleSessionListener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author x1pta
 */
public class MySessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int count=(int)se.getSession().getServletContext().getAttribute("COUNT");
        count+=1;
        se.getSession().getServletContext().setAttribute("COUNT", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("SessionDestroyed is invoked.");
    }
}
