package listeners;

import database.DataService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Boris on 18.02.2016.
 */
public class ContextInit implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataService service=new DataService();
        service.init();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
