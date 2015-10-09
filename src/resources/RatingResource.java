package resources;

import matching.QueryResult;
import model.Model;
import model.Rating;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by jonathan on 30-9-15.
 * kan geen xml parsen, daarom aparte xml path
 */
@Path("/ratings")
public class RatingResource extends SearchableResource<Rating>{





    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getRatings(){



        Model model = (Model) servletContext.getAttribute("model");
        QueryResult<Rating> queryResult = getResources(Rating.class, model.getRatings());


       return Response.accepted(queryResult).build();

    }



    @GET
    @Path("/xml")
    @Produces({ MediaType.APPLICATION_XML})
    public ArrayList<Rating> getUsersXML(){

        Model model = (Model) servletContext.getAttribute("model");
        QueryResult<Rating> queryResult = getResources(Rating.class, model.getRatings());

        return queryResult.getResults();

    }






}
