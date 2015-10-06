package resources;

import com.sun.deploy.net.HttpRequest;
import model.Model;
import model.User;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by jonathan on 30-9-15.
 */
@Path("/users")
public class UserResource {


    @Context
    ServletContext servletContext;



    @Context
    HttpServletRequest request;





    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<User> getUserByNickName( @QueryParam("nickname") final String nickname){

        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){

            String s = parameterNames.nextElement();
            System.out.println(s);
            System.out.println(request.getParameter(s));


        }


        System.out.println(nickname);

        Model model = (Model) servletContext.getAttribute("model");

        if(nickname == null){
            return model.getUsers();
        }

        return null;


    }









}
