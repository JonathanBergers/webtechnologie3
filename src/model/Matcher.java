package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by jonathan on 6-10-15.
 */
public class Matcher<T> {


    private ArrayList<T> items;
    private final Class<T> compareClass;

    private ArrayList<Error> errors = new ArrayList<>();


    public Matcher(Class<T> compareClass) {
       this.compareClass = compareClass;

    }

    public ArrayList<Error> getErrors() {
        return errors;
    }



    public boolean match(T item, ArrayList<QueryParam> queryParams) throws InvocationTargetException, IllegalAccessException {

        HashMap<QueryParam, Method> methodParamMap = getValidQueries(new HashMap<>(), queryParams);


        Set<QueryParam> queryParamsList = methodParamMap.keySet();

        for(QueryParam q: queryParamsList){

            Method m = methodParamMap.get(q);

            Object o = m.invoke(item);

            if(o instanceof )

        }


    }


    /**
     * Deze methode zorgt ervoor dat de queries gematched worden met de methode, als een methode een queryname niet heeft wordt er een error toegevoegd aan de lijst met errors
     * recursief
     *
     * @param queryParams
     * @return
     */
    public HashMap<QueryParam, Method> getValidQueries(HashMap<QueryParam, Method> methodMap, ArrayList<QueryParam> queryParams) {


        if (queryParams.isEmpty()) {

            return methodMap;
        }

        QueryParam qp = queryParams.remove(0);

        boolean error = true;
        for (Method m : compareClass.getClass().getMethods()) {

            // object heeft een getter met voor met de naam van de query
            if (("get" + qp.getName().toLowerCase()).equals(m.getName().toLowerCase())) {

                //alleen int of Strings voor deze matcher
                switch (m.getReturnType()){

                    //case String.class:
                }


                error = false;
                methodMap.put(qp, m);
                break;
            }

        }

        // als er geen veld is voor de query dan wordt er een error toegevoegd.
        if (error) {

            errors.add(new QueryError<T>(compareClass, qp));

        }

        getValidQueries(methodMap, queryParams);

        return methodMap;


    }








}
