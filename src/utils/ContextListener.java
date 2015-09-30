package utils;

import model.Model;
import model.User;
import org.omg.IOP.ServiceContextListHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by jonathan on 30-9-15.
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Model model = new Model();
        for (int i = 0; i < 10; i++) {
            model.users.addUser(new User("adolf" + i, "Adolf" + i, "Hitler"+ i, "Jemoder"));
        }


        servletContextEvent.getServletContext().setAttribute("model", model);




    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {


    }
}