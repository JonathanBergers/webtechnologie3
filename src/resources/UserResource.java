package resources;

import model.Model;
import model.User;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
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


    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getUserByNickName( @QueryParam("nickname") final String nickname){


        System.out.println(nickname);

        Model model = (Model) servletContext.getAttribute("model");
        return model.getUserByNickName(nickname);


    }







}
