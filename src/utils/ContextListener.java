package utils;

import model.Model;
import model.Movie;
import model.Rating;
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
            model.addUser(new User("adolf" + i, "Adolf" + i, "Hitler"+ i, "Jemoder"));
            model.addMovie(new Movie(i,i, "KANKER DATE", "OK"+i, "ok", "OKEE", 10000+i ));


        }

        //rating test
        model.getMovies().get(0).Rate(10, model.getUsers().get(0));
        model.getMovies().get(0).Rate(-4, model.getUsers().get(0));
        model.getMovies().get(0).Rate(10, model.getUsers().get(1));
        model.getMovies().get(0).Rate(10, model.getUsers().get(2));
        model.getMovies().get(0).Rate(7, model.getUsers().get(3));

        model.getMovies().get(0).deleteRatingFromUser(model.getUsers().get(0));

        servletContextEvent.getServletContext().setAttribute("model", model);




    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {


    }
}
