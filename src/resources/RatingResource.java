package resources;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import matching.QueryResult;
import model.Rating;
import model.User;
import register.CustomRestResponse;
import register.RatingService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by jonathan on 30-9-15.
 * kan geen xml parsen, daarom aparte xml path
 */
@Path("ratings")
public class RatingResource extends SearchableResource<Rating>{






    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getRatings(){

        User u = getUserFromToken();
        if(u== null){
            return Response.status(400).entity(new CustomRestResponse().addError("accessToken", "invalid access token")).build();
        }

        return Response.accepted(u.getRatings()).build();

    }

    @GET
    @Path("xml")
    @Produces({ MediaType.APPLICATION_XML})
    public ArrayList<Rating> getRatingsXML(){

        User u = getUserFromToken();
        if(u == null){
            return new ArrayList<Rating>();
        }

        return u.getRatings();

    }



    @PUT
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








}
