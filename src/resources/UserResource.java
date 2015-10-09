package resources;

import com.sun.deploy.net.HttpRequest;
import matching.Matcher;
import matching.QueryParameter;
import matching.QueryResult;
import model.Model;
import model.User;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by jonathan on 30-9-15.
 * kan geen xml parsen, daarom aparte xml path
 */
@Path("/users")
public class UserResource extends SearchableResource<User>{





    @GET
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
