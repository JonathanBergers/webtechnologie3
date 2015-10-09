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
public abstract class SearchableResource<T> {



    @Context
    ServletContext servletContext;



    @Context
    HttpServletRequest request;




    public QueryResult<T> getResources(Class classType, ArrayList<T> allItems){

        Enumeration<String> parameterNames = request.getParameterNames();
        ArrayList<QueryParameter<?>> parameters = new ArrayList<QueryParameter<?>>();

        while(parameterNames.hasMoreElements()){


            String parameterName = parameterNames.nextElement();
            String parameterValue = request.getParameter(parameterName);
            System.out.println(parameterName + " : "+ parameterValue);




            // als het een int is
            if(parameterValue.matches("-?\\d+(\\.\\d+)?")){

                QueryParameter<Integer> queryParam = new QueryParameter<Integer>(parameterName, Integer.parseInt(parameterValue));

                System.out.println("INT" + queryParam.getValue());
                parameters.add(queryParam);

            }else{
                QueryParameter<String> queryParam = new QueryParameter<String>(parameterName, parameterValue);
                parameters.add(queryParam);
            }




        }


        return  new Matcher<>(classType).getResult(parameters, allItems);


    }


}
