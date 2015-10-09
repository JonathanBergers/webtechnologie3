package resources;

import matching.QueryResult;
import model.Model;
import model.Rating;
import model.User;
import register.CustomRestResponse;

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
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getRatings(){

        User u = getUserFromToken();
        if(u== null){
            return Response.status(400).entity(new CustomRestResponse().addError("accessToken", "invalid access token")).build();
        }

        return Response.accepted(getModel().getRatingsWithUser(u)).build();

    }



    @GET
    @Path("/search/xml")
    @Produces({ MediaType.APPLICATION_XML})
    public ArrayList<Rating> searchRatingsXML(){


        User u = getUserFromToken();
        if(u== null){
            return new ArrayList<>();
        }


        QueryResult<Rating> queryResult = getResources(Rating.class, getModel().getRatingsWithUser(u));

        return queryResult.getResults();

    }


    @GET
    @Path("/search/")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response searchRatings(){

        User u = getUserFromToken();
        if(u== null){
            return Response.status(400).entity(new CustomRestResponse().addError("accessToken", "invalid access token")).build();
        }


        QueryResult<Rating> queryResult = getResources(Rating.class, getModel().getRatingsWithUser(u));

        return Response.accepted(queryResult).build();

    }





}
