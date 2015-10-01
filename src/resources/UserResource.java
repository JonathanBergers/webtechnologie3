package resources;

import model.Model;
import model.User;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by jonathan on 30-9-15.
 */
@Path("/users")
public class UserResource {


    @Context
    ServletContext servletContext;




    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<User> getUsers(){

        Model model = (Model) servletContext.getAttribute("model");
        return model.getUsers();


    }

    @Path("/jo")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getUser(){

        Model model = (Model) servletContext.getAttribute("model");
        return model.getUsers().get(0);


    }




}
