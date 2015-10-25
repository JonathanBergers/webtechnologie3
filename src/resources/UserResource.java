package resources;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import login.LoginService;
import matching.QueryResult;
import model.User;
import register.CustomRestResponse;
import register.RegistrationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by jonathan on 30-9-15.
 * kan geen xml parsen, daarom aparte xml path
 */
@Path("users")
public class UserResource extends SearchableResource<User>{





    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Response registerUser(String testObject){

        CustomRestResponse restResponse = new CustomRestResponse();
        System.out.println("KIJK");
        System.out.println(testObject);

        JsonObject jsonObject = new GsonBuilder().create().fromJson(testObject, JsonObject.class);


        if(jsonObject == null ||jsonObject.isJsonNull()){
            restResponse.addError("convert", "json is null");
            return Response.status(403).entity(restResponse).build();
        }

        RegistrationService registrationService = new RegistrationService(jsonObject,getModel());

        restResponse = registrationService.validateUserCredentials(restResponse);


        System.out.println((new GsonBuilder().create().toJson(restResponse.getErrors())).toString());

        return Response.ok(restResponse).build();

    }


    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getUsers(){

        QueryResult<User> queryResult = getResources(User.class, getModel().getUsers());


       return Response.accepted(queryResult).build();

    }



    @GET
    @Path("xml")
    @Produces({ MediaType.APPLICATION_XML})
    public ArrayList<User> getUsersXML(){

        QueryResult<User> queryResult = getResources(User.class, getModel().getUsers());

        return queryResult.getResults();

    }









}
