package resources;

import model.Movie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;


/**
 * Created by falco on 30-9-15.
 */
@Path("/movies")
public class MovieResourse {

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Response getMovies(){
        return Response.created(URI.create("/")).build();
    }
}
