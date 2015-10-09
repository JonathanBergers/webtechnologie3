package matching;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Deze matcher kan een lijst filteren op basis van meegegeven queryparameters
 * de matcher gebruikt reflection om de attributen te comparen met de queryparameters
 * Created by jonathan on 6-10-15.
 */
public class Matcher<T> {


    private final Class<T> compareClass;

    private ArrayList<Error> errors = new ArrayList<>();


    public Matcher(Class<T> compareClass) {
       this.compareClass = compareClass;

    }

    public ArrayList<Error> getErrors() {
        return errors;
    }


    public QueryResult<T> getResult(QueryParameter<?> queryParameter, ArrayList<T> items){

        ArrayList<QueryParameter<?>> queryParameters = new ArrayList<>();
        queryParameters.add(queryParameter);

        return getResult(queryParameters, items);

    }

    /**filtert alle items op basis van de meegegeven queries
     *
     * @param queryParameters
     * @param items
     * @return
     */
    public QueryResult<T> getResult(ArrayList<QueryParameter<?>> queryParameters, ArrayList<T> items){

        HashMap<QueryParameter, Method> methodParamMap = getValidQueries(new HashMap<QueryParameter, Method>(), queryParameters);

        ArrayList<T> itemsForResult= new ArrayList<>();
        for(T item: items){

            try {
                if(match(item, methodParamMap)){
                    itemsForResult.add(item);
                }
            } catch (InvocationTargetException e) {
                System.out.println("TERROR ERROR");

                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("TERROR ERROR2");
                e.printStackTrace();
            }
        }



        return new QueryResult<T>(itemsForResult, errors);

    }



    /**kijkt of een item dezelfde waarden heeft als de waarde van de parameters
     *
     * @param item
     * @return methodParameterMap, de map met daarin de query params en methoden om mee te valideren.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public boolean match(T item, HashMap<QueryParameter, Method> methodParamMap) throws InvocationTargetException, IllegalAccessException {



        Set<QueryParameter> queryParamsList = methodParamMap.keySet();


        for(QueryParameter q: queryParamsList){

            Method m = methodParamMap.get(q);

            System.out.println(item.toString());
            System.out.println(m.getName());
            System.out.println(item.getClass().getName());

            Object o = m.invoke(item);


            // als je zoekt op iets dat null is
            if(o == null){
                o = "null";
            }


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
     * @param queryParameters
     * @return
     */
    public HashMap<QueryParameter, Method> getValidQueries(HashMap<QueryParameter, Method> methodMap, ArrayList<QueryParameter<?>> queryParameters) {


        if (queryParameters.isEmpty()) {

            return methodMap;
        }

        QueryParameter qp = queryParameters.remove(0);

        boolean error = true;
        for (Method m : compareClass.getMethods()) {

            // object heeft een getter met voor met de naam van de query
            if (("get" + qp.getName().toLowerCase()).equals(m.getName().toLowerCase())) {

                //alleen int of Strings voor deze matcher
                if(m.getReturnType().equals(String.class) || m.getReturnType().equals(int.class) || m.getReturnType().equals(Integer.class)){

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

        getValidQueries(methodMap, queryParameters);

        return methodMap;


    }








}
