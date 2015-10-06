package resources;

import model.Model;
import model.Movie;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Movie> getMovies(@QueryParam("title") String title){
        Model model = (Model) servletContext.getAttribute("model");
        if(title == null){
            return model.getMovies();
        }

        return model.getMoviesByTitle(title);
    }

}
