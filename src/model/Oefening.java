package model;

import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by jonathan on 6-10-15.
 */
public class Oefening {


    public static void main(String[] args) {
        new Oefening().niks();
    }

    public class Foo<T> {

        private T name;

        public Foo(T name) {
            this.name = name;
        }

        public T getName() {
            return name;
        }

        public void setName(T name) {
            this.name = name;
        }

    }


    public void niks() {


        Foo f = new Foo(12);


        ArrayList<QueryParam> queryParams = new ArrayList<>();
        queryParams.add(new QueryParam("name", "jo"));
        queryParams.add(new QueryParam("Geen field", "niks"));
        QueryParam q = new QueryParam("name", "jo");

        hasValue(f);




    }

//
//    /**
//     * Deze methode kijkt of een object wel een getter heeft die overeenkomt met de naam van de query parameter
//     * recursief
//     *
//     * @param errors
//     * @param queryParams
//     * @param object
//     * @param <T>
//     * @return
//     */
//    private <T> ArrayList<QueryError<T>> checkErrors(ArrayList<QueryError<T>> errors, ArrayList<QueryParam> queryParams, T object) {
//
//
//        if (queryParams.isEmpty()) {
//
//            return errors;
//        }
//
//        QueryParam qp = queryParams.remove(0);
//
//        boolean error = true;
//        for (Method m : object.getClass().getMethods()) {
//
//            // object heeft een getter met voor met de naam van de query
//            if (("get" + qp.getName().toLowerCase()).equals(m.getName().toLowerCase())) {
//                error = false;
//                break;
//            }
//
//        }
//
//        if (error) {
//
//            errors.add(new QueryError<T>(qp));
//
//        }
//
//        checkErrors(errors, queryParams, object);
//
//        return errors;
//
//
//    }


    private void hasValue(Object object) {


        ArrayList<Foo> items = new ArrayList<>();
        items.add(new Foo<String>("jonathan"));
        items.add(new Foo<String>("bergers"));


        ArrayList<QueryParam> queryParams = new ArrayList<>();
        queryParams.add(new QueryParam("name", "jo"));
        queryParams.add(new QueryParam("Geen field", "niks"));
        QueryParam q = new QueryParam("name", "jo");


        // kijken of het object wel de attributen heeft van de query

        // even een arraylist maken voor contains
        ArrayList<Field> fields = new ArrayList<>();

        Collections.addAll(fields, object.getClass().getFields());


        for (Method m : object.getClass().getMethods()) {


            if (m.getName().toLowerCase().equals("get" + q.getName().toLowerCase())) {

                try {

                    Object o = m.invoke(object);

                    if (o instanceof String) {

                        String s = (String) o;
                        System.out.println(s);

                    }

                    if (o instanceof Integer) {

                        int s = (int) o;
                        System.out.println(s);
                    }


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }


    }






}
