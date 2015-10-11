package resources;

import matching.QueryResult;
import model.Model;
import model.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


/**
 * Created by falco on 30-9-15.
 */
@Path("/movies/")
public class MovieResource extends SearchableResource<Movie>{


    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getMovies(){



        Model model = (Model) servletContext.getAttribute("model");
        QueryResult<Movie> queryResult = getResources(Movie.class, model.getMovies());

        return Response.accepted(queryResult).build();

    }

//
//
//    @GET
//    @Path("/xml")
//    @Produces({ MediaType.APPLICATION_XML})
//    public ArrayList<Movie> getMoviesXML(){
//
//        Model model = (Model) servletContext.getAttribute("model");
//        QueryResult<Movie> queryResult = getResources(Movie.class, model.getMovies());
//
//        return queryResult.getResults();
//
//    }


}
