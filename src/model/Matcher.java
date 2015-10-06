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


    public QueryResult<T> getResult(ArrayList<QueryParam> queryParams){

        HashMap<QueryParam, Method> methodParamMap = getValidQueries(new HashMap<QueryParam, Method>(), queryParams);

        for(T item: items){

            try {
                if(!match(item, methodParamMap)){
                    items.remove(item);
                }
            } catch (InvocationTargetException e) {
                System.out.println("TERROR ERROR");

                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        return new QueryResult<T>(items, errors);

    }



    /**kijkt of een item dezelfde waarden heeft als de waarde van de parameters
     *
     * @param item
     * @return methodParameterMap, de map met daarin de query params en methoden om mee te valideren.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public boolean match(T item, HashMap<QueryParam, Method> methodParamMap) throws InvocationTargetException, IllegalAccessException {



        Set<QueryParam> queryParamsList = methodParamMap.keySet();


        for(QueryParam q: queryParamsList){

            Method m = methodParamMap.get(q);

            Object o = m.invoke(item);

            // kijken of de typen wel hetzelfde zijn
            if(o.getClass().equals(q.getValue().getClass())){

                // waarden zijn niet hetzelfde
                if(!o.equals(q.getValue())){
                    return false;

                }

            }else{
                assert false: "typen moeten hetzelfde zijn";
            }






        }
        return true;


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
                if(m.getReturnType().equals(String.class) || m.getReturnType().equals(Integer.class)){
                    error = false;
                    methodMap.put(qp, m);
                    break;

                }



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
