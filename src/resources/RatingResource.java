package resources;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import matching.QueryResult;
import model.Model;
import model.Rating;
import model.User;
import register.CustomRestResponse;
import register.RatingService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
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



    @PUT
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Response changeRating(String inputJson){
        CustomRestResponse restResponse = new CustomRestResponse();
        User u = getUserFromToken();
        if(u== null){
            return Response.status(400).entity(restResponse.addError("accessToken", "invalid access token")).build();
        }


        JsonObject jsonObject = new GsonBuilder().create().fromJson(inputJson, JsonObject.class);

        if(jsonObject == null){
            return Response.status(400).entity(restResponse.addError("jsonparse", "json null")).build();
        }

        restResponse =  RatingService.validateUserCredentials(restResponse, jsonObject, getModel(), u);

        return Response.accepted(restResponse).build();

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
