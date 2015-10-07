package resources;

import matching.Matcher;
import matching.QueryParameter;
import matching.QueryResult;
import model.Model;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by jonathan on 6-10-15.
 */
public abstract class BaseResource<T> {



    @Context
    ServletContext servletContext;



    @Context
    HttpServletRequest request;




    public ArrayList<T> getResources(Class classType, ArrayList<T> allItems){

        Enumeration<String> parameterNames = request.getParameterNames();
        ArrayList<QueryParameter<?>> parameters = new ArrayList<QueryParameter<?>>();

        while(parameterNames.hasMoreElements()){


            String parameterName = parameterNames.nextElement();
            System.out.println(parameterName);
            String parameterValue = request.getParameter(parameterName);
            QueryParameter<String> queryParam = new QueryParameter<String>(parameterName, parameterValue);
            parameters.add(queryParam);

        }



        QueryResult<T> queryResult = new Matcher<>(classType).getResult(parameters, allItems);
        return queryResult.getResults();


    }


}
