package resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BaseJsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.deploy.net.HttpRequest;
import matching.Matcher;
import matching.QueryParameter;
import matching.QueryResult;
import model.Model;
import model.User;
import register.CustomRestResponse;
import register.RegistrationService;
import test.TestObject;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by jonathan on 30-9-15.
 * kan geen xml parsen, daarom aparte xml path
 */
@Path("/users")
public class UserResource extends SearchableResource<User>{





    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Response registerUser(String testObject){

        CustomRestResponse restResponse = new CustomRestResponse();

        System.out.println(testObject);

        JsonObject jsonObject = new GsonBuilder().create().fromJson(testObject, JsonObject.class);


        if(jsonObject == null ||jsonObject.isJsonNull()){
            restResponse.addError("convert", "json is null");
            return Response.status(403).entity(restResponse).build();
        }

        RegistrationService registrationService = new RegistrationService(jsonObject,(Model) servletContext.getAttribute("model"));

        restResponse = registrationService.validateUserCredentials(restResponse);


        System.out.println((new GsonBuilder().create().toJson(restResponse.getErrors())).toString());

        return Response.ok(restResponse).build();

    }


    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getUsers(){



        Model model = (Model) servletContext.getAttribute("model");
        QueryResult<User> queryResult = getResources(User.class, model.getUsers());


       return Response.accepted(queryResult).build();

    }



    @GET
    @Path("/xml")
    @Produces({ MediaType.APPLICATION_XML})
    public ArrayList<User> getUsersXML(){

        Model model = (Model) servletContext.getAttribute("model");
        QueryResult<User> queryResult = getResources(User.class, model.getUsers());

        return queryResult.getResults();

    }






}
