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
        ArrayList<QueryParameter<?>> parameters = new ArrayList<QueryParameter<?>>();

        while(parameterNames.hasMoreElements()){


            String parameterName = parameterNames.nextElement();
            System.out.println(parameterName);
            String parameterValue = request.getParameter(parameterName);
            QueryParameter<String> queryParam = new QueryParameter<String>(parameterName, parameterValue);
            parameters.add(queryParam);

        }






        System.out.println(nickname);

        Model model = (Model) servletContext.getAttribute("model");
        QueryResult<User> queryResult = new Matcher<>(User.class).getResult(parameters, model.getUsers());



       return queryResult.getResults();

    }









}
