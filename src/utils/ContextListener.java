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


        String name = "Jonathan";
        String lastname = "Bergers";
        String password  = "wachtwoord";
        String email = name + "@"+ lastname + ".com";


        Model model = new Model();
        for (int i = 0; i < 10; i++) {
            model.addUser(new User(name + i, lastname + i, password+i, email ));
        }
        //new Movie(id , tt , date ,titel, discription, director, length)
        model.addMovie(new Movie(model.getMovies().size(), 2294629, "2013", "Frozen", "When the newly crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister, Anna, teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.", "Chris Buck, Jennifer Lee", 102));
        model.addMovie(new Movie(model.getMovies().size(), 2015381, "2014", "Guardians of the Galaxy","A group of intergalactic criminals are forced to work together to stop a fanatical warrior from taking control of the universe." ,"James Gunn" , 121 ));
        model.addMovie(new Movie(model.getMovies().size(), 2582802, "2014", "Whiplash", "A promising young drummer enrolls at a cut-throat music conservatory where his dreams of greatness are mentored by an instructor who will stop at nothing to realize a student's potential.","Damien Chazelle" ,107  ));
        model.addMovie(new Movie(model.getMovies().size(), 2802144, "2014", "Kingsman: The Secret Service", "A spy organization recruits an unrefined, but promising street kid into the agency's ultra-competitive training program, just as a global threat emerges from a twisted tech genius.", "Matthew Vaughn",129 ));
        model.addMovie(new Movie(model.getMovies().size(), 1790864, "2014", "The Maze Runner", "Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow \"runners\" for a shot at escape.", "Wes Ball", 113));
        model.addMovie(new Movie(model.getMovies().size(), 2267998, "2014", "Gone Girl", "With his wife's disappearance having become the focus of an intense media circus, a man sees the spotlight turned on him when it's suspected that he may not be innocent.", "David Fincher", 149));
        model.addMovie(new Movie(model.getMovies().size(), 2179136, "2014", "American Sniper", "Navy S.E.A.L. sniper Chris Kyle's pinpoint accuracy saves countless lives on the battlefield and turns him into a legend. Back home to his wife and kids after four tours of duty, however, Chris finds that it is the war he can't leave behind.", "Clint Eastwood", 133));
        model.addMovie(new Movie(model.getMovies().size(), 2980516, "2014", "The Theory of Everything", "A look at the relationship between the famous physicist Stephen Hawking and his wife.", "James Marsh", 123));
        model.addMovie(new Movie(model.getMovies().size(), 1809398, "2014", "Unbroken", "After a near-fatal plane crash in WWII, Olympian Louis Zamperini spends a harrowing 47 days in a raft with two fellow crewmen before he's caught by the Japanese navy and sent to a prisoner-of-war camp.", "Angelina Jolie", 137));
        model.addMovie(new Movie(model.getMovies().size(), 2911666, "2014", "John Wick", "An ex-hitman comes out of retirement to track down the gangsters that took everything from him.", "Chad Stahelski, David Leitch", 101));

        model.addUser(new User("k", "k", "k", "kanker@dikkestrond.kkr"));

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
