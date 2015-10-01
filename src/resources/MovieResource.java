package resources;

import model.Model;
import model.Movie;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


/**
 * Created by falco on 30-9-15.
 */
@Path("/movies")
public class MovieResource {

    @Context
    ServletContext servletContext;




    @GET
    @Produces({MediaType.APPLICATION_XML})
    public ArrayList<Movie> getMovies(){
        Model model = (Model) servletContext.getAttribute("model");
        return model.getMovies();
    }
}
